package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller
import de.htwg.se.scrabble.util.GameManagerStateObservable

trait GameManagerState extends GameManagerStateObservable {
  //var nextState: GameManager
  val controller = Controller
  def start()
  //def switchToNextState(state: GameManagerState)
  def switchToNextState()
}
