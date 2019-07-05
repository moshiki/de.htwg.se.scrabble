package de.htwg.se.scrabble.model

trait CellInterface {
  def getValue: String

  def setValue(value: String): Boolean

  def isLetter(str: String): Boolean

  def isEmpty: Boolean


}
