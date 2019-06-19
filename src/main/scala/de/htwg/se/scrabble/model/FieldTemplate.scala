package de.htwg.se.scrabble.model

trait FieldTemplate {

  def getCell(row: Integer, col: String): Cell //return cell for the coordinates

  def setCell(row: Integer, col: String, value: String): Boolean

  def toString: String //return field as grid

  def getRows: String //return field as concatenation of rows

  def getCols: String //return filed as concatenation of columns

}
