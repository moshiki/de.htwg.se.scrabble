package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller
import org.scalatest._

class SetupManagerSpec extends WordSpec with Matchers{
  "A SetupManager is a GameManager state and responsible for setup tasks of the game" when {
    val ctl = Controller
    "start ist invoked" should {
      "create two players and update game status" in {
        val gm: GameManagerState = new SetupManagerState
        gm.start()
        ctl.players.getList.size should be(2)
      }
    }
  }
}