package de.htwg.se.scrabble.controller.controllerBaseImpl.SetWordStrategy

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.model.field.Cell
import scala.collection.immutable.ListMap

class SetWordHorizontal(controller:ControllerInterface) extends SetWordStrategy {
  var matches = List.empty[String]

  override def setWord(word: String, cell: Cell, x: String, y: Int): Boolean = {
    if (x.charAt(0) - 65 + word.length > controller.field.getSize + 1) {
      controller.gameStatus = GameStatus.TOOLONG
      return false
    }
    var placementMap = validPlacement(word, cell).getOrElse({controller.gameStatus = GameStatus.PLACEMENT; return false})
    if (validHand(word, controller.activePlayer.get.getHand, matches)) {
      controller.set(placementMap)
    }
    true
  }

  def validPlacement(word:String, start:Cell): Option[ListMap[Cell, String]] = {
    var placementMap: ListMap[Cell, String] = ListMap.empty[Cell, String]
    matches = List.empty[String]
    var currCell: Cell = start

    for (c <- word.toUpperCase) {
      if (currCell.isEmpty) {
        placementMap += (currCell -> c.toString)
      } else if (currCell.getValue == c.toString) {
        matches = c.toString :: matches
      } else {
        return None
      }
      currCell = controller.field.getNextCell(currCell).getOrElse(return None)
    }
    Some(placementMap)
  }

  def validHand(word:String, hand:List[Card], usableCards:List[String]): Boolean = {
    var allCards: List[String] = hand.map(s => s.value)
    allCards ++= usableCards
    for (c <- word) {
      if (!allCards.contains(c.toString)) {
        controller.gameStatus = GameStatus.NOHANDCARD
        return false
      }
      allCards diff List(c.toString)
    }
    true
  }
}
