package de.htwg.se.scrabble.controller

//import java.io.FileNotFoundException

import de.htwg.se.scrabble.model.Dictionary
import de.htwg.se.scrabble.util.Observable

class Controller extends Observable {
  private var dict = new Dictionary
//  try {
//    val dict = new Dictionary
//  } catch {
//    case fnf: FileNotFoundException => println(fnf.getMessage)
//  }

  def printDict(): Unit = {
    dict.printDict()
  }

  def printVector(): Unit = {
    dict.printVector()
  }

  def reloadDict(): Unit = {
    dict = new Dictionary
  }
}
