package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.{Controller, GameStatus}

case class GameOverManager(controller:Controller) extends GameManager {
  //override var nextState: GameManager = this
  this.add(controller)
  controller.roundManager = this

  controller.gameStatus = GameStatus.GAME_OVER
  notifyObservers
  switchToNextState

  override def switchToNextState: Unit = controller.roundManager = this
}
