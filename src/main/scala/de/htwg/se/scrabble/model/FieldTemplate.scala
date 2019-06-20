package de.htwg.se.scrabble.model

import de.htwg.se.scrabble.controller.Controller

trait FieldTemplate {

  val controller = Controller

  def getCell(x: String, y: Int): Option[Cell] //return cell for the coordinates

  def setCell(x: String, y: Int, value: String): Boolean

  def toString: String //return field as grid

  def getRows: String //return field as concatenation of rows

  def getCols: String //return filed as concatenation of columns

  def getSize: Integer //returnnd the field size
}
