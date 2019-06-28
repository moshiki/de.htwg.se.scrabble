package de.htwg.se.scrabble.controller.controllerBaseImpl

import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.model.player.Player
import de.htwg.se.scrabble.util.Command

class SetCommand(x: String, y: Int, value: String, activePlayer: Option[Player], controller: ControllerInterface) extends Command {
  override def doStep:   Unit = {
    controller.field.setCell(x, y, value)
    controller.activePlayer = activePlayer
  }
  override def undoStep: Unit = {
    controller.field.setCell(x, y, "_")
    controller.activePlayer = activePlayer
  }
  override def redoStep: Unit = {
    controller.field.setCell(x, y, value)
    controller.activePlayer = activePlayer
  }
}

