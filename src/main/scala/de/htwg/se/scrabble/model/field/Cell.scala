package de.htwg.se.scrabble.model.field

import de.htwg.se.scrabble.model.CellInterface

class Cell(letter: String) extends CellInterface {
  private var value: String =
    if (isLetter(letter)) {letter}
    else "_"

  def getValue: String = value

  def setValue(value: String): Boolean = if (isLetter(value)) {this.value = value; true} else false

  def isLetter(str: String): Boolean = str.matches("[A-Z#*_]")

  def isEmpty: Boolean = if (value == "_" || value == "*") true else false
}

/*object Cell {
  import play.api.libs.json._
  implicit val regularFieldWrites = Json.writes[RegularField]
}*/
