package de.htwg.se.scrabble.controller.controllerBaseImpl.SetWordStrategy

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}
import de.htwg.se.scrabble.model.field.Cell
import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer

class SetWordVertical(controller:ControllerInterface) extends SetWordStrategy(controller:ControllerInterface) {
  override def setWord(word: String, cell: Cell, x: String, y: Int): Boolean = {
    if (x.charAt(0) - 65 + word.length > controller.field.getSize + 1) {
      controller.gameStatus(GameStatus.TOOLONG)
      return false
    }
    val placementMap = validPlacement(word, cell).getOrElse({controller.gameStatus(GameStatus.PLACEMENT); return false})
    if (validHand(word, placementMap, controller.activePlayer.get.getHand)) {
      val surroundingWords = validSurrounding(placementMap)
      if (surroundingWords.isDefined) controller.set(placementMap, surroundingWords.get)
      return true
    }
    false
  }

  def validPlacement(word:String, start:Cell): Option[ListMap[Cell, String]] = {
    val matches = ListBuffer.empty[String]
    var placementMap = ListMap.empty[Cell, String]
    var currCell: Cell = start

    for (c <- word.toUpperCase) {
      if (currCell.isEmpty) {
        placementMap += (currCell -> c.toString)
      } else if (currCell.getValue == c.toString) {
        matches += c.toString //::: matches
      } else {
        return None
      }
      currCell = controller.field.getLowerCell(currCell).getOrElse(return None)
    }
    if (controller.firstDraw) {
      if (!placementMap.keys.toList.contains(controller.field.getStarCell.getOrElse(return None))) return None
    }
    if (matches.nonEmpty || controller.firstDraw) {
      Some(placementMap)
    } else None
  }

  def validSurrounding(placementMap: ListMap[Cell, String]): Option[List[String]] = {
    val head: (Cell, String) = placementMap.toList.head
    var upCell: Option[Cell] = controller.field.getUpperCell(head._1)
    var lowCell: Option[Cell] = Some(head._1)
    val lb = ListBuffer[String]()
    val sb = new StringBuilder()
    val encounteredWords = ListBuffer[String]()

    //check upper cells
    while (upCell.isDefined && !upCell.get.isEmpty) {
      lb.prepend(upCell.get.getValue)
      upCell = controller.field.getPrevCell(upCell.get)
    }
    //check lower cells
    while (lowCell.isDefined && !lowCell.get.isEmpty || placementMap.keys.toList.contains(lowCell.get)) {
      lb.append(if (lowCell.get.isEmpty) placementMap(lowCell.get) else lowCell.get.getValue)
      lowCell = controller.field.getLowerCell(lowCell.get)
    }
    sb.appendAll(lb.map(s => s.charAt(0)))
    if (!controller.getDict.contains(sb.toString())) {controller.gameStatus(GameStatus.PLACEMENT); return None}
    encounteredWords += sb.toString()

    for (p <- placementMap) { // check previous and following cells for each cell to be set
      val currCell: Cell = p._1
      var prevCell: Option[Cell] = controller.field.getPrevCell(currCell)
      var nextCell: Option[Cell] = controller.field.getNextCell(currCell)
      lb.clear()
      lb += p._2
      sb.clear()

      // previous cells
      while (prevCell.isDefined && !prevCell.get.isEmpty) {
        lb.prepend(prevCell.get.getValue)
        prevCell = controller.field.getPrevCell(prevCell.get)
      }
      // following cells
      while (nextCell.isDefined && !nextCell.get.isEmpty) {
        lb.append(nextCell.get.getValue)
        nextCell = controller.field.getNextCell(nextCell.get)
      }
      sb.appendAll(lb.map(s => s.charAt(0)))
      if (sb.length > 1) {
        if (controller.getDict.contains(sb.toString())) {
          encounteredWords += sb.toString()
        } else {
          controller.gameStatus(GameStatus.PLACEMENT)
          return None
        }
      }
    }
    Some(encounteredWords.toList)
  }


}
