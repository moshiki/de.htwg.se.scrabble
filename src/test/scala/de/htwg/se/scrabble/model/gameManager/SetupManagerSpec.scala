package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller
import org.scalatest._

class SetupManagerSpec extends WordSpec with Matchers{
  "A SetupManager is a GameManager state and responsible for setup tasks of the game" when {
    val ctl = new Controller()
    "is initialized" should {
      "create two players and update game status" in {
        val gm: GameManager = SetupManager(ctl)
        ctl.players.getList.size should be(2)
      }
    }
  }
}