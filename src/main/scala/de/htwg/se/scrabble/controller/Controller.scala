package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.controller.GameStatus._
import de.htwg.se.scrabble.model.{Dictionary, FieldTemplate, RegularField}
import de.htwg.se.scrabble.model.cards.{Card, CardStackTemplate, RegularCardStack}
import de.htwg.se.scrabble.model.gameManager._
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import de.htwg.se.scrabble.util.{Observable, Observer, UndoManager}


class Controller extends Observable {
  private val dict = Dictionary
  var players =  new PlayerList
  var grid: FieldTemplate = new RegularField(15)
  var stack: CardStackTemplate = new RegularCardStack

  var undoManager: UndoManager = new UndoManager

  var roundManager: GameManager = SetupManager()
  var gameStatus: GameStatus = IDLE

  def dictToString: String = dict.dictToString

  def vectorToString: String = dict.vectorToString

  def newGame(): Unit = {
    grid = RegularField(15)
    players.put(Player("player a", "1"))
    players.put(Player("player b", "2"))
//    gameStatus
    notifyObservers
  }

  def newGrid(): Unit = {

  }

  def newPlayer(role:String, name:String): Unit = {
    players.put(Player(role, name))
    notifyObservers
  }

  def getCard: Option[Card] = {
    stack.getCard
  }

  def set(newGrid:FieldTemplate, newPlayers: PlayerList, newCards: CardStackTemplate): Unit = {
    this.players = newPlayers
    this.grid = newGrid
    this.stack = newCards
  }
}
