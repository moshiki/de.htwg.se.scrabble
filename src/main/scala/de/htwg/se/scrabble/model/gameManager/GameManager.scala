package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.util.GameManagerStateObserver

trait GameManager extends GameManagerStateObserver {
  var status: String
  //var nextState: GameManager
  def getStatus: Boolean
  def statusToString: String
  def switchToNextState
}
