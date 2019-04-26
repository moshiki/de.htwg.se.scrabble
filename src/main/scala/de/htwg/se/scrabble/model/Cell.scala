package de.htwg.se.scrabble.model

class Cell(letter: String) {
  var value: String =
    if (isLetter(letter)) {letter}
    else "x"

  def isLetter(str: String): Boolean = str.matches("[A-Z]")
}