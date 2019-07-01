package de.htwg.se.scrabble.controller.controllerBaseImpl

import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.model.PlayerInterface
import de.htwg.se.scrabble.util.Command

class NextCommand(nextPlayer: Option[PlayerInterface], activePlayer: Option[PlayerInterface], controller: ControllerInterface) extends Command {
  override def doStep:   Unit = {
    nextPlayer.get.grantActionPermit()
    nextPlayer.get.grantSwitchedHand()
    controller.activePlayer = nextPlayer
  }
  override def undoStep: Unit = {
    nextPlayer.get.revokeActionPermit()
    nextPlayer.get.revokeSwitchedHand()
    controller.activePlayer = activePlayer
  }
  override def redoStep: Unit = {
    doStep
  }
}

