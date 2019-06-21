package de.htwg.se.scrabble.model.processWord

import de.htwg.se.scrabble.controller.Controller
import de.htwg.se.scrabble.model.Cell

trait SetWordStrategy {
  val controller = Controller
  def setWord(word: String, cell: Cell, x:String, y: Int): Boolean
}
