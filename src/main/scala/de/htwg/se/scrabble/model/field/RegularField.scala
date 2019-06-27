package de.htwg.se.scrabble.model.field

import de.htwg.se.scrabble.controller.GameStatus
import de.htwg.se.scrabble.model.FieldInterface

import scala.collection.mutable
import scala.collection.mutable.SortedMap


case class RegularField(size: Int) extends FieldInterface {
  // var size = 15
  var grid: SortedMap[Int, SortedMap[String, Cell]] = SortedMap.empty[Int, SortedMap[String, Cell]]

  for (row <- 1 to size) {
    grid += (row -> SortedMap.empty[String, Cell])
    for (a <- 65 until (65 + size)) {
      var col = a.toChar.toString
      grid(row) += (col -> new Cell("_"))
    }
  }
  setCell((65+size/2).toChar.toString, size/2, "*")

  override def getCell(col: String, row: Int): Option[Cell] = {
    val X = col.toUpperCase().charAt(0)-65
    if (X < size && X >= 0 && row <= size && row > 0) {
      Some(grid(row)(col))
    } else {
      controller.gameStatus = GameStatus.OOBOUND
      None
    }
  }

  override def getNextCell(cell: Cell): Option[Cell] = {
    var coord = getCoordinates(cell).getOrElse(return None)
    getCell((coord.col+1).toChar.toString, coord.row)
  }

  override def getPrevCell(cell: Cell): Option[Cell] = {
    var coord = getCoordinates(cell).getOrElse(return None)
    getCell((coord.col-1).toChar.toString, coord.row)
  }

  override def getUpperCell(cell: Cell): Option[Cell] = {
    var coord = getCoordinates(cell).getOrElse(return None)
    getCell(coord.col.toString, coord.row-1)
  }

  override def getLowerCell(cell: Cell): Option[Cell] = {
    var coord = getCoordinates(cell).getOrElse(return None)
    getCell(coord.col.toString, coord.row+1)
  }

  override def setCell(row: String, col: Int, value: String): Boolean = {
    val c = getCell(row.toUpperCase(),col)
    if (c.isDefined) {
      c.get.setValue(value.toUpperCase())
      true
    } else false
  }

  override def toString: String = {
    var board: String = "|"
    val r = 65 until 65+size
    r.foreach(col => board += col.toChar + "|")
    board += "\n_______________________________\n"
    grid.foreach(row => {row._2.foreach(col => board += "|" + col._2.getValue)
                         board += "| " + row._1 + "\n"})
    board += "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n"
    board
  }

  override def getSize: Int = {
    size
  }

  def getCoordinates(cell: Cell): Option[Coordinate] = {
    grid.foreach(y => {
      val revMap: mutable.Map[Cell, String] = y._2 map {_.swap}
      if (revMap.contains(cell)) {
        return Some(Coordinate(y._1, revMap(cell).charAt(0)))
      }
    })
    None
  }

  case class Coordinate(row: Int, col: Char)
}
