package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}

class RoundManagerState(controller:ControllerInterface) extends GameManagerState {
  this.add(controller)

  override def start(): Unit = {
    //controller.roundManager = this
    fillHand()
    controller.gameStatus = GameStatus.FILLHAND
    notifyObservers
  }

  def fillHand(): Unit = {
    for (player <- controller.players.getList) {
      while (!controller.stack.isEmpty && player.getNrCardsInHand < 7) {
        player.addToHand(controller.stack.getCard.get)
      }
    }
  }

  //override def switchToNextState(state: GameManagerState): Unit = controller.roundManager = state
  override def switchToNextState(): Unit = {
    controller.roundManager = new GameOverManagerState(controller)
    controller.roundManager.start()
  }
}
