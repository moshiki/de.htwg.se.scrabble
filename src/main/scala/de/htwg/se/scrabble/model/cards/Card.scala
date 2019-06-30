package de.htwg.se.scrabble.model.cards

import com.google.inject.Inject
import de.htwg.se.scrabble.model.CardInterface

case class Card @Inject()(letter: String) {
  val value: String =
    if (isLetter(letter)) {letter}
    else "_"

  def isLetter(str: String): Boolean = str.matches("[A-Z#*]")

  override def toString = this.value

  def isEmpty: Boolean = true // TODO: Implement
}
