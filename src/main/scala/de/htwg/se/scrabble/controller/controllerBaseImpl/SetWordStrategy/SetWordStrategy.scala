package de.htwg.se.scrabble.controller.controllerBaseImpl.SetWordStrategy

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.model.field.Cell

abstract class SetWordStrategy(controller:ControllerInterface) {
  def setWord(word: String, cell: Cell, x:String, y: Int): Boolean

  def validHand(word:String, hand:List[Card], usableCards:List[String]): Boolean = {
    var allCards: List[String] = hand.map(s => s.value)
    allCards ++= usableCards
    for (c <- word) {
      if (!allCards.contains(c.toString)) {
        controller.gameStatus = GameStatus.NOHANDCARD
        return false
      }
      allCards diff List(c.toString)
    }
    true
  }
}
