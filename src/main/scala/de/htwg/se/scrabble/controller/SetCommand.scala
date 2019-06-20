package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.model.player.Player
import de.htwg.se.scrabble.util.Command

class SetCommand(x: String, y: Int, value: String, activePlayer: Option[Player]) extends Command {
  override def doStep:   Unit = {
    Controller.field.setCell(x, y, value)
    Controller.activePlayer = activePlayer
  }
  override def undoStep: Unit = {
    Controller.field.setCell(x, y, "_")
    Controller.activePlayer = activePlayer
  }
  override def redoStep: Unit = {
    Controller.field.setCell(x, y, value)
    Controller.activePlayer = activePlayer
  }
}

