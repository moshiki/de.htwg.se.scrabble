package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.GameStatus

class RoundManagerState extends GameManagerState {
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
    controller.roundManager = new GameOverManagerState
    controller.roundManager.start()
  }
}
