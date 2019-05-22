package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.model.Dictionary
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import de.htwg.se.scrabble.util.Observable

import scala.io.StdIn.readLine

class Controller extends Observable {
  private var dict = new Dictionary
  val players = new PlayerList

  var gameStatus: GameStatus = IDLE

  def printDict(): Unit = dict.printDict()

  def printVector(): Unit = dict.printVector()

  def reloadDict(): Unit = {
    dict = new Dictionary
    notifyObservers
  }

  def newPlayer(role:String, name:String): Unit = {
    players.put(Player(role, name))
    notifyObservers
  }

  def getCard: String = {
    val r = util.Random.nextInt(26)
    val arr = dict.alphabet.toArray
    arr(r)._1
  }

}
