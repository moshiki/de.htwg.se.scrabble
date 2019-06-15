package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.model.FieldTemplate
import de.htwg.se.scrabble.model.cards.CardStackTemplate
import de.htwg.se.scrabble.model.player.PlayerList
import de.htwg.se.scrabble.util.Command

class SetCommand(grid:FieldTemplate, players: PlayerList, stack: CardStackTemplate, controller: Controller) extends Command {
  override def doStep:   Unit = controller.set(grid, players, stack)
  override def undoStep: Unit = controller.set(grid, players, stack)
  override def redoStep: Unit = controller.set(grid, players, stack)
}

