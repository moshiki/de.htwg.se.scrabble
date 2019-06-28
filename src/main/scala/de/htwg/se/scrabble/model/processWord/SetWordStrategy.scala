package de.htwg.se.scrabble.model.processWord

import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller
import de.htwg.se.scrabble.model.field.Cell

trait SetWordStrategy {
  def setWord(word: String, cell: Cell, x:String, y: Int): Boolean
}
