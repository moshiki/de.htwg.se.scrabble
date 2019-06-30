package de.htwg.se.scrabble.controller.controllerBaseImpl

import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.model.field.Cell
import de.htwg.se.scrabble.model.player.Player
import de.htwg.se.scrabble.util.Command

import scala.collection.immutable.ListMap

class SetWordCommand(placementMap: ListMap[Cell, String], surroundingWords: List[String], activePlayer: Option[Player], controller: ControllerInterface) extends Command {
  override def doStep:   Unit = {
    for (p <- placementMap) {
      val c = controller.field.getCoordinates(p._1).getOrElse(return)
      controller.field.setCell(c.col.toString, c.row, activePlayer.get.putCard(Card(p._2)).get.value)
    }
    activePlayer.get.addPoints(controller.evalPoints(surroundingWords))
    activePlayer.get.revokeActionPermit()
    controller.activePlayer = activePlayer
  }
  override def undoStep: Unit = {
    for (p <- placementMap) {
      val c = controller.field.getCoordinates(p._1).get
      controller.field.setCell(c.col.toString, c.row, "_")
      controller.activePlayer.get.addToHand(Card(p._2))
    }
    activePlayer.get.subPoints(controller.evalPoints(surroundingWords))
    activePlayer.get.grantActionPermit()
    controller.activePlayer = activePlayer
  }
  override def redoStep: Unit = {
    doStep
  }
}

