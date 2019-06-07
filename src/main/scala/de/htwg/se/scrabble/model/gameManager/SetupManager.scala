package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller
import de.htwg.se.scrabble.model.RegularField
import de.htwg.se.scrabble.model.cards.RegularCardStack
import de.htwg.se.scrabble.model.player.Player

case class SetupManager(controller:Controller) extends GameManager {
  override var status: String = _
  override var nextState: GameManager = RoundManager(controller)

  //controller.stack = RegularCardStack
  //controller.field = RegularField(15)
  controller.players.put(Player("player a", "1"))
  controller.players.put(Player("player b", "2"))

  switchToNextState


  override def getStatus: Boolean = ???

  override def statusToString: String = ???

  override def switchToNextState: Unit = controller.roundManager = nextState
}
