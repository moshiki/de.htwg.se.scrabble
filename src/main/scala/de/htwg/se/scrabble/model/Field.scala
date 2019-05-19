package de.htwg.se.scrabble.model

class Field {
  var size = 15
  var matrix: Array[Array[String]] = Array.ofDim[String](size,size)

  for ( i <- 0 until size){
    for ( j <- 0 until size){
      matrix(i)(j) = "_"
    }
  }

  def printField(): Unit = {
  println("A B C D E F G H I J K L M O")
  println("_______________________________")
    for ( i <- 0 until size){
      for ( j <- 0 until size){
        print("|" + matrix(i)(j).toString)
      }
      print("|\n")
    }
    println("""‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾""")
 }

  def printHor(): Unit = {
    for { i <- 0 until size
          j <- 0 until size
    } print(s"${matrix(i)(j)}")
  }

  def printVer(): Unit = {
    for { i <- 0 until size
          j <- 0 until size
    } print(s"${matrix(j)(i)}")
  }

  // vor einfügen prüfen ob und wo das wort eingebaut werden kann. Anschließende auswahl über nummern mit Punktevergabe. Bei einziger legemöglichkeit direkte auswahl
  // Savegame je runde: spieler und deren Punktestand. Zustand des Feldes. Karten der Hand.
  // Eigabemöglichkeit: Kleinbustaben = Hand; Großbustaben = Feld; Programm sucht wo es gebildet werden kann und gibt vorschläge für wahl?
}
