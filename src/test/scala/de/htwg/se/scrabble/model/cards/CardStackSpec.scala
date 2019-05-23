package de.htwg.se.scrabble.model.cards
import org.scalatest._

class CardStackSpec extends WordSpec with Matchers {
  "A CardStack" when {
    val stack = CardStack
    "getCard is invoked" should {
      "return a letter(card) of the card stack" in {
        stack.getCard should fullyMatch regex "[a-z]"
      }
    }
  }
}

