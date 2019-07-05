package de.htwg.se.scrabble.model.cards
import org.scalatest._

class RegularCardStackSpec extends WordSpec with Matchers {
  "A CardStack" when {
    val stack = new RegularCardStack
    val stack2 = new RegularCardStack
    "getCard is invoked" should {
      "return an Options Some(letter(card)) of the card stack or None if stack is empty" in {
        val card = stack.getCard
        if (card.isDefined) {
          stack.getSize should not be 0
          card.get.value should fullyMatch regex "[A-Z*#]"
        } else
          stack.getSize should be(0)
      }

      "remove the handed card from the stack when getCard is called" in {
        val stack2 = new RegularCardStack
        val size = stack2.getSize
        stack2.getCard
        if(size > 0) {
          stack2.getSize should be < size
        }
        stack2.putCard(Card("C"))
        stack2.getSpecificCard(Card("C"))
      }

      "return a None when getCard is invoked and stack is empty" in {
        while (stack2.getSize > 0)
          stack2.getCard
        stack2.getCard should be(None)
      }
    }
  }
}

