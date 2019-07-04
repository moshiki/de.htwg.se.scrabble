/*
package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.model.player.Player

class GameManagerSpec {
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

  class PreSetupManagerSpec extends WordSpec with Matchers{
    "A PreSetupManager is a GameManager state and responsible for pre setup tasks of the game" when {
      val ctl = injector.getInstance(classOf[ControllerInterface])
      val gm: GameManager = GameManager("PreSetupManager", ctl)
      "start is invoked" should {
        "do nothing ans stay in game over state" in {
          gm.start()
        }
      }
      "switchToNextState is invoked" should {
        "hand over next state to gameManager" in {
          ctl.roundManager(gm)
          ctl.roundManager.toString shouldBe "PreSetupManager"
          gm.switchToNextState()
          ctl.roundManager.toString shouldBe "RoundManager"
        }
      }
    }
  }

  class RoundManagerSpec extends WordSpec with Matchers{
    "A RoundManager is a GameManager state and responsible for round tasks of the game" when {
      val ct = injector.getInstance(classOf[ControllerInterface])

      "start is invoked" should {
        "fill up hands of players and update game status" in {
          ct.players.put(Player("A", "Hermann"))
          ct.players.get("A").get.getNrCardsInHand should be(0)
          ct.roundManager(GameManager("RoundManager", ct))
          ct.roundManager.start()
          ct.players.get("A").get.getNrCardsInHand should be(7)
        }
      }
    }
  }

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

}
*/
