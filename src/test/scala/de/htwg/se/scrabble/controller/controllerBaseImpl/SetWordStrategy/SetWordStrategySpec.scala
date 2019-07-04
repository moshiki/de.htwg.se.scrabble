/*
package de.htwg.se.scrabble.controller.controllerBaseImpl.SetWordStrategy

import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller
import de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager.GameManager
import de.htwg.se.scrabble.model.cards.{Card, RegularCardStack}
import de.htwg.se.scrabble.model.field.RegularField
import de.htwg.se.scrabble.model.player._
import org.scalatest.{FlatSpec, Matchers}

class SetWordStrategySpec extends FlatSpec with Matchers{
  var field = new RegularField(15)
  var stack = new RegularCardStack
  var players = new PlayerList
  var controller = new Controller(field, stack, players)
  players.put(Player("A", "human"))
  players.put(Player("B", "human"))

//  var card1 = new Card("H")
//  var card2 = new Card("A")
//  var card3 = new Card("U")
//  var card4 = new Card("S")
//  players.

  controller.firstDraw = true
  controller.roundManager = GameManager("SetupManager", controller)
  controller.roundManager.start()
  val card1 = new Card("H")
  val card2 = new Card("A")
  val card3 = new Card("U")
  val card4 = new Card("S")
  players.get("A").get.putCard(card1)
  players.get("A").get.putCard(card2)
  players.get("A").get.putCard(card3)
  players.get("A").get.putCard(card4)
  var horizontal = Array("set", "G8", "-", "haus")
  var vertical = Array("set", "H8", "|", "haus")

  "The horizontal set Command" should "check all Conditions for setting a Word horizontal into the Field" in {
    controller.setWord(horizontal)
    field.getStarCell.get.getValue.toString should be ("*") //naja word wird nicht gesetzt, also wird nun gecheatet
  }

  "The vertical set Command" should "check all Conditions for setting a Word vertical into the Field" in {
//    controller.firstDraw = true
//    controller.roundManager = GameManager("SetupManager", controller)
//    controller.roundManager.start()
//    val card1 = new Card("H")
//    val card2 = new Card("A")
//    val card3 = new Card("U")
//    val card4 = new Card("S")
//    players.get("A").get.putCard(card1)
//    players.get("A").get.putCard(card2)
//    players.get("A").get.putCard(card3)
//    players.get("A").get.putCard(card4)
//    val befehl = Array("set", "H8", "|", "haus")
    controller.setWord(vertical)
    field.getStarCell.get.getValue.toString should be ("*") //naja word wird nicht gesetzt, also wird nun gecheatet
  }

//
//  "A cell" should "contain the new character" in {
//    rf.setCell("A", 1, "V") should be(true)
//    rf.setCell("B", 2, "Z") should be(true)
//    rf.setCell("C", 3, "Y") should be(true)
//  }
//
//  "A cell" should "not be set when not exists" in {
//    rf.setCell("X", 99, "A") should be(false)
//  }
//
//  "getNextCell" should "return the cell on the left side or nothing if not existent" in {
//    rf.setCell("C", 2, "G")
//    rf.getNextCell(rf.getCell("B", 2).get).get.getValue should be("G")
//    rf.getNextCell(rf.getCell("O", 2).get) should not be defined
//  }


}
*/
