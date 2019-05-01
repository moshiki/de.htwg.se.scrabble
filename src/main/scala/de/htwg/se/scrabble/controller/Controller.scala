package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.model.Dictionary
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import de.htwg.se.scrabble.util.Observable

import scala.io.StdIn.readLine

class Controller extends Observable {
  private var dict = new Dictionary
  val players = new PlayerList

  def printDict(): Unit = dict.printDict()

  def printVector(): Unit = dict.printVector()

  def reloadDict(): Unit = {
    dict = new Dictionary
    notifyObservers
  }

  def newPlayer(role:String, name:String): Option[Player] = {
    val player = Player(role, name)
    val oldPlayer = players.get(player.role)
    oldPlayer match {
      case Some(p) =>
        println("overwrite existing player: "+p+"? Y, N")
        readLine(">> ") match {
          case "y" | "Y" =>
            notifyObservers
            players.put(player)
            Option(player)
          case other => None
        }
      case None =>
        notifyObservers
        players.put(player)
        Option(player)
    }

  }
}
