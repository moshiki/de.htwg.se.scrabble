package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller

case class RoundManager(controller:Controller) extends GameManager {
  override var status: String = _
  override var nextState: GameManager = this

  fillHand

  notifyObservers
  switchToNextState

  override def getStatus: Boolean = ???
  override def statusToString: String = ???

  def fillHand = {
    for (player <- controller.players.getList) {
      while (player.getNrCardsInHand < 7) {
        if (!controller.stack.isEmpty) {
          player.addToHand(controller.stack.getCard.get)
        }
      }
    }
  }

  override def switchToNextState: Unit = controller.roundManager = nextState

}
