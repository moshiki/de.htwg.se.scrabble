package de.htwg.se.scrabble

object Start {
  def main(args: Array[String]): Unit = {
    val student = Student("Rockt!")
    println("Scrabble " + student.name)
  }
}
// Blaaa
case class Student(name: String) {
  def f(x: Int) : Int = x + 1
}

