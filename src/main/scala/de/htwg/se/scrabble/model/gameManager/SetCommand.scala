package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller



//TODO: Commands hier einbauen und nur aufrufen lassen von TUI und GUI
class SetCommand(controller: Controller) extends Command {

  def execute(com:String) : Unit {

  }

//    override def doStep:   Unit = controller.grid = controller.grid.set(row, col, value)
//
//    override def undoStep: Unit = controller.grid = controller.grid.set(row, col, 0)
//
//    override def redoStep: Unit = controller.grid = controller.grid.set(row, col, value)


//  override def doStep:   Unit = controller.grid = controller.grid.set(row, col, value)
//  override def undoStep: Unit = controller.grid = controller.grid.set(row, col, 0)
//  override def redoStep: Unit = controller.grid = controller.grid.set(row, col, value)
}

