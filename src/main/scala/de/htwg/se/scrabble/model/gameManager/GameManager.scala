package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.util.Observable

trait GameManager extends Observable {
  var status: String
  def getStatus: Boolean
  def statusToString: String
}
