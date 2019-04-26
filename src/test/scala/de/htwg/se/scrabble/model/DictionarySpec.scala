package de.htwg.se.scrabble.model
import java.io.FileNotFoundException

import org.scalatest._
import org.scalatest.PrivateMethodTester._

import scala.collection.mutable

class DictionarySpec extends WordSpec with Matchers {
  "A Dictionary" when { "is generated" should {
    val dictionary = new Dictionary()
    "initialize an alphabet map and a word list" in {
      dictionary.alphabet should contain key "A"
      dictionary.alphabet should contain key "Z"
      //dictionary.alphabet should contain value "1"
      dictionary.dict should not be empty }
  }
    "throw FileNotFoundException when input files not found" in {
      val dictionary = new Dictionary()
      val loadAlphabet = PrivateMethod[mutable.HashMap[String, Integer]]('loadAlphabet)
      val loadDictionary = PrivateMethod[mutable.HashSet[String]]('loadDictionary)
      intercept[FileNotFoundException] {
        dictionary invokePrivate loadAlphabet("falseFile.txt")
      }
      intercept[FileNotFoundException] {
        dictionary invokePrivate loadDictionary("falseFile.txt")
      }
    }
  }
}
