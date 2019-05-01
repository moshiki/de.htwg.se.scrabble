package de.htwg.se.scrabble.model.player

import org.scalatest._

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("A", "Mampfred")
    "have a role and name" in {
      player.role should be("A")
      player.name should be("Mampfred")
    }
    "have a nice String representation" in {
      player.toString should be("Player A: Mampfred")
    }
  }}
}
