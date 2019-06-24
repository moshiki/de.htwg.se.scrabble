package de.htwg.se.scrabble.model.player

import de.htwg.se.scrabble.model.PlayerInterface
import de.htwg.se.scrabble.model.cards.Card

import scala.collection.mutable.ArrayBuffer

case class Player(role: String, name: String){
  private var hand: List[Card] = Nil
  private val handSize = 7

  def getHand: List[Card] = hand

  def putCard(card: Card): Option[Card] = {
    if (hand.contains(card)) {
      hand = ((ArrayBuffer() ++ hand) - card).toList
      Some(card)
    } else {
      None
    }
  }

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
