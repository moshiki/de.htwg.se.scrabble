package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.controller.GameStatus._
import de.htwg.se.scrabble.model.{Dictionary, FieldTemplate, RegularField}
import de.htwg.se.scrabble.model.cards.{Card, CardStackTemplate, RegularCardStack}
import de.htwg.se.scrabble.model.gameManager._
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import de.htwg.se.scrabble.util.{Observable, Observer}

class Controller extends Observable {
  private val dict = Dictionary
  var players = PlayerList
  var field: FieldTemplate = RegularField(15)
  val stack: CardStackTemplate = RegularCardStack

  var roundManager: GameManager = SetupManager()
  var gameStatus: GameStatus = IDLE

  def dictToString: String = dict.dictToString

  def vectorToString: String = dict.vectorToString

  def newGame(): Unit = {
    field = RegularField(15)
    players.put(Player("player a", "1"))
    players.put(Player("player b", "2"))
    gameStatus
    notifyObservers
  }

  def newPlayer(role:String, name:String): Unit = {
    players.put(Player(role, name))
    notifyObservers
  }

  def getCard: Option[Card] = {
    stack.getCard
  }
}
