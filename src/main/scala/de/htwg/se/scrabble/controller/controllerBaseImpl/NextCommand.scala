package de.htwg.se.scrabble.controller.controllerBaseImpl

import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.model.player.Player
import de.htwg.se.scrabble.util.Command

class NextCommand(nextPlayer: Option[Player], activePlayer: Option[Player], controller: ControllerInterface) extends Command {
  override def doStep:   Unit = {
    nextPlayer.get.grantActionPermit()
    controller.activePlayer = nextPlayer
  }
  override def undoStep: Unit = {
    nextPlayer.get.revokeActionPermit()
    controller.activePlayer = activePlayer
  }
  override def redoStep: Unit = {
    doStep
  }
}

