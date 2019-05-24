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

  override def toString: String = {
    var board: String = "|"
    for (a<- 65 until (65+ size)) board = board + a.toChar + "|"
    board = board + "\n_______________________________\n"
    for ( i <- 0 until size){
      for ( j <- 0 until size){
        board = board + "|" + matrix(i)(j).value
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
    } str.append(s"${matrix(i)(j).value}")
    str.toString()
  }

  def getCols: String = {
    val str = new StringBuilder
    for { i <- 0 until size
          j <- 0 until size
    } str.append(s"${matrix(j)(i).value}")
    str.toString()
  }

  // vor einfügen prüfen ob und wo das wort eingebaut werden kann. Anschließende auswahl über nummern mit Punktevergabe. Bei einziger legemöglichkeit direkte auswahl
  // Savegame je runde: spieler und deren Punktestand. Zustand des Feldes. Karten der Hand.
  // Eigabemöglichkeit: Kleinbustaben = Hand; Großbustaben = Feld; Programm sucht wo es gebildet werden kann und gibt vorschläge für wahl?
}
