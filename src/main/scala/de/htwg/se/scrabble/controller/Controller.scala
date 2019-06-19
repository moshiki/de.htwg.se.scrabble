package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.controller.GameStatus._
import de.htwg.se.scrabble.model.{Dictionary, FieldTemplate, RegularField}
import de.htwg.se.scrabble.model.cards.{Card, CardStackTemplate, RegularCardStack}
import de.htwg.se.scrabble.model.gameManager._
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import de.htwg.se.scrabble.util.{Observable, Observer, UndoManager}

class Controller extends Observable with Observer{
  private val dict = Dictionary
  var players = new PlayerList
  var field: FieldTemplate = RegularField(15, this)
  var stack: CardStackTemplate = new RegularCardStack

  var roundManager: GameManager = PreSetupManager(this)
  var gameStatus: GameStatus = IDLE
  private val undoManager = new UndoManager

  def dictToString: String = dict.dictToString

  def vectorToString: String = dict.vectorToString

  def newGame(): Unit = {
    field = RegularField(15, this)
    stack = new RegularCardStack
    roundManager = SetupManager(this)
    //notifyObservers
  }

  def newPlayer(role:String, name:String): Unit = {
    players.put(Player(role, name))
    notifyObservers
  }

  def getCard: Option[Card] = {
    stack.getCard
  }

  def set(x: String, y: Int, value: String): Unit = {
    undoManager.doStep(new SetCommand(x, y, value, this))
    notifyObservers
  }
  def undo(): Unit = {
    undoManager.undoStep
    notifyObservers
  }
  def redo(): Unit = {
    undoManager.redoStep
    notifyObservers
  }

  override def update: Boolean = {notifyObservers; true}
}
