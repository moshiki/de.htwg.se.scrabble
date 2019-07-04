package de.htwg.se.scrabble.model

import de.htwg.se.scrabble.model.cards.Card

trait CardStackInterface {

  def getCard: Option[Card]
  def getSpecificCard(card:Card): Option[Card]
  def putCard(card:Card)
  def getStack: List[Card]
  def getSize: Int
  def isEmpty: Boolean

}
