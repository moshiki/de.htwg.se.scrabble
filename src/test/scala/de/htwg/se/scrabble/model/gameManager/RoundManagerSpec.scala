package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller
import de.htwg.se.scrabble.model.player.Player
import org.scalatest._

class RoundManagerSpec extends WordSpec with Matchers{
  "A RoundManager is a GameManager state and responsible for round tasks of the game" when {
    val ctl = new Controller()
    ctl.players.put(Player("A", "Hermann"))

    "is initialized" should {
      "fill up hands of players and update game status" in {
        ctl.players.get("A").get.getNrCardsInHand should be(0)
        ctl.roundManager = RoundManager(ctl)
        ctl.players.get("A").get.getNrCardsInHand should be(7)
      }
    }
    "return the currently inactive player when inactivePlayer is invoked" in {
      ctl.players.put(Player("B", "Karl"))
      val rm = RoundManager(ctl)
      ctl.roundManager = rm
      ctl.activePlayer = ctl.players.get("A").get
      rm.inactivePlayer should be(ctl.players.get("B").get)
    }
  }
}