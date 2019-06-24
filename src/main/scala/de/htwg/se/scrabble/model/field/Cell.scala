package de.htwg.se.scrabble.model.field

class Cell(letter: String) {
  private var value: String =
    if (isLetter(letter)) {letter}
    else "_"

  def getValue: String = value

  def setValue(value: String): Boolean = if (isLetter(value)) {this.value = value; true} else false

  def isLetter(str: String): Boolean = str.matches("[A-Z#*_]")

  def isEmpty: Boolean = if (value == "_") true else false
}
