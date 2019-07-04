package de.htwg.se.scrabble.model

import de.htwg.se.scrabble.model.cards.Card

trait PlayerInterface {
  def role: String
  def name: String
  def getHand: List[Card]
  def putCard(card: Card): Option[Card]
  def getNrCardsInHand: Int
  def addToHand(card: Card): Boolean
  def getPoints: Int
  def addPoints(value: Int)
  def subPoints(value: Int)
  def actionPermitted: Boolean
  def grantActionPermit()
  def revokeActionPermit()
  def switchedHand: Boolean
  def grantSwitchedHand()
  def revokeSwitchedHand()
  def toString:String
}
