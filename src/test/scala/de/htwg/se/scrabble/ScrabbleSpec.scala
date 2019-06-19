package de.htwg.se.scrabble

import org.scalatest.{Matchers, WordSpec}

class ScrabbleSpec extends WordSpec with Matchers {

  "The Scrabble main class" should {
    "accept text input as argument without read line loop, to test it from command line " in {
      Scrabble.main(Array[String]("new"))
    }
  }

}
