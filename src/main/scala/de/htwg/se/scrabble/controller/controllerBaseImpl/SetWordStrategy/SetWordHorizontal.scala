package de.htwg.se.scrabble.controller.controllerBaseImpl.SetWordStrategy

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}
import de.htwg.se.scrabble.model.field.Cell
import de.htwg.se.scrabble.model.FieldInterface
import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer

class SetWordHorizontal(controller:ControllerInterface) extends SetWordStrategy(controller:ControllerInterface) {
  var matches = List.empty[String]

  override def setWord(word: String, cell: Cell, x: String, y: Int): Boolean = {
    if (x.charAt(0) - 65 + word.length > controller.field.getSize + 1) {
      controller.gameStatus(GameStatus.TOOLONG)
      return false
    }
    val placementMap = validPlacement(word, cell).getOrElse({controller.gameStatus(GameStatus.PLACEMENT); return false})
    if (validHand(word, controller.activePlayer.get.getHand, matches)) {
      val surroundingWords = validSurrounding(placementMap)
      if (surroundingWords.isDefined) controller.set(placementMap, surroundingWords.get)
      return true
    }
    false
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
    if (controller.firstDraw) {
      if (!placementMap.keys.toList.contains(controller.field.getStarCell.getOrElse(return None))) return None
      controller.firstDraw(false)
    }
    Some(placementMap)
  }

  def validSurrounding(placementMap: ListMap[Cell, String]): Option[List[String]] = {
    val head: (Cell, String) = placementMap.toList.head
    var prevCell: Option[Cell] = controller.field.getPrevCell(head._1)
    var nextCell: Option[Cell] = Some(head._1)
    val lb = ListBuffer[String]()
    val sb = new StringBuilder()
    val encounteredWords = ListBuffer[String]()

    //check previous cells
    while (prevCell.isDefined && !prevCell.get.isEmpty) {
      lb.prepend(prevCell.get.getValue)
      prevCell = controller.field.getPrevCell(prevCell.get)
    }
    //check following cells
    while (nextCell.isDefined && (!nextCell.get.isEmpty || placementMap.keys.toList.contains(nextCell.get))) {
      lb.append(if (nextCell.get.isEmpty) placementMap(nextCell.get) else nextCell.get.getValue)
      nextCell = controller.field.getNextCell(nextCell.get)
    }
    sb.appendAll(lb.map(s => s.charAt(0)))
    if (!controller.getDict.contains(sb.toString())) {controller.gameStatus(GameStatus.PLACEMENT); return None}
    encounteredWords += sb.toString()

    for (p <- placementMap) { // check upper and lower cells for each cell to be set
      val currCell: Cell = p._1
      var upCell: Option[Cell] = controller.field.getUpperCell(currCell)
      var lowCell: Option[Cell] = controller.field.getLowerCell(currCell)
      lb.clear()
      lb += p._2
      sb.clear()

      // upper cells
      while (upCell.isDefined && !upCell.get.isEmpty) {
        lb.prepend(upCell.get.getValue)
        upCell = controller.field.getUpperCell(upCell.get)
      }
      // lower cells
      while (lowCell.isDefined && !lowCell.get.isEmpty) {
        lb.append(lowCell.get.getValue)
        lowCell = controller.field.getLowerCell(lowCell.get)
      }
      sb.appendAll(lb.map(s => s.charAt(0)))
      if (sb.length > 1) {
        if (controller.getDict.contains(sb.toString())) {
          encounteredWords += sb.toString()
        } else {
          controller.gameStatus(GameStatus.PLACEMENT)
          None
        }
      }
    }
    Some(encounteredWords.toList)
  }
}
