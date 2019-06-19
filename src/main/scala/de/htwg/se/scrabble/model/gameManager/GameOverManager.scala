package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.{Controller, GameStatus}

case class GameOverManager(controller:Controller) extends GameManager {
  override var status: String = _
  //override var nextState: GameManager = this
  this.add(controller)

  controller.gameStatus = GameStatus.GAME_OVER
  notifyObservers
  switchToNextState

  override def getStatus: Boolean = ???

  override def statusToString: String = ???

  override def switchToNextState: Unit = controller.roundManager = this
}
