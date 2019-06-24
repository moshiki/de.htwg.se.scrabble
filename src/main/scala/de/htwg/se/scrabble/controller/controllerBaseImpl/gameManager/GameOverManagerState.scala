package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.GameStatus

class GameOverManagerState extends GameManagerState {
  this.add(controller)

  override def start(): Unit = {
    //controller.roundManager = this
    controller.gameStatus = GameStatus.GAME_OVER
    notifyObservers
  }
  //override def switchToNextState(state: GameManagerState): Unit = controller.roundManager = state
  override def switchToNextState(): Unit = {
    controller.roundManager = this
    controller.roundManager.start()
  }
}
