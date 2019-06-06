package de.htwg.se.scrabble.model.player

import de.htwg.se.scrabble.model.cards.Card

case class Player(role: String, name: String) {
  private var hand: List[Card] = Nil
  private val handSize = 7

  def getHand: List[Card] = hand

  def getNrCardsInHand: Integer = hand.size

  def addToHand(card: Card): Boolean = {
    if (getNrCardsInHand < handSize) {
      hand = card :: hand
      return true
    }
    false
  }

  // ToDo: FUnktion zum legen einer Karte implementieren. Muss prüfen, ob Karte auch wirklich in Hand vorhanden ist.

  // ToDO: Punkte im SPieler speichern

  override def toString:String = "Player "+role+": "+name
}
