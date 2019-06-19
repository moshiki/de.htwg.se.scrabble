package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.{Controller, GameStatus}
import de.htwg.se.scrabble.model.player.Player

case class SetupManager(controller:Controller) extends GameManager {
  //override var nextState: GameManager = RoundManager(controller)
  this.add(controller)
  controller.roundManager = this

  controller.players.put(Player("A", "human being"))
  controller.players.put(Player("B", "human being"))
  controller.gameStatus = GameStatus.TWOP

  notifyObservers
  switchToNextState

  override def switchToNextState: Unit = controller.roundManager = RoundManager(this.controller)
}
