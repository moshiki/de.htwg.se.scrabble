package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.controller.GameStatus.GameStatus
import org.scalatest._

class GameStatusSpec extends WordSpec with Matchers{
  "A GameStatus is an enumeration object to handle different states in the game" when {
    val gs: GameStatus = GameStatus.IDLE
    "message is invoked" should {
      "return the matching status as string" in {
        GameStatus.message(GameStatus.LEGAL) should be("legal word")
      }
    }
  }
}
