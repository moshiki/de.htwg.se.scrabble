package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.Scrabble.injector
import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller
import org.scalatest._

class PreSetupManagerSpec extends WordSpec with Matchers{
  "A PreSetupManager is a GameManager state and responsible for pre setup tasks of the game" when {
    val ctl = injector.getInstance(classOf[ControllerInterface])
    val gm: GameManagerState = new PreSetupManagerState(ctl)
    "start is invoked" should {
      "do nothing ans stay in game over state" in {
        gm.start()
      }
    }
    "switchToNextState is invoked" should {
      "hand over next state to gameManager" in {
        ctl.roundManager = gm
        ctl.roundManager shouldBe a [PreSetupManagerState]
        gm.switchToNextState()
        ctl.roundManager shouldBe a [RoundManagerState]
      }
    }
  }
}
