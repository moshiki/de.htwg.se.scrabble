package de.htwg.se.scrabble.model.processWord

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}
import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller
import de.htwg.se.scrabble.model.ProcessWordInterface
import de.htwg.se.scrabble.model.field.Cell

//TODO in Controllerschicht packen, falls nicht alle Controller zugriffe beseitigt werden können oder es in den Round Manager aufgenommn wurde
case class ProcessWord(controller:ControllerInterface) extends ProcessWordInterface {

  def setWord(parameters: Array[String]): Unit = {
    if (parameters.length != 4) return

    val x: String = parameters(1).charAt(0).toString.toUpperCase()
    val y: Int = parameters(1).substring(1).toInt

    val alignment: SetWordStrategy = {
      if (parameters(2).matches("[-]")) new SetWordHorizontal(controller)
      else if (parameters(2).matches("[|]")) new SetWordVertical(controller)
      else return
    }
    val word: String = if (parameters(3).matches("[A-Za-z#]+")) parameters(3).toUpperCase() else return
    val cell: Cell = if (controller.field.getCell(x, y).isDefined) controller.field.getCell(x, y).get else return

    if (checkWord(word)) {
      alignment.setWord(word, cell, x, y)


            // TODO: INSERT -> wird beim einsetzen min. ein anderer Bustabe genutzt? (oder Mittelfeld) (checkenn ob buchstabenn auch alle gleich sind oder _ oder *)

            // grid -> field
            //      controller.undoManager.doStep(new SetCommand(controller.grid, controller.players, controller.stack, controller))
            //      controller.notifyObservers
            /*} else {
              System.err.println("wrong input, pls type cmd like: set B5 - Hallo")*/

    } else {
      controller.gameStatus = GameStatus.ILLEGAL
    }
  }

  def error(): Unit = {
    //ToDO: Errorhandling (Info für User)" pls type cmd like: set B5 - Hallo"
  }

  def checkWord(word: String): Boolean = {
    if (controller.getDict.contains(word.toUpperCase())) {  // TODO Dictionary hier laden
      true
    }
    else {
      false
    }
  }
}
