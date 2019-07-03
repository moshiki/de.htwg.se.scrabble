/*
package de.htwg.se.scrabble.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.scrabble.ScrabbleModule
import de.htwg.se.scrabble.controller.StateCacheInterface
import de.htwg.se.scrabble.controller.GameStatus._
import de.htwg.se.scrabble.controller.controllerBaseImpl.StateCache
import de.htwg.se.scrabble.model._
import de.htwg.se.scrabble.model.cards.{Card, EmptyCardStack}
import de.htwg.se.scrabble.model.field.Cell
import de.htwg.se.scrabble.model.fileIoComponent.FileIOInterface
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import play.api.libs.json.{JsValue, Json}

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
      val given = (cell \ "given").as[Boolean]
      val showCandidates = (cell \ "showCandidates").as[Boolean]
      if (given) grid = grid.setGiven(row, col, value)
      if (showCandidates) grid = grid.setShowCandidates(row, col)
    }
    grid
  }

  override def save(grid: GridInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("grid.json"))
    pw.write(Json.prettyPrint(gridToJson(grid)))
    pw.close
  }

  implicit val cellWrites = new Writes[CellInterface] {
    def writes(cell: CellInterface) = Json.obj(
      "value" -> cell.value,
      "given" -> cell.given,
      "showCandidates" -> cell.showCandidates
    )
  }

  def gridToJson(grid: GridInterface) = {
    Json.obj(
      "grid" -> Json.obj(
        "size" -> JsNumber(grid.size),
        "cells" -> Json.toJson(
          for {
            row <- 0 until grid.size;
            col <- 0 until grid.size
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(grid.cell(row, col))
            )
          }
        )
      )
    )
  }



}
*/
