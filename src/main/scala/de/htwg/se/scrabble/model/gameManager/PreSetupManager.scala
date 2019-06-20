package de.htwg.se.scrabble.model.gameManager

class PreSetupManager extends GameManager {
  this.add(controller)
  controller.roundManager = this

  override def switchToNextState(state: GameManager): Unit = controller.roundManager = state
  override def switchToNextState: Unit = controller.roundManager = new SetupManager
}
