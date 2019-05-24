package de.htwg.se.scrabble.model.gameManager

abstract case class GameManager() {
  var status: String
  def getStatus: Boolean
  def statusToString: String
}
