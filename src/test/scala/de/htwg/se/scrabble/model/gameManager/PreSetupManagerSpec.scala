package de.htwg.se.scrabble.model.gameManager

import de.htwg.se.scrabble.controller.Controller
import org.scalatest._

class PreSetupManagerSpec extends WordSpec with Matchers{
  "A PreSetupManager is a GameManager state and responsible for pre setup tasks of the game" when {
    val ctl = Controller
    "switchToNextState is invoked" should {
      val gm: GameManager = new PreSetupManager
      "hand over next state to gameManager" in {
        ctl.roundManager = gm
        ctl.roundManager shouldBe a [PreSetupManager]
        gm.switchToNextState
        ctl.roundManager shouldBe a [SetupManager]
      }
    }
  }
}
