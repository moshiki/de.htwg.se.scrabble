package de.htwg.se.scrabble.model.cards

import com.google.inject.Inject
import de.htwg.se.scrabble.model.CardInterface

case class Card @Inject()(letter: String) extends CardInterface {
  val value: String =
    if (isLetter(letter)) {letter}
    else "_"

  def isLetter(str: String): Boolean = str.matches("[A-Z#*]")

  override def toString = this.value

  override def isEmpty: Boolean = true // TODO: Implement

  override def getCard: Option[Card] = ???

  override def getSize: Int = ???
}
