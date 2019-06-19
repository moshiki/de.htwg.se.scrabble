package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.util.Command

class SetCommand(row: Int, col: String, value: String, controller: Controller) extends Command {
  override def doStep:   Unit = controller.field.setCell(row, col, value)
  override def undoStep: Unit = controller.field.setCell(row, col, "_")
  override def redoStep: Unit = controller.field.setCell(row, col, value)
}

