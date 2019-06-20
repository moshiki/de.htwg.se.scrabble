package de.htwg.se.scrabble.model

import de.htwg.se.scrabble.controller.GameStatus

case class RegularField(size: Integer) extends FieldTemplate {
  // var size = 15
  var matrix: Array[Array[Cell]] = Array.ofDim[Cell](size,size)

  for ( i <- 0 until size){
    for ( j <- 0 until size){
      matrix(i)(j) = new Cell("_")
    }
  }

  matrix(size/2)(size/2) = new Cell("X")

  override def getCell(x: String, y: Int): Option[Cell] = {
    val X = x.toUpperCase().charAt(0)-65
    if (X < size && y < size) {
      Some(matrix(y)(X))
    } else {
      controller.gameStatus = GameStatus.OOBOUND
      None
    }
  }

  override def setCell(x: String, y: Int, value: String): Boolean = {
    val c = getCell(x.toUpperCase(),y)
    if (c.isDefined) {
      c.get.setValue(value.toUpperCase())
      true
    } else false
  }

  override def toString: String = {
    var board: String = "|"
    for (a <- 65 until (65+ size)) board = board + a.toChar + "|"
    board = board + "\n_______________________________\n"
    for ( i <- 0 until size){
      for ( j <- 0 until size){
        board = board + "|" + matrix(i)(j).getValue
      }
      board = board + "| " + i + "\n"
    }
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

  override def getSize: Integer = {
    size
  }
  // undo je runde: spieler und deren Punktestand. Zustand des Feldes. Karten der Hand.
}
