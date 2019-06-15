package de.htwg.se.scrabble.util

//cmd-Pattern: Command Interface
trait Command {
  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit

//  def saveStep:Unit
}
