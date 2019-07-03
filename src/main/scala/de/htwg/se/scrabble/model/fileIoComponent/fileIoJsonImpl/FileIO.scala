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
import de.htwg.se.scrabble.model.fileIoComponent.FileIOInterface
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import play.api.libs.json._

import scala.io.Source

class FileIO extends FileIOInterface {

  override def load: StateCacheInterface = {
    val injector = Guice.createInjector(new ScrabbleModule)
    val source: String = Source.fromFile("savedstate.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    var field: FieldInterface = null
    val stack: CardStackInterface = new EmptyCardStack
    val players: PlayerListInterface = injector.instance[PlayerListInterface]
    val roundManager = (json \ "variables" \ "roundManager").as[String]
    var gameStatus = IDLE
    var activePlayer: Option[PlayerInterface] = None
    val firstDraw = (json \ "variables" \ "firstDraw").as[Boolean]

    val size = (json \ "field" \ "size").get.toString.toInt
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

    val cardStack = (json \ "stack" \ "cards" \\ "card")
    for (card <- cardStack) {
      stack.putCard(Card(card.as[String]))
    }

    val playerList = (json \ "players" \\ "player")
    for (player <- playerList) {
      val role = (player \ "role").as[String]
      val name = (player \ "name").as[String]
      val handSize = (player \ "hand" \ "size").as[Int]
      val hand = (player \ "hand" \\ "card")
      val points = (player \ "points").as[Int]
      val actionPerm = (player \ "actionPermit").as[Boolean]
      val switchedHand = (player \ "switchedHand").as[Boolean]

      players.put(Player(role, name))
      for (card <- hand) {
        players.get(role).get.addToHand(Card(card.as[String]))
      }
      players.get(role).get.addPoints(points)
      if (actionPerm) {
        players.get(role).get.grantActionPermit()
      } else {
        players.get(role).get.revokeActionPermit()
      }
      if (switchedHand) {
        players.get(role).get.grantSwitchedHand()
      } else {
        players.get(role).get.revokeSwitchedHand()
      }
    }
    activePlayer = players.get((json \ "variables" \ "activePlayer").as[String])
    StateCache(field,stack,players,roundManager,gameStatus,activePlayer,firstDraw)
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
      "player" -> Json.obj(
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
              "cell" -> field.getCell(col.toChar.toString, row)
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
