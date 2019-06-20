package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller

case class PreSetupManager(controller:Controller) extends GameManager {
  this.add(controller)
  //controller.roundManager = this

  override def switchToNextState(state: GameManager): Unit = controller.roundManager = state
  override def switchToNextState: Unit = controller.roundManager = SetupManager(this.controller)
}
