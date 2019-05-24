package de.htwg.se.scrabble.model

trait FieldTemplate {
  def toString: String //return field as grid

  def getRows: String //return field as concatenation of rows

  def getCols: String //return filed as concatenation of columns

}
