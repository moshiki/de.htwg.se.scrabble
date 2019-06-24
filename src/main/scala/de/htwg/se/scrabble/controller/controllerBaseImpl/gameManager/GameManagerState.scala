package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller
import de.htwg.se.scrabble.util.GameManagerStateObservable

trait GameManagerState extends GameManagerStateObservable {
  //var nextState: GameManager
  val controller = Controller
  def start()
  //def switchToNextState(state: GameManagerState)
  def switchToNextState()
}
