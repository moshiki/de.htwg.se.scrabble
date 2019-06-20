package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.{Controller, GameStatus}

case class RoundManager(controller:Controller) extends GameManager {
  this.add(controller)
  controller.roundManager = this

  fillHand
  controller.gameStatus = GameStatus.FILLHAND
  notifyObservers

  switchToNextState

  def fillHand = {
    for (player <- controller.players.getList) {
      while (!controller.stack.isEmpty && player.getNrCardsInHand < 7) {
        player.addToHand(controller.stack.getCard.get)
      }
    }
  }

  override def switchToNextState(state: GameManager): Unit = controller.roundManager = state
  override def switchToNextState: Unit = controller.roundManager = GameOverManager(this.controller)
}
