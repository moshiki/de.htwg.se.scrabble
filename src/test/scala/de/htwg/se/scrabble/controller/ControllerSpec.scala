package de.htwg.se.scrabble.controller


import de.htwg.se.scrabble.util.Observer
import org.scalatest._

class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller()
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Boolean = {updated = true; updated}
      }
      controller.add(observer)
      "notify its Observer after reload the dictionary" in {
        //controller.reloadDict()
        controller.notifyObservers
        observer.updated should be(true)
      }

    }
  }
  "A controller" should {
    val controller = new Controller()
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

    "return a random card (letter of the alphabet) when getCard is called an stack is not empty" in {
      val card = controller.getCard
      if (card.isDefined)
        card.get.value should fullyMatch regex "[A-Z#*]"
      else
        card should be(None)
    }

    "return a None when getCard is invoked and stack is empty" in {
      while (controller.stack.getSize > 0)
        controller.stack.getCard
      controller.getCard should be(None)
    }

    "start a new game when newGame is invoked" in {
      val oldfield = controller.field
      val oldstack = controller.stack
      controller.newGame()
      controller.field should not equal oldfield
      controller.stack should not equal oldstack
    }
  }
}
