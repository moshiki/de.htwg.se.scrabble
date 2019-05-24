package de.htwg.se.scrabble.model

import java.io.FileNotFoundException

import org.scalatest._
import org.scalatest.PrivateMethodTester._

import scala.collection.mutable

class DictionarySpec extends WordSpec with Matchers {
  "A Dictionary is a tailor-made datatype that contains a set of words and a map with a value" +
    "for each letter of the alphabet. A Dictionary" when {
    "is created" should {
      val dictionary = new Dictionary()
      "initialize the alphabet map with an external text file, that for each letter" +
        "contains one line with letter and value separated by a blank." +
        "And initialize a word set with an external text file that contains one word per line." in {
        dictionary.alphabet should contain key "A"
        dictionary.alphabet should contain key "Z"
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
    "exists" should {
      val dictionary = new Dictionary {
        override val dict:mutable.HashSet[String] = mutable.HashSet("d","b","a","c")
        override val alphabet:mutable.TreeMap[String, Integer] = mutable.TreeMap(("C",1),("A",2),("D",3),("B",4))

      "print out the dictionary in sorted order" in {
        /*val stream = new java.io.ByteArrayOutputStream()
        Console.withOut(stream) {dictToString()}
        // windows: stream.toString should be("Word list:\r\na\r\nb\r\nc\r\nd\r\n")
        stream.toString*/ dictToString should be("Word list:\na\nb\nc\nd")
      }
      "print out the alphabet map in sorted order" in {
        /*val stream = new java.io.ByteArrayOutputStream()
        Console.withOut(stream) {
          printVector()
        }
        // windows: stream.toString should be("Alphabet vector:\r\n(A,2)\r\n(B,4)\r\n(C,1)\r\n(D,3)\r\n")
        stream.toString*/ vectorToString should be("Alphabet vector:\n(A,2)\n(B,4)\n(C,1)\n(D,3)")
      }
    }}
}
