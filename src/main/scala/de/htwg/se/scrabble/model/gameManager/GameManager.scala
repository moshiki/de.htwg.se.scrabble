package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller
import de.htwg.se.scrabble.util.GameManagerStateObservable

trait GameManager extends GameManagerStateObservable {
  //var nextState: GameManager
  val controller = Controller
  def switchToNextState(state: GameManager)
  def switchToNextState
}
