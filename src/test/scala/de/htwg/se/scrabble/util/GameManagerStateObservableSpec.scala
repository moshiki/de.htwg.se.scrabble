package de.htwg.se.scrabble.util

import org.scalatest.{Matchers, WordSpec}

class GameManagerStateObservableSpec extends WordSpec with Matchers {
  "An Observable" should {
    val gmsobservable = new GameManagerStateObservable
    val observer = new  Observer {
      var updated: Boolean = false

      def isUpdated: Boolean = updated

      override def update: Boolean = {
        updated = true; updated
      }
    }
    "add an Observer" in {
      gmsobservable.add(observer)
      gmsobservable.subscribers should contain(observer)
    }
    "notify an Observer" in {
      observer.isUpdated should be(false)
      gmsobservable.notifyObservers
      observer.isUpdated should be(true)
    }
    "remove an Observer" in {
      gmsobservable.remove(observer)
      gmsobservable.subscribers should not contain observer
    }
  }
  }
