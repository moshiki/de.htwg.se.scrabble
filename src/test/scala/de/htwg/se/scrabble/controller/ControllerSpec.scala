package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.model.Dictionary
import de.htwg.se.scrabble.util.Observer
import org.scalatest._

class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller()
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update = {updated = true; updated}
      }
      controller.add(observer)
      "notify its Observer after reload the dictionary" in {
        controller.reloadDict()
        observer.updated should be(true)
      }

    }
  }
  "A controller" should {
    val controller = new Controller()
    "print the word list of the dictionary when printDict() is invoked" in {
      controller.printDict()
    }
    "print the alphabet vector of the dictionary when printVector() is invoked" in {
      controller.printVector()
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

    "return a random card (letter of the alphabet) when getCard is invoked" in {
      controller.getCard should fullyMatch regex "[a-z]"
    }

  }
}
