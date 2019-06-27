package de.htwg.se.scrabble.model.processWord

import de.htwg.se.scrabble.controller.GameStatus
import de.htwg.se.scrabble.model.field.Cell

import scala.collection.SortedMap

class SetWordHorizontal extends SetWordStrategy {
  override def setWord(word: String, cell: Cell, x: String, y: Int): Boolean = {
    if (x.charAt(0) - 65 + word.length > controller.field.getSize + 1) {
      controller.gameStatus = GameStatus.TOOLONG
      return false
    }

    var currCell: Cell = cell

    for (c <- word) {

      if (currCell.isEmpty)
      // TODO: Nach rechts Cellenbuchstaben mit eingabe prüfenn, je nach index
    }
    true
  }

  def validatePlacement(word:String, startCell: Cell) = {
    val placementMap = SortedMap[Cell, String]()
    var matches: Int = 0
    var currCell: Cell = startCell

    for (c <- word) {
      if (currCell.isEmpty) {
        placementMap ++ Map(currCell, c)
      } else if (currCell.getValue == c) {
        placementMap ++ Map(currCell, c)
        matches += 1
      } else {
        return None
      }

      currCell =
    }
  }
}
