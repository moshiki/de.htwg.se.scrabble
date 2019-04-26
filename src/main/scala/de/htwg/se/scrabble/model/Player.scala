package de.htwg.se.scrabble.model

case class Player(pos: String, name: String) {

  override def toString:String = "Player "+pos+": "+name
}
