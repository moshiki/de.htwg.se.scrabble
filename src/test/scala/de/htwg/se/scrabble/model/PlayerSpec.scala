package de.htwg.se.scrabble.model

import org.scalatest._

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("A", "Mampfred")
    "have a name" in {
      player.name should be("Your name")
    }
    "have a nice String representation" in {
      player.toString should be("Your name")
    }
  }}
}
