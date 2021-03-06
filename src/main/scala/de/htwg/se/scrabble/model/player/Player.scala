package de.htwg.se.scrabble.model.player

import de.htwg.se.scrabble.model.PlayerInterface
import de.htwg.se.scrabble.model.cards.Card
import scala.collection.mutable.ArrayBuffer

case class Player(role: String, name: String) extends PlayerInterface {
  private var hand: List[Card] = Nil
  private val handSize = 8
  private var points = 0
  private var actionPermit: Boolean = true
  private var switched = false

  def getHand: List[Card] = hand

  def putCard(card: Card): Option[Card] = {
    if (hand.contains(card)) {
      hand = ((ArrayBuffer() ++ hand) - card).toList
      Some(card)
    } else {
      None
    }
  }

  def getNrCardsInHand: Int = hand.size

  def addToHand(card: Card): Boolean = {
    if (getNrCardsInHand < handSize) {
      hand = card :: hand
      return true
    }
    false
  }

  override def getPoints: Int = points
  override def addPoints(value: Int): Unit = points += value
  override def subPoints(value: Int): Unit = points -= value

  override def actionPermitted: Boolean = actionPermit
  override def grantActionPermit(): Unit = actionPermit = true
  override def revokeActionPermit(): Unit = actionPermit = false

  override def switchedHand: Boolean = switched
  override def grantSwitchedHand(): Unit = switched = false
  override def revokeSwitchedHand(): Unit = switched = true

  override def toString:String = "PLAYER "+role+s" | $points points"
}

object Player {
  import play.api.libs.json._
  implicit val playerWrites = OWrites[Player](_ => Json.obj())
  //implicit val cellReads = Reads[Cell](json =>
  //json.validate[JsObject].filter(_.values.isEmpty).map(_ => Cell()))
}
