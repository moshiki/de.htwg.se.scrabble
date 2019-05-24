package de.htwg.se.scrabble.model.gameManager

trait GameManager {
  var status: String
  def getStatus: Boolean
  def statusToString: String
}
