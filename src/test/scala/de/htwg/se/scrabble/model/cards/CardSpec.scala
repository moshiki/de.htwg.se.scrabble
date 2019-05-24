package de.htwg.se.scrabble.model.cards
import org.scalatest._

class CardSpec extends WordSpec with Matchers {
  "A card" when {
    val card = Card("")
    "is empty" should {
      "contain nothing" in {card.value should be("_")}
    }
    "is set" should {
      val card = Card("Acv")
      "contain only one capital letter or is empty" in {
        card.value should fullyMatch regex "[A-Z#*]||[^$]"
      }
    }

  "isLetter(str: String)" when {
    "str is not a capital letter" should {
      val card = Card(letter = "x")
      "return false" in {
        false === card.isLetter(card.value)
      }
    }
    "str is a capital letter" should {
      val card = Card(letter = "A")
      "return true" in {
        true === card.isLetter(card.value)
      }
    }
  }
  }
}