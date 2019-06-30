package de.htwg.se.scrabble.model

import de.htwg.se.scrabble.model.field.{Cell, RegularField}
import org.scalatest.{FlatSpec, Matchers}

class RegularFieldSpec extends FlatSpec with Matchers{
  val rf = new RegularField(15)

  /*"Regular Field" should "give back an '*'" in {
    rf.getCell("H", 7).get.getValue should be("*")
  }*/

  "Regular Field" should "give back None if coordinates exceed field size" in {
    rf.getCell("H", 100) should be(None)
  }

  "A cell" should "contain the new character" in {
    rf.setCell("A", 1, "V") should be(true)
    rf.setCell("B", 2, "Z") should be(true)
    rf.setCell("C", 3, "Y") should be(true)
  }

  "A cell" should "not be set when not exists" in {
    rf.setCell("X", 99, "A") should be(false)
  }

  "getNextCell" should "return the cell on the left side or nothing if not existent" in {
    rf.setCell("C", 2, "G")
    rf.getNextCell(rf.getCell("B", 2).get).get.getValue should be("G")
    rf.getNextCell(rf.getCell("O", 2).get) should not be defined
  }

  "getPrevCell" should "return the cell on the left side or nothing if not existent" in {
    rf.setCell("A", 2, "F")
    rf.getPrevCell(rf.getCell("B", 2).get).get.getValue should be("F")
    rf.getPrevCell(rf.getCell("A", 2).get) should not be defined
  }

  "getUpperCell" should "return the cell on the left side or nothing if not existent" in {
    rf.setCell("B", 1, "I")
    rf.getUpperCell(rf.getCell("B", 2).get).get.getValue should be("I")
    rf.getUpperCell(rf.getCell("B", 1).get) should not be defined
  }
  "getLowerCell" should "return the cell on the left side or nothing if not existent" in {
    rf.setCell("B", 3, "H")
    rf.getLowerCell(rf.getCell("B", 2).get).get.getValue should be("H")
    rf.getLowerCell(rf.getCell("B", 15).get) should not be defined
  }

  "getCoordinates" should "return the coordinates of a cell or nothing if not existent"  in {
    val c = rf.getCoordinates(rf.getCell("B", 10).get).get
    c.col should be('B')
    c.row should be(10)

    rf.getCoordinates(new Cell("_")) should not be defined
  }

  "A coordinate" should "contain a column and a row" in {
    val c = rf.Coordinate(3, 'A')
    c.col should be('A')
    c.row should be(3)
  }

  "getSize" should "return the field size" in {
    rf.getSize should be(15)
  }


  "A Printed Field" should "contain the H from the Test Before" in {
    rf.toString should contain('V')
  }
  /*"The Printed Row" should "contain the H from the Test Before" in {
    rf.getRows should contain('Z')
  }
  "The Printed Col" should "contain the H from the Test Before" in {
    rf.getCols should contain('Y')
  }*/


}
