package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.{Controller, GameStatus}

case class GameOverManager(controller:Controller) extends GameManager {
  this.add(controller)
  controller.roundManager = this

  controller.gameStatus = GameStatus.GAME_OVER
  notifyObservers

  override def switchToNextState(state: GameManager): Unit = controller.roundManager = state
  override def switchToNextState: Unit = controller.roundManager = this
}
