package de.htwg.se.scrabble.model.player

import com.google.inject.Inject
import de.htwg.se.scrabble.model.PlayerListInterface

import scala.collection.mutable

class PlayerList @Inject() extends PlayerListInterface {
  private val playerMap = mutable.TreeMap[String, Player]()

  def put(player: Player): Option[Player] = playerMap.put(player.role, player)

  def contains(player: Player): Boolean = revMap().contains(player)

  def exists(role: String): Boolean = if (get(role).isDefined) true else false

  def get(role: String): Option[Player] = {

    role match {
      case "a" | "A" => playerMap.get("A")
      case "b" | "B" => playerMap.get("B")
      case _ => None
    }
  }

  def getList: List[Player] = playerMap.values.toList

  override def toString: String = {
    val str = new mutable.StringBuilder()
    playerMap.foreach(x => str.append(x._2.toString + "\n"))
    str.toString()
  }

  private def revMap(): mutable.Map[Player, String] = {
    mutable.Map[Player, String]() ++= playerMap map {
      _.swap
    }
  }
}

object PlayerList {
  import play.api.libs.json._
  implicit val playerListWrites = OWrites[PlayerList](_ => Json.obj())
  //implicit val cellReads = Reads[Cell](json =>
  //json.validate[JsObject].filter(_.values.isEmpty).map(_ => Cell()))
}
