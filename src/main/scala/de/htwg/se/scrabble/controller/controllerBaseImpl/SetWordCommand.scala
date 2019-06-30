package de.htwg.se.scrabble.controller.controllerBaseImpl

import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.model.PlayerInterface
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.model.field.Cell
import de.htwg.se.scrabble.util.Command

import scala.collection.immutable.ListMap

class SetWordCommand(placementMap: ListMap[Cell, String], surroundingWords: List[String], activePlayer: Option[PlayerInterface],
                     firstDraw: Boolean, controller: ControllerInterface) extends Command {
  override def doStep:   Unit = {
    for (p <- placementMap) {
      val c = controller.field.getCoordinates(p._1).getOrElse(return)
      controller.field.setCell(c.col.toString, c.row, activePlayer.getOrElse(return).putCard(Card(p._2)).getOrElse(return).value)
    }
    activePlayer.get.addPoints(controller.evalPoints(surroundingWords))
    activePlayer.get.revokeActionPermit()
    controller.firstDraw = false
    controller.activePlayer = activePlayer
  }
  override def undoStep: Unit = {
    for (p <- placementMap) {
      val c = controller.field.getCoordinates(p._1).get
      if (controller.field.getCell(c.col.toString, c.row).get == controller.field.getStarCell.get) {
        controller.field.setCell(c.col.toString, c.row, "*")
      } else {
        controller.field.setCell(c.col.toString, c.row, "_")
      }
      controller.activePlayer.getOrElse(return).addToHand(Card(p._2))
    }
    activePlayer.get.subPoints(controller.evalPoints(surroundingWords))
    activePlayer.get.grantActionPermit()
    controller.firstDraw = firstDraw
    controller.activePlayer = activePlayer
  }
  override def redoStep: Unit = {
    if (activePlayer.getOrElse(return).actionPermitted) doStep
  }
}

