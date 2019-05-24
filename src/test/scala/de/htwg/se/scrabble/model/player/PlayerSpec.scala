package de.htwg.se.scrabble.model.player

import de.htwg.se.scrabble.model.cards.Card
import org.scalatest._

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when {
    val player = Player("A", "Mampfred")
    "new" should {
      "have a role and name" in {
        player.role should be("A")
        player.name should be("Mampfred")
      }
      "have a nice String representation" in {
        player.toString should be("Player A: Mampfred")
      }
    }
    "exists" should {
      "take up to 7 cards to the hand with addToHand(Card)" in {
        for (i<- 1 to 7) {
          player.addToHand(Card("A")) should be(true)
        }
        player.addToHand(Card("B")) should be(false)
      }
      "know the number of cards on hand" in {
        player.getNrCardsInHand should be(7)
      }
      "have a hand of cards" in {
        var list: List[Card] = Nil
        for (i <-1 to 7)
          list = Card("A") :: list
        player.getHand should be(list)
      }
    }
  }
}
