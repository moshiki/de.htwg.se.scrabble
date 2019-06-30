package de.htwg.se.scrabble.controller.controllerBaseImpl.SetWordStrategy

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.model.field.Cell
import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer

class SetWordHorizontal(controller:ControllerInterface) extends SetWordStrategy {
  var matches = List.empty[String]

  override def setWord(word: String, cell: Cell, x: String, y: Int): Boolean = {
    if (x.charAt(0) - 65 + word.length > controller.field.getSize + 1) {
      controller.gameStatus = GameStatus.TOOLONG
      return false
    }
    val placementMap = validPlacement(word, cell).getOrElse({controller.gameStatus = GameStatus.PLACEMENT; return false})
    if (validHand(word, controller.activePlayer.get.getHand, matches)) {
      if (validSurrounding(placementMap)) controller.set(placementMap)
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

  def validSurrounding(placementMap: ListMap[Cell, String]): Boolean = {
    val head: (Cell, String) = placementMap.toList.head
    val tail: (Cell, String) = placementMap.toList.last
    var prevCell: Option[Cell] = controller.field.getPrevCell(head._1)
    var nextCell: Option[Cell] = controller.field.getNextCell(tail._1)
    val lb = ListBuffer[String]() ++= placementMap.values
    val sb = new StringBuilder()

    if (prevCell.isDefined) { //check previous cells
      while (!prevCell.get.isEmpty) {
        lb.prepend(prevCell.get.getValue)
        prevCell = controller.field.getPrevCell(prevCell.get)
      }
    }
    if (nextCell.isDefined) { //check following cells
      while (!nextCell.get.isEmpty) {
        lb.append(nextCell.get.getValue)
        nextCell = controller.field.getNextCell(nextCell.get)
      }
    }
    sb.appendAll(lb.map(s => s.charAt(0)))
    if (!controller.getDict.contains(sb.toString())) return false

    for (p <- placementMap) { // check upper and lower cells for each cell to be set
      val currCell: Cell = p._1
      var upCell: Option[Cell] = controller.field.getUpperCell(currCell)
      var lowCell: Option[Cell] = controller.field.getLowerCell(currCell)
      lb.clear()
      lb += currCell.getValue
      sb.clear()

      if (upCell.isDefined) { // upper cells
        while (!upCell.get.isEmpty) {
          lb.prepend(upCell.get.getValue)
          upCell = controller.field.getUpperCell(upCell.get)
        }
      }
      if (lowCell.isDefined) { // lower cells
        while (!lowCell.get.isEmpty) {
          lb.append(lowCell.get.getValue)
          lowCell = controller.field.getLowerCell(lowCell.get)
        }
      }
      sb.appendAll(lb.map(s => s.charAt(0)))
      if (!controller.getDict.contains(sb.toString())) return false
    }
    true
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
