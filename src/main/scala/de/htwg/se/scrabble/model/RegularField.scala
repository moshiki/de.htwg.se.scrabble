package de.htwg.se.scrabble.model

case class RegularField(size: Integer) extends FieldTemplate {
  // var size = 15
  var matrix: Array[Array[Cell]] = Array.ofDim[Cell](size,size)

  for ( i <- 0 until size){
    for ( j <- 0 until size){
      matrix(i)(j) = new Cell("_")
    }
  }

  matrix(size/2)(size/2) = new Cell("X")

  def getCell(row: Integer, col:String): Cell = {
    matrix(col.charAt(0)-65)(row)
  }

  def setCell(row: Integer, col: String, value: String): Boolean = {
    getCell(row,col).setValue(value)
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

  def getRows: String = {
    val str = new StringBuilder
    for { i <- 0 until size
          j <- 0 until size
    } str.append(s"${matrix(i)(j).getValue}")
    str.toString()
  }

  def getCols: String = {
    val str = new StringBuilder
    for { i <- 0 until size
          j <- 0 until size
    } str.append(s"${matrix(j)(i).getValue}")
    str.toString()
  }

  // undo je runde: spieler und deren Punktestand. Zustand des Feldes. Karten der Hand.
}
