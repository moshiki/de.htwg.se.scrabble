package de.htwg.se.scrabble.model

import de.htwg.se.scrabble.model.cards.Card

trait CardInterface {

  def getCard: Option[Card]

  def getSize: Int

  def isEmpty: Boolean

}
