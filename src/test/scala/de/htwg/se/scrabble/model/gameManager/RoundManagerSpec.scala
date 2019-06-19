package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller
import de.htwg.se.scrabble.model.RegularField
import de.htwg.se.scrabble.model.cards.RegularCardStack
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import org.scalatest._

class RoundManagerSpec extends WordSpec with Matchers{
  "A RoundManager is a GameManager state and responsible for round tasks of the game" when {
    val ctl = new Controller()
    /*ctl.field = RegularField(15, ctl)
    ctl.stack = newRegularCardStack
    ctl.players = new PlayerList*/
    ctl.players.put(Player("A", "Hermann"))

    "is initialized" should {
      "fill up hands of players and update game status" in {
        ctl.players.get("A").get.getNrCardsInHand should be(0)
        ctl.roundManager = RoundManager(ctl)
        ctl.players.get("A").get.getNrCardsInHand should be(7)
      }
    }
  }
}