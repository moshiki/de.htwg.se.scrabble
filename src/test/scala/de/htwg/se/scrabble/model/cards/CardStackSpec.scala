package de.htwg.se.scrabble.model.cards
import org.scalatest._

class CardStackSpec extends WordSpec with Matchers {
  "A CardStack" when {
    val stack = CardStack
    "getCard is invoked" should {
      "return an Options Some(letter(card)) of the card stack or None if stack is empty" in {
        val card = stack.getCard
        if (card.isDefined) {
          stack.getSize should not be (0)
          card.get should fullyMatch regex "[a-z#]"
        } else
          stack.getSize should be(0)
      }

      "remove the handed card from the stack when getCard is called" in {
        val size = stack.getSize
        stack.getCard
        stack.getSize should be < size
      }

      "return a None when getCard is invoked and stack is empty" in {
        while (stack.getSize > 0)
          stack.getCard
        stack.getCard should be(None)
      }
    }
  }
}

