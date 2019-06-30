package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.util.GameManagerStateObservable

trait GameManagerState extends GameManagerStateObservable {
  def start()
  def switchToNextState()
}
