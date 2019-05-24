package de.htwg.se.scrabble.model.player

import scala.collection.mutable.ListBuffer

case class Player(role: String, name: String) {
  private var hand: List[String] = Nil
  private var handSize = 7

  def getHand: List[String] = hand

  def getNrCardsInHand: Integer = hand.size

/*  def addToHand(Card): Boolean = {
    if (getNrCardsInHand < handSize) {

    }
  }*/

  override def toString:String = "Player "+role+": "+name
}
