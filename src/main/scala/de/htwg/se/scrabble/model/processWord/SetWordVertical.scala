package de.htwg.se.scrabble.model.processWord

import de.htwg.se.scrabble.controller.GameStatus
import de.htwg.se.scrabble.model.Cell

class SetWordVertical extends SetWordStrategy {
  override def setWord(word: String, cell: Cell, x: String, y: Int): Boolean = {
      if (x.toInt + word.length > controller.field.getSize + 1) {
        controller.gameStatus = GameStatus.TOOLONG
        return false
      }
      //        for (n <- 0 to word.length){
      //          cell
      //          // TODO: ^^ nur nach untnen
      //        }
      true
    }
    true
}
