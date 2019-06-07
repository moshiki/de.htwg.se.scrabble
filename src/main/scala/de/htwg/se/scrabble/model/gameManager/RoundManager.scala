package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.{Controller, GameStatus}

// abstract case weil noch nicht fertig
abstract case class RoundManager(controller: Controller) extends GameManager {
  override var status: String = _
  override def getStatus: Boolean = ???
  override def statusToString: String = ???


  def round : Unit {
//    controller
  }

  def pull: Unit =

  // wie viele karten nötig?
    if(true){       // Reicht ees zum ziehen?

      // ja/Nein
    }

  def setWord : Unit {
    // check erste beiden buchstaben für initial feld
    // check 3. Buchstaben für horizontal oder vertikal
    // check <3 word (ignore whitespace)
  }

}
