package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.util.Command

class SetCommand(row: Int, col: String, value: String, controller: Controller) extends Command {
  override def doStep:   Unit = ???
  override def undoStep: Unit = ???
  override def redoStep: Unit = ???
}

