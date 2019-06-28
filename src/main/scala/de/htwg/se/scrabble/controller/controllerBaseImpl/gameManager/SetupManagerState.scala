package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}
import de.htwg.se.scrabble.model.player.Player

class SetupManagerState(controller:ControllerInterface) extends GameManagerState {
  //override var nextState: GameManager = RoundManager(controller)
  this.add(controller)

  override def start(): Unit = {
    //controller.roundManager = this
    controller.players.put(Player("A", "human"))
    controller.players.put(Player("B", "human"))
    controller.activePlayer = controller.players.get("A")
    controller.gameStatus = GameStatus.TWOP
    notifyObservers
    switchToNextState()
  }

  //override def switchToNextState(state: GameManagerState): Unit = controller.roundManager = state
  override def switchToNextState(): Unit = {
    controller.roundManager = new RoundManagerState(controller)
    controller.roundManager.start()
  }
}
