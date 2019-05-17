package de.htwg.se.scrabble.model.player

import scala.collection.mutable

class PlayerList {
  private val playerMap = mutable.TreeMap[String, Player]()

  def put(player:Player): Option[Player] = playerMap.put(player.role, player)

  def contains(player:Player): Boolean = revMap().contains(player)

  def exists(role:String): Boolean = if (get(role).isDefined) true else false

  def remove(player:Player): Option[Player] = playerMap.remove(getByPlayer(player))

  def get(role:String): Option[Player] = {

    role match {
      case "a" | "A" => playerMap.get("A")
      case "b" | "B" => playerMap.get("B")
    }
  }

  def getList: List[Player] = playerMap.values.toList

  def print(): Unit = getList.foreach(println)

  private def getByPlayer(player:Player): String = {

      val revMap = playerMap map {_.swap}
      revMap.get(player) match {
        case None => ""
      }
  }

  private def revMap(): mutable.Map[Player, String] = {
    mutable.Map[Player, String]() ++= playerMap map {_.swap}
  }


}
