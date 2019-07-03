package de.htwg.se.scrabble.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.scrabble.ScrabbleModule
import de.htwg.se.scrabble.controller.{GameStatus, StateCacheInterface}
import de.htwg.se.scrabble.controller.GameStatus._
import de.htwg.se.scrabble.controller.controllerBaseImpl.StateCache
import de.htwg.se.scrabble.model._
import de.htwg.se.scrabble.model.cards.{Card, EmptyCardStack, RegularCardStack}
import de.htwg.se.scrabble.model.field.Cell
import de.htwg.se.scrabble.model.fileIoComponent.FileIOInterface
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import play.api.libs.json._

import scala.io.Source

class FileIO extends FileIOInterface {

  override def load: StateCacheInterface = {
    var field: FieldInterface = null
    val source: String = Source.fromFile("savedstate.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "field" \ "size").get.toString.toInt
    val injector = Guice.createInjector(new ScrabbleModule)
    size match {
      case 15 => field = injector.instance[FieldInterface](Names.named("regular"))
      case _ =>
    }
    for (index <- 0 until size * size) {
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[String]
      val cell = (json \\ "cell")(index)
      val value = (cell \ "value").as[String]
      field.setCell(col, row, value)
    }
    StateCache(field, new RegularCardStack,new PlayerList,"",GameStatus.IDLE,None,true)
  }

  override def save(states: StateCacheInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("savedstate.json"))
    pw.write(Json.prettyPrint(statesToJson(states)))
    pw.close
  }

  implicit val cellWrites = new Writes[CellInterface] {
    def writes(cell: CellInterface) = Json.obj(
      "value" -> cell.getValue,
    )
  }
  implicit val cardWrites = new Writes[Card] {
    def writes(card: Card) = Json.obj(
      "card" -> card.value
    )
  }

  implicit val playerWrites = new Writes[PlayerInterface] {
    def writes(player: PlayerInterface) = Json.obj(
      "role" -> player.role,
      "name" -> player.name,
      "points" -> player.getPoints,
      "actionPermit" -> player.actionPermitted,
      "switchedHand" -> player.switchedHand,
      "hand" -> Json.obj(
        "size" -> JsNumber(player.getNrCardsInHand),
        "cards" -> Json.toJson(
          for(card <- player.getHand) yield card
        )
      )
    )
  }

  def statesToJson(states: StateCacheInterface) = {
    Json.obj(
      "field" -> fieldToJson(states.field),
      "stack" -> stackToJson(states.stack),
      "players" -> playerListToJson(states.players),
      "variables" -> variablesToJson(states.roundManager, states.activePlayer, states.firstDraw)
    )
  }

  def fieldToJson(field: FieldInterface) = {
    Json.obj(
        "size" -> JsNumber(field.getSize),
        "cells" -> Json.toJson(
          for {
            row <- 1 to field.getSize
            col <- 65 until 65 + field.getSize
          } yield
            Json.obj(
              "row" -> row,
              "col" -> col.toChar.toString,
              "value" -> field.getCell(col.toChar.toString, row)
            )
        )
      )
  }
  def stackToJson(stack: CardStackInterface) = {
    Json.obj(
        "size" -> stack.getSize,
        "cards" -> Json.toJson(
          for (card <- stack.getStack) yield card
      )
    )
  }
  def playerListToJson(players: PlayerListInterface) = {
    Json.toJson(
        for (player <- players.getList) yield player
    )
  }
  def variablesToJson(rm: String, ap: Option[PlayerInterface], fd: Boolean) = {
    Json.obj(
        "roundManager" -> rm,
        "activePlayer" -> {if (ap.isDefined) ap.get.role else ""},
        "firstDraw" -> fd
      )
  }
}
