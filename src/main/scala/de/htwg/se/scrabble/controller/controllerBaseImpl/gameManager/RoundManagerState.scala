package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.ControllerInterface

class RoundManagerState(controller:ControllerInterface) extends GameManagerState {
  this.add(controller)

  override def start(): Unit = {
    controller.fillHand()
  }


  override def switchToNextState(): Unit = {
    controller.roundManager = new GameOverManagerState(controller)
    controller.roundManager.start()
  }
}
