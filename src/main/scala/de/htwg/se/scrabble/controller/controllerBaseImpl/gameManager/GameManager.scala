package de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager

import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}
import de.htwg.se.scrabble.model.player.Player
import de.htwg.se.scrabble.util.GameManagerStateObservable

trait GameManager extends GameManagerStateObservable {
  def start()
  def switchToNextState()
  override def toString: String
}

object GameManager {
  private class PreSetupManager(controller:ControllerInterface) extends GameManager {
    this.add(controller)
    override def start(): Unit = {}
    override def switchToNextState(): Unit = {
      controller.roundManager(new SetupManager(controller))
      controller.roundManager.start()
    }
    override def toString = "PreSetupManager"
  }

  private class SetupManager(controller:ControllerInterface) extends GameManager {
    this.add(controller)
    override def start(): Unit = {
      //controller.roundManager = this
      controller.players.put(Player("A", "human"))
      controller.players.put(Player("B", "human"))
      controller.activePlayer(controller.players.get("A"))
      controller.gameStatus(GameStatus.TWOP)
      //notifyObservers
      switchToNextState()
    }
    override def switchToNextState(): Unit = {
      controller.roundManager(new RoundManager(controller))
      controller.roundManager.start()
    }
    override def toString = "SetupManager"
  }

  private class RoundManager(controller:ControllerInterface) extends GameManager {
    this.add(controller)
    override def start(): Unit = {
      controller.fillHand()
    }
    override def switchToNextState(): Unit = {
      controller.roundManager(new GameOverManager(controller))
      controller.roundManager.start()
    }
    override def toString = "RoundManager"
  }

  private class GameOverManager(controller:ControllerInterface) extends GameManager {
    this.add(controller)
    override def start(): Unit = {
      controller.gameStatus(GameStatus.GAME_OVER)
      notifyObservers
    }
    override def switchToNextState(): Unit = {
      controller.roundManager(this)
      controller.roundManager.start()
    }
    override def toString = "GameOverManager"
  }

  def apply(s: String, ctl: ControllerInterface): GameManager = {
    s match {
      case "PreSetupManager" => new PreSetupManager(ctl)
      case "SetupManager" => new SetupManager(ctl)
      case "RoundManager" => new RoundManager(ctl)
      case "GameOverManager" => new GameOverManager(ctl)
      case _ => new RoundManager(ctl)
    }
  }

}
