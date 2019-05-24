package de.htwg.se.scrabble.model.cards

trait CardStackTemplate {

  def getCard: Option[Card]

  def getSize: Integer

}
