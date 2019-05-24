package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.controller.GameStatus._
import de.htwg.se.scrabble.model.{Dictionary, FieldTemplate, RegularField}
import de.htwg.se.scrabble.model.cards.{Card, CardStackTemplate, RegularCardStack}
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import de.htwg.se.scrabble.util.Observable

class Controller extends Observable {
  private var dict = new Dictionary
  var players = PlayerList
  var field: FieldTemplate = RegularField(15)
  val stack: CardStackTemplate = RegularCardStack

  var gameStatus: GameStatus = IDLE

  def dictToString: String = dict.dictToString

  def vectorToString: String = dict.vectorToString

  def reloadDict(): Unit = {
    dict = new Dictionary
    notifyObservers
  }

  def newGame(): Unit = {
    field = RegularField(15)
  }

  def newPlayer(role:String, name:String): Unit = {
    players.put(Player(role, name))
    notifyObservers
  }

  def getCard: Option[Card] = {
    stack.getCard
  }

}
