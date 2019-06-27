package de.htwg.se.scrabble.model

import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller
import de.htwg.se.scrabble.model.field.Cell

trait FieldInterface {
  val controller = Controller

  def getCell(col: String, row: Int): Option[Cell]

  def getCoordinates(cell: Cell): Option[Coordinate]

  def getNextCell(cell: Cell): Option[Cell]

  def getPrevCell(cell: Cell): Option[Cell]

  def getUpperCell(cell: Cell): Option[Cell]

  def getLowerCell(cell: Cell): Option[Cell]

  def setCell(col: String, row: Int, value: String): Boolean

  def toString: String

  def getSize: Int

  case class Coordinate(row: Int, col: Char)
}
