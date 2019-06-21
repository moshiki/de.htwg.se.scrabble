package de.htwg.se.scrabble.model.processWord

import de.htwg.se.scrabble.controller.{Controller, GameStatus}
import de.htwg.se.scrabble.model.Cell

object ProcessWord {

  val controller = Controller

  def setWord(parameters: Array[String]): Unit = {
    val x: String = parameters(1).charAt(0).toString
    val y: Int = parameters(1).substring(1).toInt

    val alignment: SetWordStrategy = {
      if (parameters(2).matches("[-]")) new SetWordHorizontal
      else if (parameters(2).matches("[-]")) new SetWordVertical
      else return
    }
    val word: String = if (parameters(3).matches("[A-Za-z#]+")) parameters(3) else return
    val cell: Cell = if (controller.field.getCell(x, y).isDefined) controller.field.getCell(x, y).get else return

            // => Prüfen auf gültige Zelle ist in field.getCell schon implementiert. Wenn ungültig wird der GameStatus im controller
            //    auf coordinates out of bound gesetzt (error message)
                   /*if (parameters.length == 4) {
                    if(((parameters(1).substring(0,0).toInt - 65) > controller.field.getSize) || // Startposition checken
                      (parameters(1).substring(1).toInt > controller.field.getSize )) {
                      System.err.println("wrong Position, pls type cmd like: set B5 - Hallo")
                      return
                    }*/


            // => siehe oben in val alignment: Strategy pattern: - horizontal, | vertikal, sonst abbrechen
                  /*if (!(parameters(2) == "-" || parameters(2) == "|")) { // check ob wirklich "|" oder "-" an 2. stelle steht
                    System.err.println("wrong direction modifier: '-' vertical, '|' horizontal")
                    return
                  }*/

    if (checkWord(word)) {
            // -> Ist es ein gültiges Wort? -> erledigt
            // if((parameters(4) kein wort aus dem wörterbuch) {Error Massage + Return}
      alignment.setWord(word, cell, x, y)
            // -> hab ich ins SetWordHorizontal strategy pattern verschoben
                /*if (parameters(1).substring(0, 0).toInt - 65 + parameters(4).length > controller.field.getSize + 1) {
                    System.err.println("Word is to long for the Field with the Start at " + parameters(1).toString)
                    return
                  }
                  for (n <- 0 to parameters(4).toString.length) {

                    controller.field.getCell(parameters(1).substring(1, 1) + n, parameters(1).substring(1, 1).toInt)
                    // TODO: Nach rechts Cellenbuchstaben mit eingabe prüfenn, je nach index

                  }
                }*/

            // -> hab ich ins SetWordVertical strategy pattern verschoben
              /*if (parameters(2) == "|") {
                if (parameters(1).substring(1, 1).toInt + parameters(4).length > controller.field.getSize + 1) {
                  System.err.println("Word is to long for the Field with the Start at " + parameters(1))
                  return
                }
                //        for (n <- 0 to parameters(4).toString.length){
                //          controller.grid.getCell(n , parameters(1).substring(1,1).toInt )
                //          // TODO: ^^ nur nach untnen
                //        }
              }*/

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

  def checkWord(word: String): Boolean = {
    if (controller.dict.dict.contains(word.toUpperCase())) {
      true
    }
    else {
      false
    }
  }
}
