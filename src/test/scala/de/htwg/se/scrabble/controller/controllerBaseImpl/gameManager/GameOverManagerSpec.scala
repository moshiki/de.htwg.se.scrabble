/*
package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.Scrabble.injector
import de.htwg.se.scrabble.controller.ControllerInterface
import org.scalatest._

class GameOverManagerSpec extends WordSpec with Matchers{
  "A GameOverManager is a GameManager state and responsible for terminal tasks of the game" when {
    val ctl = injector.getInstance(classOf[ControllerInterface])
    val gm: GameManager = GameManager("GameOverManager", ctl)
    "start is invoked" should {
      "do nothing ans stay in game over state" in {
        gm.start()
      }
    }
    "switchToNextState is invoked" should {
      "do nothing and stay in game over state" in {
        ctl.roundManager(GameManager("GameOverManager", ctl))
        ctl.roundManager.toString shouldBe "GameOverManager"
        gm.switchToNextState()
        ctl.roundManager.toString shouldBe "GameOverManager"
      }
    }
  }
}
*/
