package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.controller.GameStatus._
import de.htwg.se.scrabble.model.{Dictionary, FieldTemplate, RegularField}
import de.htwg.se.scrabble.model.cards.{Card, CardStackTemplate, RegularCardStack}
import de.htwg.se.scrabble.model.gameManager._
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import de.htwg.se.scrabble.util.{Observable, Observer, UndoManager}

object Controller extends Observable with Observer{
  private val dict = Dictionary
  var players = new PlayerList
  var field: FieldTemplate = RegularField(15)
  var stack: CardStackTemplate = new RegularCardStack

  var roundManager: GameManagerState = new PreSetupManagerState
  var gameStatus: GameStatus = IDLE
  var activePlayer: Option[Player] = None
  private val undoManager = new UndoManager

  def dictToString: String = dict.dictToString

  def vectorToString: String = dict.vectorToString

  def newGame(): Unit = {
    field = RegularField(15)
    stack = new RegularCardStack
    roundManager = new SetupManagerState
    roundManager.start()
    //notifyObservers
  }

  def newPlayer(role:String, name:String): Unit = {
    players.put(Player(role, name))
    notifyObservers
  }

  def next(): Unit = {
    if (roundManager.isInstanceOf[RoundManagerState]) {
      activePlayer = inactivePlayer
      roundManager = new RoundManagerState
      roundManager.start()
      notifyObservers
    }
  }

  def inactivePlayer: Option[Player] = {
    if (activePlayer.get == players.get("A").get) {
      players.get("B")
    } else {
      players.get("A")
    }
  }

  def getCard: Option[Card] = {
    stack.getCard
  }

  def set(x: String, y: Int, value: String): Unit = {
    undoManager.doStep(new SetCommand(x, y, value, activePlayer))
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
