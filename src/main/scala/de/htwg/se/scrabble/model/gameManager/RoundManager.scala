package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.GameStatus

class RoundManager extends GameManager {
  this.add(controller)
  controller.roundManager = this

  fillHand()
  controller.gameStatus = GameStatus.FILLHAND
  notifyObservers

  def fillHand(): Unit = {
    for (player <- controller.players.getList) {
      while (!controller.stack.isEmpty && player.getNrCardsInHand < 7) {
        player.addToHand(controller.stack.getCard.get)
      }
    }
  }

  override def switchToNextState(state: GameManager): Unit = controller.roundManager = state
  override def switchToNextState: Unit = controller.roundManager = new GameOverManager
}
