/*
package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.ControllerInterface

class PreSetupManager(controller:ControllerInterface) extends GameManager {
  this.add(controller)

  override def start(): Unit = {}
  //override def switchToNextState(state: GameManagerState): Unit = controller.roundManager = state
  override def switchToNextState(): Unit = {
    controller.roundManager(new SetupManager(controller))
    controller.roundManager.start()
  }
  override def toString = "PreSetupManagerState"
}
*/
