package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller

case class RoundManager(controller:Controller) extends GameManager {
  override var status: String = ""
  //override var nextState: GameManager = this
  this.add(controller)
  controller.roundManager = this

  fillHand
  status = "Filled hands"
  notifyObservers

  //nextState = GameOverManager(this.controller)
  switchToNextState

  controller.roundManager = GameOverManager(this.controller)

  override def getStatus: Boolean = ???
  override def statusToString: String = status

  def fillHand = {
    for (player <- controller.players.getList) {
      while (player.getNrCardsInHand < 7) {
        if (!controller.stack.isEmpty) {
          player.addToHand(controller.stack.getCard.get)
        }
      }
    }
  }

  override def switchToNextState: Unit = controller.roundManager = this

}
