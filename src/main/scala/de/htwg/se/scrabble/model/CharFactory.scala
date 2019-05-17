package de.htwg.se.scrabble.model

trait CharFactory {
    def getChar = println("animal running")
  }

  private class Dog extends CharFactory {
    override def getChar: Unit = println("dog running")
  }

  private class Cat extends CharFactory {
    override def getChar: Unit = println("cat running")
  }

  object Animal {
    def apply(kind: String) = kind match {
      case "dog" => new Dog()
      case "cat" => new Cat()
    }
  }
//  val animal = Animal("dog")
//  animal.run
