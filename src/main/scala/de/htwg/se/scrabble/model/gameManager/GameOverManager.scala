package de.htwg.se.scrabble.model.gameManager

case class GameOverManager() extends GameManager {
  override var status: String = _

  override def getStatus: Boolean = ???

  override def statusToString: String = ???
}
