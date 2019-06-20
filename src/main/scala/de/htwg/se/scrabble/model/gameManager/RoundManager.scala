package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.{Controller, GameStatus}
import de.htwg.se.scrabble.model.player.Player

case class RoundManager(controller:Controller) extends GameManager {
  this.add(controller)
  controller.roundManager = this

  fillHand()
  controller.gameStatus = GameStatus.FILLHAND
  notifyObservers

  controller.activePlayer = inactivePlayer


  def fillHand(): Unit = {
    for (player <- controller.players.getList) {
      while (!controller.stack.isEmpty && player.getNrCardsInHand < 7) {
        player.addToHand(controller.stack.getCard.get)
      }
    }
  }

  def inactivePlayer: Player = {
    if (controller.activePlayer == controller.players.get("A").get) {
      controller.players.get("B").get
    } else {
      controller.players.get("A").get
    }
  }

  override def switchToNextState(state: GameManager): Unit = controller.roundManager = state
  override def switchToNextState: Unit = controller.roundManager = GameOverManager(this.controller)
}
