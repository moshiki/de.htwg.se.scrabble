package de.htwg.se.scrabble.model
import org.scalatest._

class CellSpec extends WordSpec with Matchers {
  "A cell" when {
    val cell = new Cell("")
    "is empty" should {
      "contain nothing" in {cell.getValue should be("_")}
    }
    "is set" should {
      val cell = new Cell("Acv")
      "contain only one capital letter or is empty" in {
        cell.getValue should fullyMatch regex "[A-Z]||[^$]"
      }
    }
    "setValue is called set the specific value" in {
      cell.setValue("F")
      cell.getValue should be("F")
    }
    "getValue is called return value" in {
      cell.getValue should be("F")
    }
  }
  "isLetter(str: String)" when { "str is not a capital letter" should {
      val cell = new Cell(letter = "x")
      "return false" in {
        false === cell.isLetter(cell.getValue)
      }
  }
  "str is a capital letter" should {
      val cell = new Cell(letter = "A")
      "return true" in {
        true === cell.isLetter(cell.getValue)
      }
    }
  }
}
