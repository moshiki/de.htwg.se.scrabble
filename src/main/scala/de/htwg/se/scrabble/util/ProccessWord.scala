package de.htwg.se.scrabble.util

import de.htwg.se.scrabble.controller.{Controller, SetCommand}

object ProccessWord {

  //   Zusammenschreiben für befehlsübergabe zammebau
  // TODO: weiterleiten an Objekt, welches nachfolgende funktionen bearbeitet (in util package)
  def setWord(parameters:Array[String], controller: Controller): Unit = {
    if (parameters.length == 4) {
      // Startpositions check
      // TODO: Sind schon Wörter im Grid? Wenn nein, weiter bei prüfe Wort gültig ->
      if(!parameters(1).substring(0,0).matches("[A-Z#*]") //TODO: check auf größe des Feldes noch! (Buchstabe) sonst sind array.outOfBounds überläufte möglich
        || !parameters(1).substring(1,1).matches("[0-9#*]")) {  // TODO: check auf größe des Feldes noch! (Zahl) sonst sind array.outOfBounds überläufte möglich
        System.err.println("wrong Position, pls type cmd like: set B5 - Hallo ")
        return
      } // check ob | oder -
      if(!(parameters(2) == "-" || parameters(2) =="|")) {  // zusammenschnnitt unnd mit substring bei 4 checken ob | oder -
        System.err.println("wrong direction modifier: '-' vertical, '|' horizontal")
        return
      }
      // TODO: past es als wort mit den vorhandenenn Buchstabenn rein?
      // TODO: -> Ist es ein gültiges Wort?
      // TODO: ist die länge des wortes inerhalb des grids?
      // TODO: Wort in das alktuelle grid einfügen (insert). Wenn noch keine Wörter im Grid, dann in Mitte platzieren.
      controller.undoManager.doStep(new SetCommand(controller.grid, controller.players, controller.stack, controller))
      controller.notifyObservers
    } else {
      System.err.println("wrong input, pls type cmd like: set B5 - Hallo")
    }
  }


  def undo(controller: Controller): Unit = {
    controller.undoManager.undoStep
    controller.notifyObservers
  }

  def redo(controller: Controller): Unit = {
    controller.undoManager.redoStep
    controller.notifyObservers
  }

}
