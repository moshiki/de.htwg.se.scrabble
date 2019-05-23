package de.htwg.se.scrabble.model.player

import org.scalatest._

class PlayerListSpec extends WordSpec with Matchers{
  "A Playerlist" when { "new" should {
    val listP = PlayerList
    val p1 = Player("A", "Mampfred")
    val p2 = Player("B", "Lukas")

    listP.put(p1)
    listP.put(p2)

    "there are 2 usable player" in {
      assert(listP.contains(p1))
      assert(listP.contains(p2))
    }

    "return the player list when getList is called" in {
      listP.getList should be(List(p1,p2))
    }

    "return players as string when toString is called" in {
      listP.toString should (include (p1.toString) and include (p2.toString))
    }

    "return the player when get(player) is called or None when player not exists" in {
      listP.get(p1.role) should be(Some(p1))
      listP.get(p2.role) should be(Some(p2))
      listP.get("c") should be(None)
    }
  }
  }
}
