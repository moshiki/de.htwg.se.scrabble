package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.ControllerInterface

class RoundManager(controller:ControllerInterface) extends GameManager {
  this.add(controller)

  override def start(): Unit = {
    controller.fillHand()

  }
  override def switchToNextState(): Unit = {
    controller.roundManager(new GameOverManager(controller))
    controller.roundManager.start()
  }
  override def toString = "RoundManagerState"
}
