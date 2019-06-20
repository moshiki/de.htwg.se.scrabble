package de.htwg.se.scrabble.model

import org.scalatest.{FlatSpec, Matchers}

class RegularFieldSpec extends FlatSpec with Matchers{
  val rf = RegularField(15)

  "Regular Field" should "give back an 'X'" in {
    rf.getCell("H", 7).get.getValue should be("X")
  }

  "Regular Field" should "give back None id coordinates exceed field size" in {
    rf.getCell("H", 100) should be(None)
  }

  "new Cell" should "contain the new character" in {
    rf.setCell("A", 1, "V") should be(true)
    rf.setCell("B", 2, "Z") should be(true)
    rf.setCell("C", 3, "Y") should be(true)
  }

  "A Printed Field" should "contain the H from the Test Before" in {
    rf.toString should contain('V')
  }
  "The Printed Row" should "contain the H from the Test Before" in {
    rf.getRows should contain('Z')
  }
  "The Printed Col" should "contain the H from the Test Before" in {
    rf.getCols should contain('Y')
  }


}
