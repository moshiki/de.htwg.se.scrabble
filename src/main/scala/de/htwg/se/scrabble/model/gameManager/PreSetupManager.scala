package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.{Controller, GameStatus}

case class PreSetupManager(controller:Controller) extends GameManager {
  override var status: String = ""
  //override var nextState: GameManager = SetupManager(controller)
  this.add(controller)
  controller.roundManager = this

  override def getStatus: Boolean = ???

  override def statusToString: String = status

  override def switchToNextState: Unit = controller.roundManager = SetupManager(this.controller)
}
