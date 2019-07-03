package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}

class GameOverManager(controller:ControllerInterface) extends GameManager {
  this.add(controller)

  override def start(): Unit = {
    //controller.roundManager = this
    controller.gameStatus(GameStatus.GAME_OVER)
    notifyObservers
  }
  //override def switchToNextState(state: GameManagerState): Unit = controller.roundManager = state
  override def switchToNextState(): Unit = {
    controller.roundManager(this)
    controller.roundManager.start()
  }
  override def toString = "GameOverManagerState"
}
