package de.htwg.se.scrabble.model

import scala.collection.mutable
import scala.io.Source
import java.io.{File, FileNotFoundException}

class Dictionary {
  private val directory = System.getProperty("user.dir")
  private val alphaPath = directory + "\\src\\resources\\alphabet.txt"
  private val dictionaryPath = directory + "\\src\\resources\\deutsch2000.txt"
  val alphabet: mutable.HashMap[String, Integer] = loadAlphabet(alphaPath)
  val dict: mutable.HashSet[String] = loadDictionary(dictionaryPath)

  private def loadAlphabet(filePath: String): mutable.HashMap[String, Integer] = {
    val alphaMap: mutable.HashMap[String, Integer] = new mutable.HashMap
    val file = new File(filePath)
    if (file.exists() && !file.isDirectory) {
      val br = Source.fromFile(file)
      for (line <- br.getLines) {
        val entry: Array[String] = line.split(" ")
        alphaMap.put(entry(0), Integer.parseInt(entry(1)))
      }
      br.close()
      alphaMap
    } else { throw new FileNotFoundException("alphabet sourcefile in \'"+ alphaPath+"\' not found!") }
  }

  private def loadDictionary(filePath: String): mutable.HashSet[String] = {
    var dictSet: mutable.HashSet[String] = new mutable.HashSet
    val file = new File(filePath)
    if (file.exists() && !file.isDirectory) {
      val br = Source.fromFile(file)
      for (line <- br.getLines()) {
        dictSet += line
      }
      br.close()
      dictSet
    } else { throw new FileNotFoundException("alphabet sourcefile in \'"+ dictionaryPath+"\' not found!\")")}
  }



}
