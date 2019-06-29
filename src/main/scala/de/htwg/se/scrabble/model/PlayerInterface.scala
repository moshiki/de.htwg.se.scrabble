package de.htwg.se.scrabble.model

import de.htwg.se.scrabble.model.cards.Card

trait PlayerInterface {
  def getHand: List[Card]
  def putCard(card: Card): Option[Card]
  def getNrCardsInHand: Integer
  def addToHand(card: Card): Boolean
  def toString:String
  // ToDo: FUnktion zum legen einer Karte implementieren. Muss prüfen, ob Karte auch wirklich in Hand vorhanden ist.
  // ToDO: Punkte im SPieler speichern
}
