package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.util.Observable

trait GameManager extends Observable {
  switchToNextState
  var status: String
  var nextState: GameManager
  def getStatus: Boolean
  def statusToString: String
  def switchToNextState
}
