package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller
import de.htwg.se.scrabble.model.player.Player

case class SetupManager(controller:Controller) extends GameManager {
  override var status: String = ""
  //override var nextState: GameManager = RoundManager(controller)
  this.add(controller)
  controller.roundManager = this

  controller.players.put(Player("A", "human being"))
  controller.players.put(Player("B", "human being"))
  status = "Added 2 human players"

  notifyObservers
  switchToNextState

  override def getStatus: Boolean = ???

  override def statusToString: String = status

  override def switchToNextState: Unit = controller.roundManager = RoundManager(this.controller)
}
