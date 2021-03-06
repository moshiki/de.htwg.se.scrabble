package de.htwg.se.scrabble.model
import de.htwg.se.scrabble.model.field.Cell
import org.scalatest._

class CellSpec extends WordSpec with Matchers {
  "A cell" when {
    val cell = new Cell("")
    "is empty" should {
      "contain nothing" in {
        cell.getValue should be("_")
        cell.isEmpty should be(true)
      }
    }
    "is set" should {
      val cell1 = new Cell("A")
      val cell2 = new Cell("xyz")
      "contain only one capital letter or is empty" in {
        cell1.getValue should be ("A")
        cell1.isEmpty should be(false)
        cell2.getValue should be("_")
        cell2.isEmpty should be(true)

      }
    }
    "setValue is called set the specific value" in {
      cell.setValue("F") should be(true)
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
