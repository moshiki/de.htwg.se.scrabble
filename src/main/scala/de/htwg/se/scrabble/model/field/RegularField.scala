package de.htwg.se.scrabble.model.field

import de.htwg.se.scrabble.controller.GameStatus
import de.htwg.se.scrabble.model.FieldInterface

import scala.collection.mutable.SortedMap


case class RegularField(size: Int) extends FieldInterface {
  // var size = 15
  var matrix: Array[Array[Cell]] = Array.ofDim[Cell](size,size)
  var grid: SortedMap[Int, SortedMap[String, Cell]] = SortedMap.empty[Int, SortedMap[String, Cell]]

  for (row <- 0 until size) {
    grid += (row -> SortedMap.empty[String, Cell])
    for (a <- 65 until (65 + size)) {
      var col = a.toChar.toString
      grid(row) += (col -> new Cell("_"))
      /*//grid += (col -> SortedMap.empty[String, Cell])
      for (col <- 0 until size) {
        grid(row) += (col -> new Cell("_"))
        //field += (new Coordinate(a.toChar.toString, i)-> new Cell("_"))
      }*/
    }
  }

  /*for ( i <- 0 until size){

    for ( j <- 0 until size){
      matrix(i)(j) = new Cell("_")
    }
  }*/

  setCell((65+size/2).toChar.toString, size/2, "*")
  //matrix(size/2)(size/2) = new Cell("X")


  override def getCell(row: String, col: Int): Option[Cell] = {
    val X = row.toUpperCase().charAt(0)-65
    if (X < size && col < size) {
      Some(grid(col)(row))
    } else {
      controller.gameStatus = GameStatus.OOBOUND
      None
    }
  }

  //override def getNextCell(cell: Cell): Option[Cell] = {None  }

  override def setCell(row: String, col: Int, value: String): Boolean = {
    val c = getCell(row.toUpperCase(),col)
    if (c.isDefined) {
      c.get.setValue(value.toUpperCase())
      true
    } else false
  }

  override def toString: String = {
    var board: String = "|"
    //for (a <- 65 until (65+ size)) board += a.toChar + "|"
    val r = 65 until 65+size
    r.foreach(col => board += col.toChar + "|")
    board += "\n_______________________________\n"
    grid.foreach(row => {board += row._1
                         row._2.foreach(col => board += "|" + col._2.getValue)
                         board += "\n"})
    /*for ( i <- 0 until size){
      for ( j <- 0 until size){
        board = board + "|" + grid(i)(j).getValue
      }
      board = board + "| " + i + "\n"
    }*/
    board = board + "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n"
    board
  }

  override def getRows: String = {
    val str = new StringBuilder
    for { i <- 0 until size
          j <- 0 until size
    } str.append(s"${matrix(i)(j).getValue}")
    str.toString()
  }

  override def getCols: String = {
    val str = new StringBuilder
    for { i <- 0 until size
          j <- 0 until size
    } str.append(s"${matrix(j)(i).getValue}")
    str.toString()
  }

  override def getSize: Int = {
    size
  }
  // undo je runde: spieler und deren Punktestand. Zustand des Feldes. Karten der Hand.
}
