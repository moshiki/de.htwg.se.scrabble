package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.model.{FieldTemplate}
import de.htwg.se.scrabble.util.Command

class SolveCommand(controller: Controller) extends Command {

  var memento: FieldTemplate = controller.grid

  override def doStep: Unit = {
    memento = controller.grid
    val (success, newgrid) = new Solver(controller.grid).solve
    if (success) controller.gameStatus = SOLVED else controller.gameStatus= NOT_SOLVABLE
    controller.grid = newgrid
  }

  override def undoStep: Unit = {
    val new_memento = controller.grid
    controller.grid = memento
    memento = new_memento
  }

  override def redoStep: Unit = {
    val new_memento = controller.grid
    controller.grid = memento
    memento = new_memento
  }
}