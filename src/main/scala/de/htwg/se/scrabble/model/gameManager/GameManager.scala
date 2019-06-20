package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.util.GameManagerStateObservable

trait GameManager extends GameManagerStateObservable {
  //var nextState: GameManager
  def switchToNextState(state: GameManager)
  def switchToNextState
}
