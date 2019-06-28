package de.htwg.se.scrabble.controller


import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller
import de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager.{GameOverManagerState, RoundManagerState}
import de.htwg.se.scrabble.util.Observer
import org.scalatest._

class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller
      val observer = new Observer {
        var updated: Boolean = false

        def isUpdated: Boolean = updated

        override def update: Boolean = {
          updated = true; updated
        }
      }
      controller.add(observer)
      "notify its Observer after been notified" in {
        //controller.reloadDict()
        controller.update
        observer.updated should be(true)
      }

    }
    "empty" should {
      val controller = new Controller
      "handle undo/redo correctly on an empty undo-stack" in {
        controller.field.getCell("A", 1).get.getValue should be("_")
        controller.undo
        controller.field.getCell("A", 1).get.getValue should be("_")
        controller.redo
        controller.field.getCell("A", 1).get.getValue should be("_")
      }
      "handle undo/redo of setting a cell correctly" in {
        controller.field.getCell("A", 1).get.getValue should be("_")
        controller.set("A", 1, "B")
        controller.field.getCell("A", 1).get.getValue should be("B")
        controller.undo
        controller.field.getCell("A", 1).get.getValue should be("_")
        controller.redo
        controller.field.getCell("A", 1).get.getValue should be("B")
      }
    }
  }
  "A controller" should {
    val controller = new Controller
    "return the word list of the dictionary as string with dictToString" in {
      controller.dictToString
    }
    "print the alphabet vector of the dictionary when printVector() is invoked" in {
      controller.vectorToString
    }
    "create a new player with role and name when newPlayer(role, name) is invoked and no player exists" in {
      val player = controller.newPlayer("A", "name")
      //player.role should be("A")
      //player.name should be("name")
    }
    "overwrite an existing player or do nothing when newPlayer(role, name) is invoked and a player already exists" in {
      val player1 = controller.newPlayer("A", "name")
      val player2 = controller.newPlayer("A", "newname")
    }

//    "return a random card (letter of the alphabet) when getCard is called an stack is not empty" in {
//      val card = controller.getCard
//      if (card.isDefined)
//        card.get.value should fullyMatch regex "[A-Z#*]"
//      else
//        card should be(None)
//    }

    "return a None when getCard is invoked and stack is empty" in {
      while (controller.stack.getSize > 0)
        controller.stack.getCard
      controller.getCard should be(None)
    }

    /*"start a new game when newGame is invoked" in {
      controller.newGame()
      controller.set("A", 1, "B")
      controller.field.getCell("A", 1).get.getValue should be("B")
      controller.newGame()
      controller.field.getCell("A", 1).get.getValue should be("_")
    }*/

    "go on to the next player when RoundManager is active and next is invoked" in {
      controller.newGame()
      controller.roundManager = new RoundManagerState(controller)
      val currPlayer = controller.activePlayer
      controller.next()
      controller.activePlayer should not be currPlayer
      controller.roundManager shouldBe a [RoundManagerState]
    }
    "do nothing when RoundManager is not active and next is invoked" in {
      controller.newGame()
      controller.roundManager = new GameOverManagerState(controller)
      val currPlayer = controller.activePlayer
      controller.next()
      controller.activePlayer should be(currPlayer)
    }
    "return the currently inactive player when inactivePlayer is invoked" in {
      controller.newGame()
      controller.activePlayer = controller.players.get("A")
      controller.inactivePlayer.get should be(controller.players.get("B").get)
    }
  }
}
