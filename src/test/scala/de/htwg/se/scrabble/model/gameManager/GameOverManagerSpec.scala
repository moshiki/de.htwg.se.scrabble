package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller
import org.scalatest._

class GameOverManagerSpec extends WordSpec with Matchers{
  "A GameOverManager is a GameManager state and responsible for terminal tasks of the game" when {
    val ctl = Controller
    "switchToNextState is invoked" should {
      val gm: GameManager = new GameOverManager
      "do nothing and stay in game over state" in {
        ctl.roundManager = new GameOverManager
        ctl.roundManager shouldBe a [GameOverManager]
        gm.switchToNextState
        ctl.roundManager shouldBe a [GameOverManager]
      }
    }
  }
}