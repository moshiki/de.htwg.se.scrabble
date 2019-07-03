package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.Scrabble.injector
import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller
import org.scalatest._

class SetupManagerSpec extends WordSpec with Matchers{
  "A SetupManager is a GameManager state and responsible for setup tasks of the game" when {
    val ctl = injector.getInstance(classOf[ControllerInterface])
    "start ist invoked" should {
      "create two players and update game status" in {
        val gm: GameManager = GameManager("SetupManager", ctl)
        gm.start()
        ctl.players.getList.size should be(2)
      }
    }
  }
}