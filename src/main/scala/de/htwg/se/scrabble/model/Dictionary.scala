package de.htwg.se.scrabble.model

import scala.collection.mutable
import scala.io.Source
import java.io.{File, FileNotFoundException}

import scala.collection.immutable

object Dictionary {
  private val directory = System.getProperty("user.dir")
  private val alphaPath = directory + "/src/main/resources/alphabet.txt"
  private val dictionaryPath = directory + "/src/main/resources/deutsch2000.txt"
  val alphabet: immutable.TreeMap[String, Integer] = loadAlphabet(alphaPath)
  val dict: immutable.HashSet[String] = loadDictionary(dictionaryPath)

  private def loadAlphabet(filePath: String): immutable.TreeMap[String, Integer] = {
    var alphaMap: immutable.TreeMap[String, Integer] = new immutable.TreeMap
    val file = new File(filePath)
    if (file.exists() && !file.isDirectory) {
      val br = Source.fromFile(file)
      for (line <- br.getLines) {
        val entry: Array[String] = line.split(" ")
        alphaMap += (entry(0) -> Integer.parseInt(entry(1)))
      }
      br.close()
      alphaMap
    } else { throw new FileNotFoundException("alphabet sourcefile in \'"+ alphaPath+"\' not found!") }
  }

  private def loadDictionary(filePath: String): immutable.HashSet[String] = {
    var dictSet: immutable.HashSet[String] = new immutable.HashSet
    val file = new File(filePath)
    if (file.exists() && !file.isDirectory) {
      val br = Source.fromFile(file)
      for (line <- br.getLines()) {
        dictSet += line
      }
      br.close()
      dictSet
    } else { throw new FileNotFoundException("alphabet source file in \'"+ dictionaryPath+"\' not found!\")")}
  }

  def dictToString: String = {
    val str = new mutable.StringBuilder()
    str.append("Word list:")
    this.dict.toStream.sorted.foreach(x=>str.append("\n"+x))
    str.toString()
  }

  def vectorToString: String = {
    val str = new mutable.StringBuilder()
    str.append("Alphabet vector:")
    this.alphabet.toSeq.sortBy(_._1).foreach(x=>str.append("\n"+x))
    str.toString()
  }
}
