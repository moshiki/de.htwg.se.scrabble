package de.htwg.se.scrabble.model.cards

trait CardStackTemplate {

  def getCard: Option[String]

  def getSize: Integer

}
