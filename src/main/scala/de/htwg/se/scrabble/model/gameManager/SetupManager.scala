package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller
import de.htwg.se.scrabble.model.player.Player

case class SetupManager(controller:Controller) extends GameManager {
  override var status: String = _
  override var nextState: GameManager = RoundManager(controller)

  controller.players.put(Player("A", "human being"))
  controller.players.put(Player("B", "human being"))

  notifyObservers
  switchToNextState

  override def getStatus: Boolean = ???

  override def statusToString: String = ???

  override def switchToNextState: Unit = controller.roundManager = nextState
}
