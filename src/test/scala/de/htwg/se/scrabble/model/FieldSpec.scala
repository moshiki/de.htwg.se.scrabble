package de.htwg.se.scrabble.model

import org.scalatest.{Matchers, WordSpec}

class FieldSpec extends WordSpec with Matchers{
  "For a Field" when {
    "is created" should {
      val matrix = new Field(15)
      "initialize the Cells with a Underscore" in {
//        TODO: Write real Tests
//        matrix.printHor() should contain String "_"
//        matrix.printVer() should contain char "_"
//        matrix.printField() should not be empty
      }
    }
  }
}
