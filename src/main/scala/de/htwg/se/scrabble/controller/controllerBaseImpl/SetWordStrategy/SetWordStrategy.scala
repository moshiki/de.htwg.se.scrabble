package de.htwg.se.scrabble.controller.controllerBaseImpl.SetWordStrategy

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.model.field.Cell

abstract class SetWordStrategy(controller:ControllerInterface) {
  def setWord(word: String, cell: Cell, x:String, y: Int): Boolean

  def validHand(word:String, placementMap: Map[Cell, String], hand:List[Card]): Boolean = {
    for (card <- placementMap.values) {
      if (!hand.contains(Card(card))) {
        controller.gameStatus(GameStatus.NOHANDCARD)
        return false
      }
    }
    true
  }
}
