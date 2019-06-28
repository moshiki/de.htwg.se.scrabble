package de.htwg.se.scrabble.model.processWord

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.model.field.Cell


class SetWordHorizontal(controller:ControllerInterface) extends SetWordStrategy {
  var matches = List.empty[String]

  override def setWord(word: String, cell: Cell, x: String, y: Int): Boolean = {
    if (x.charAt(0) - 65 + word.length > controller.field.getSize + 1) {
      controller.gameStatus = GameStatus.TOOLONG
      return false
    }
    var placementMap = validPlacement(word, cell).getOrElse({controller.gameStatus = GameStatus.PLACEMENT; return false})
    if (validHand(word, controller.activePlayer.get.getHand, matches)) {
      placementMap.foreach(p => controller.set(p._1,
        if (matches.contains(p._2)) {
          matches = matches diff List(p._2)
          p._2
        } else {
          controller.activePlayer.get.putCard(Card(p._2)).get.value
        }))
    }

    true
  }

  def validPlacement(word:String, start:Cell): Option[Map[Cell, String]] = {
    val placementMap: Map[Cell, String] = Map.empty[Cell, String]
    matches = List.empty[String]
    var currCell: Cell = start

    for (c <- word.toUpperCase) {
      if (currCell.isEmpty) {
        placementMap + (currCell -> c)
      } else if (currCell.getValue == c.toString) {
        placementMap + (currCell -> c)
        matches + c.toString
      } else {
        return None
      }
      currCell = controller.field.getNextCell(currCell).getOrElse(return None)
    }
    Some(placementMap)
  }

  def validHand(word:String, hand:List[Card], usableCards:List[String]): Boolean = {
    val allCards = hand.map(s => s.value) :: usableCards
    for (c <- word) {
      if (!allCards.contains(c.toString)) {
        return false
      }
      allCards diff List(c.toString)
    }
    true
  }
}
