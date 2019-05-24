package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.controller.GameStatus._
import de.htwg.se.scrabble.model.RegularField
import de.htwg.se.scrabble.model.Dictionary
import de.htwg.se.scrabble.model.cards.CardStack
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import de.htwg.se.scrabble.util.Observable

class Controller extends Observable {
  private var dict = new Dictionary
  var players = PlayerList
  var field = RegularField(15)
  val stack = CardStack

  var gameStatus: GameStatus = IDLE

  def printDict(): Unit = dict.printDict()

  def printVector(): Unit = dict.printVector()

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

  def getCard: Option[String] = {
    stack.getCard
  }

}
