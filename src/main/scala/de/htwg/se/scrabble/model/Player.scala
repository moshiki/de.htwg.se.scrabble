package de.htwg.se.scrabble.model

case class Player(role: String, name: String) {
  override def toString:String = "Player "+role+": "+name
}
