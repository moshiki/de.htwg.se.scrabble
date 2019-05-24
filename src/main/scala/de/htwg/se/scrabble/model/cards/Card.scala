package de.htwg.se.scrabble.model.cards

case class Card(letter: String) {
  val value: String =
    if (isLetter(letter)) {letter}
    else "_"

  def isLetter(str: String): Boolean = str.matches("[A-Z#*]")

  override def toString = this.value
}
