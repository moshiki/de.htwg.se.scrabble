package de.htwg.se.scrabble.util

import de.htwg.se.scrabble.controller.{Controller, SetCommand}

object ProccessWord {

  def setWord(parameters:Array[String], controller: Controller): Unit = {
    if (parameters.length == 4) {
      if(((parameters(1).substring(0,0).toInt - 65) > controller.field.getSize) || // Startposition checken
        (parameters(1).substring(1,1).toInt > controller.field.getSize )) {
        System.err.println("wrong Position, pls type cmd like: set B5 - Hallo")
        return
      }

      // TODO: -> Ist es ein gültiges Wort?
      // if((parameters(4) kein wort aus dem wörterbuch) {Error Massage + Return}


      if(!(parameters(2) == "-" || parameters(2) =="|")) {                        // check ob wirklich "|" oder "-" an 2. stelle steht
        System.err.println("wrong direction modifier: '-' vertical, '|' horizontal")
        return
      }
      if( parameters(2) == "-"){
        if(parameters(1).substring(0,0).toInt -65 + parameters(4).size > controller.field.getSize + 1 ){
          System.err.println("Word is to long for the Field with the Start at " + (parameters(1).toString))
          return
        }
        for (n <- 0 to parameters(4).toString.length){

          controller.field.getCell(parameters(1).substring(1,1) + n , parameters(1).substring(1,1).toInt )
          // TODO: Nach rechts Cellenbuchstaben mit eingabe prüfenn, je nach index

        }
      }

      if(parameters(2) == "|") {
        if(parameters(1).substring(1,1).toInt + parameters(4).size > controller.field.getSize + 1 ){
          System.err.println("Word is to long for the Field with the Start at " + (parameters(1)))
          return
        }
        //        for (n <- 0 to parameters(4).toString.length){
        //          controller.grid.getCell(n , parameters(1).substring(1,1).toInt )
        //          // TODO: ^^ nur nach untnen
        //        }
      }


      // TODO: INSERT -> wird beim einsetzen min. ein anderer Bustabe genutzt? (oder Mittelfeld) (checkenn ob buchstabenn auch alle gleich sinnd oder _ oder *)


                                                              // grid -> field
//      controller.undoManager.doStep(new SetCommand(controller.grid, controller.players, controller.stack, controller))
//      controller.notifyObservers
    } else {
      System.err.println("wrong input, pls type cmd like: set B5 - Hallo")
    }
  }


//
//
//  def undo(controller: Controller): Unit = {
//    controller.undoManager.undoStep
//    controller.notifyObservers
//  }
//
//  def redo(controller: Controller): Unit = {
//    controller.undoManager.redoStep
//    controller.notifyObservers
//  }


}