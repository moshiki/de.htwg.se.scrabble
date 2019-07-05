package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.controllerBaseImpl.SetWordStrategy.{SetWordHorizontal, SetWordVertical}
import de.htwg.se.scrabble.controller.controllerBaseImpl.{Controller, SetCommand}
import de.htwg.se.scrabble.model.cards.{Card, RegularCardStack}
import de.htwg.se.scrabble.model.field.RegularField
import de.htwg.se.scrabble.model.player._
import org.scalatest.{FlatSpec, Matchers}

class SetWordSpec extends FlatSpec with Matchers{
  var field = new RegularField(15)
  var stack = new RegularCardStack
  var players = new PlayerList
  var controller = new Controller(field, stack, players)

//  var card1 = new Card("H")
//  var card2 = new Card("A")
//  var card3 = new Card("U")
//  var card4 = new Card("S")
//  players.

  players.put(Player("A", "human"))
  players.put(Player("B", "human"))
  controller.firstDraw = true
  controller.activePlayer(players.get("A"))
  var horizontal = Array("set", "G8", "-", "haus")
  var vertical = Array("set", "H8", "|", "haus")
  val setHoriz = new SetWordHorizontal(controller)
  val setVert = new SetWordVertical(controller)


  "The horizontal set Command" should "check all Conditions for setting a Word horizontal into the Field" in {
    field.getStarCell.get.getValue.toString should be ("*")

    players.get("A").get.addToHand(Card("H"))
    players.get("A").get.addToHand(Card("A"))
    players.get("A").get.addToHand(Card("U"))
    players.get("A").get.addToHand(Card("S"))
    players.get("A").get.grantActionPermit()
    setHoriz.setWord("haus", field.getCell("H", 8).get, "H", 8)
    //players.get("A").get.addToHand(Card("H"))
    //setHoriz.setWord("haus", field.getCell("H", 8).get, "H", 8)
    players.get("A").get.addToHand(Card("B"))
    players.get("A").get.addToHand(Card("A"))
    players.get("A").get.addToHand(Card("U"))
    players.get("A").get.addToHand(Card("M"))
    players.get("A").get.grantActionPermit()
    setVert.setWord("baum", field.getCell("I", 7).get, "I", 7)
    //players.get("A").get.addToHand(Card("H"))
//    setVert.setWord("baum", field.getCell("I", 7).get, "I", 7)
    controller.undo()
    controller.redo()


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
    field = new RegularField(15)
    field.getStarCell.get.getValue.toString should be ("*")
    controller.switchHand()
    controller.undo()
    controller.redo()
    controller.evalPoints(List("HAUS"))


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
