package de.htwg.se.scrabble.controller.controllerBaseImpl

import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.model.player.Player
import de.htwg.se.scrabble.util.Command

class SetWordCommand(word: String, x: String, y: Int, activePlayer: Option[Player], controller: ControllerInterface) extends Command {
  override def doStep:   Unit = {
    //controller.field.setCell(x, y)
    controller.activePlayer = activePlayer
  }
  override def undoStep: Unit = {
    controller.field.setCell(x, y, "_")
    controller.activePlayer = activePlayer
  }
  override def redoStep: Unit = {
    //controller.field.setCell(x, y)
    controller.activePlayer = activePlayer
  }
}

