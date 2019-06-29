package de.htwg.se.scrabble.model.processWord

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.model.field.Cell

import scala.collection.SortedMap
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

  def validPlacement(word:String, start:Cell): Option[ListMap[Cell, String]] = {
    var placementMap: ListMap[Cell, String] = ListMap.empty[Cell, String]
    matches = List.empty[String]
    var currCell: Cell = start

    for (c <- word.toUpperCase) {
      if (currCell.isEmpty) {
        placementMap += (currCell -> c.toString)
      } else if (currCell.getValue == c.toString) {
        placementMap += (currCell -> c.toString)
        matches + c.toString
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
        return false
      }
      allCards diff List(c.toString)
    }
    true
  }
}
