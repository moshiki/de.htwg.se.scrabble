package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller
import de.htwg.se.scrabble.model.player.Player
import org.scalatest._

class RoundManagerSpec extends WordSpec with Matchers{
  "A RoundManager is a GameManager state and responsible for round tasks of the game" when {
    val ct = Controller

    "start is invoked" should {
      "fill up hands of players and update game status" in {
        ct.players.put(Player("A", "Hermann"))
        ct.players.get("A").get.getNrCardsInHand should be(0)
        ct.roundManager = new RoundManagerState
        ct.roundManager.start()
        ct.players.get("A").get.getNrCardsInHand should be(7)
      }
    }
  }
}