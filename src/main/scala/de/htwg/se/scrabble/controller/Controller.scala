package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.model.Dictionary
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import de.htwg.se.scrabble.util.Observable

import scala.io.StdIn.readLine

class Controller extends Observable {
  private var dict = new Dictionary
  val players = new PlayerList

  var state: ControllerState = initState(this)

  def getCurrentStateAsString(): String = state.getCurrentStateAsString()

  def nextState(): Unit = state = state.nextState()

  def switchNextState():Unit = nextState()

  def handle(input: String): Unit = {
    state.eval(input)
  }


  abstract class ControllerState {

    def printDict(): Unit = dict.printDict()

    def printVector(): Unit = dict.printVector()

    def eval(input: String)

    def nextState(): ControllerState

    def getCurrentStateAsString(): String

  }

  case class initState(controller:Controller) extends ControllerState {
    override def eval(input: String) = {

    }

    override def nextState(): ControllerState = setupState(controller)

    def reloadDict(): Unit = {
      dict = new Dictionary
      notifyObservers
    }

    override def getCurrentStateAsString(): String = {
      "state"
    }

    def newPlayer(role: String, name: String): Option[Player] = {
      val player = Player(role, name)
      val oldPlayer = players.get(player.role)
      oldPlayer match {
        case Some(p) =>
          println("overwrite existing player: " + p + "? Y, N")
          readLine(">> ") match {
            case "y" | "Y" =>
              notifyObservers
              players.put(player)
              Option(player)
            case other => None
          }
        case None =>
          notifyObservers
          players.put(player)
          Option(player)
      }
    }

    def getCard: String = {
      val r = util.Random.nextInt(26)
      val arr = dict.alphabet.toArray
      arr(r)._1
    }
  }

  case class setupState(controller:Controller) extends ControllerState {
    override def eval(input: String) = {

    }

    override def getCurrentStateAsString(): String = {

    }
  }
}
