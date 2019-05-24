package de.htwg.se.scrabble.model

trait FieldTemplate {

  def getCell(x:String, y: Integer): Cell //return cell for the coordinates

  def toString: String //return field as grid

  def getRows: String //return field as concatenation of rows

  def getCols: String //return filed as concatenation of columns

}
