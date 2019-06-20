package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller
import org.scalatest._

class GameOverManagerSpec extends WordSpec with Matchers{
  "A GameOverManager is a GameManager state and responsible for terminal tasks of the game" when {
    val ctl = Controller
    val gm: GameManagerState = new GameOverManagerState
    "start is invoked" should {
      "do nothing ans stay in game over state" in {
        gm.start()
      }
    }
    "switchToNextState is invoked" should {
      "do nothing and stay in game over state" in {
        ctl.roundManager = new GameOverManagerState
        ctl.roundManager shouldBe a [GameOverManagerState]
        gm.switchToNextState()
        ctl.roundManager shouldBe a [GameOverManagerState]
      }
    }
  }
}