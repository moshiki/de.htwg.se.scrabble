package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.model.{FieldTemplate}
import de.htwg.se.scrabble.util.Command

// UNNÖTIGE KLASSE!!!!!
class SolveCommand(controller: Controller) extends Command {

  var memento: FieldTemplate = controller.grid

  // succes checkt immer ob ganzes feld gelöst ist für das ende, was wir nicht benötigen
  override def doStep: Unit = {
//    memento = controller.grid         // feld
//    memoplayer = controller.players   // Spieler
//    memocards = controller.stack      // Carten Stapel

//    val (success, newgrid) = new Solver(controller.grid).solve                              setzen der felde und werte
//    if (success) controller.gameStatus = SOLVED else controller.gameStatus= NOT_SOLVABLE    bedingung prüfen für legen
//    controller.grid = newgrid                                                               austausch
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