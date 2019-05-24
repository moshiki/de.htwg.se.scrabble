package de.htwg.se.scrabble.model.cards

abstract class CardStackTemplate {

  def getCard: Option[Card]

  def getSize: Integer

}
