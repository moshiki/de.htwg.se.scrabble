package de.htwg.se.scrabble.controller.controllerBaseImpl.SetWordStrategy

import de.htwg.se.scrabble.model.field.Cell

trait SetWordStrategy {
  def setWord(word: String, cell: Cell, x:String, y: Int): Boolean
}
