package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.util.GameManagerStateObservable

trait GameManager extends GameManagerStateObservable {
  def start()
  def switchToNextState()
  override def toString: String
}
