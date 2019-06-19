package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.util.Command

class SetCommand(x: String, y: Int, value: String, controller: Controller) extends Command {
  override def doStep:   Unit = controller.field.setCell(x, y, value)
  override def undoStep: Unit = controller.field.setCell(x, y, "_")
  override def redoStep: Unit = controller.field.setCell(x, y, value)
}

