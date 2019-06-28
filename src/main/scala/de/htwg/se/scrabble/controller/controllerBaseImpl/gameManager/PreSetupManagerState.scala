package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.ControllerInterface

class PreSetupManagerState(controller:ControllerInterface) extends GameManagerState {
  this.add(controller)

  override def start(): Unit = {}
  //override def switchToNextState(state: GameManagerState): Unit = controller.roundManager = state
  override def switchToNextState(): Unit = {
    controller.roundManager = new SetupManagerState(controller)
    controller.roundManager.start()
  }
}
