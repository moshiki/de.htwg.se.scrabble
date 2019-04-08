package de.htwg.se.scrabble.model
import org.scalatest._

class CellSpec extends WordSpec with Matchers {
  "A cell" when { "is empty" should {
    val cell = new Cell("x")
    "contain x" in {
    cell.value should be("x")}
  }
    "is set" should {
      val cell = new Cell("Acv")
      "contain only one capital letter or is empty" in {
        cell.value should fullyMatch regex "[A-Z]||[x]"
      }
    }
    }
  "isLetter(str: String)" when { "str is not a capital letter" should {
      val cell = new Cell(letter = "x")
      "return false" in {
        false === cell.isLetter(cell.value)
      }
  }
  "str is a capital letter" should {
    val cell = new Cell(letter = "A")
    "return true" in {
      true === cell.isLetter(cell.value)
    }
  }
  }
}
