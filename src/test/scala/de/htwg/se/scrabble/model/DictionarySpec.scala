package de.htwg.se.scrabble.model

import java.io.FileNotFoundException

import org.scalatest._
import org.scalatest.PrivateMethodTester._

import scala.collection.{immutable, mutable}


class DictionarySpec extends WordSpec with Matchers {
  "A Dictionary is a tailor-made datatype that contains a set of words and a map with a value" +
    "for each letter of the alphabet. A Dictionary" when {
    val dictionary = Dictionary
    "is created" should {

      "initialize the alphabet map with an external text file, that for each letter" +
        "contains one line with letter and value separated by a blank." +
        "And initialize a word set with an external text file that contains one word per line." in {
        dictionary.alphabet should contain key "A"
        dictionary.alphabet should contain key "Z"
        dictionary.dict should not be empty
      }
    }
    "throw FileNotFoundException when input files not found" in {
      val dictionary = Dictionary
      val loadAlphabet = PrivateMethod[immutable.HashMap[String, Integer]]('loadAlphabet)
      val loadDictionary = PrivateMethod[immutable.HashSet[String]]('loadDictionary)
      intercept[FileNotFoundException] {
        dictionary invokePrivate loadAlphabet("falseFile.txt")
      }
      intercept[FileNotFoundException] {
        dictionary invokePrivate loadDictionary("falseFile.txt")
      }
    }
    "exists" should {
      //val dictionary = Dictionary
      /*override val dict: immutable.HashSet[String] = immutable.HashSet("d", "b", "a", "c")
        val alphabet: immutable.TreeMap[String, Integer] = immutable.TreeMap(("C", 1), ("A", 2), ("D", 3), ("B", 4))*/

      "print out the dictionary in sorted order" in {
        val str1 = new mutable.StringBuilder()
        str1.append("Word list:")
        dictionary.dict.toStream.sorted.foreach(x=>str1.append("\n"+x))
        dictionary.dictToString should be(str1.toString())
      }
      "print out the alphabet map in sorted order" in {
        val str2 = new mutable.StringBuilder()
        str2.append("Alphabet vector:")
        dictionary.alphabet.toSeq.sortBy(_._1).foreach(x=>str2.append("\n"+x))
        dictionary.vectorToString should be(str2.toString())
      }
    }
  }
}
