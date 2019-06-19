package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller

case class PreSetupManager(controller:Controller) extends GameManager {
  //override var nextState: GameManager = SetupManager(controller)
  this.add(controller)
  controller.roundManager = this

  override def switchToNextState: Unit = controller.roundManager = SetupManager(this.controller)
}
