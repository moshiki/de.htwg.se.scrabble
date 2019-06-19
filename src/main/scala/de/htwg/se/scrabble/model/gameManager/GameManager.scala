package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.util.GameManagerStateObserver

trait GameManager extends GameManagerStateObserver {
  //var nextState: GameManager
  def switchToNextState
}
