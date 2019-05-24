package de.htwg.se.scrabble.model

abstract class FieldTemplate {

  def getCell(x:String, y: Integer): Cell //return cell for the coordinates

  def setCell(x:String, y: Integer, value: String): Boolean

  def toString: String //return field as grid

  def getRows: String //return field as concatenation of rows

  def getCols: String //return filed as concatenation of columns

}
