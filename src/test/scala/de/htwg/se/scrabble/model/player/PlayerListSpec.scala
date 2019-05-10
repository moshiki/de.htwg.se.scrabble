package de.htwg.se.scrabble.model.player

import org.scalatest._

class PlayerListSpec extends WordSpec with Matchers{
  "A Playerlist" when { "new" should {
    val listP = new PlayerList
    val p1 = Player("A", "Mampfred")
    val p2 = Player("B", "Lukas")

    listP.put(p1)
    listP.put(p2)

    "there are 2 usable player" in {
      assert(listP.contains(p1))
      assert(listP.contains(p2))
    }

    "have a Liste of the two Player" in {
      listP.getList should be(List(p1,p2))
     // listP.print() should be("MASSD")
    }

   // "Blaaa" in {
    //  listP.remove(p1)
   //   assert(listP.contains(p1))
  //  }
  }
  }
}
