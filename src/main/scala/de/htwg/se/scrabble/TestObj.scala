package de.htwg.se.scrabble

object TestObj {
  def main(args: Array[String]): Unit = {
    val student = Student("Chief!")
    println("Hello " + student.name)
  }
}

  case class Student(name: String) {
    def f(x: Int) : Int = x + 1
  }

