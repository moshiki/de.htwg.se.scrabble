package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.{Controller, GameStatus}

case class PreSetupManager(controller:Controller) extends GameManager {
  override var status: String = _
  override var nextState: GameManager = SetupManager(controller)

  switchToNextState

  override def getStatus: Boolean = ???

  override def statusToString: String = ???

  override def switchToNextState: Unit = controller.roundManager = nextState
}
