package de.htwg.se.scrabble.controller.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import de.htwg.se.scrabble.ScrabbleModule
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.controller.GameStatus.{GameStatus, IDLE}
import de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager.{GameManagerState, PreSetupManagerState, RoundManagerState, SetupManagerState}
import de.htwg.se.scrabble.model.cards.{Card, RegularCardStack}
import de.htwg.se.scrabble.model.field.RegularField
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import de.htwg.se.scrabble.model.{CardInterface, Dictionary, FieldInterface, PlayerInterface}
import de.htwg.se.scrabble.util.UndoManager

// TODO Traid erzeugen der alle funktionalitäten und zugriffe kürzt auf einen befehl von auserhalb
case class Controller @Inject() () extends ControllerInterface {
  val injektor = Guice.createInjector(new ScrabbleModule)
  var field = injektor.instance[FieldInterface]
  var players = injektor.instance[PlayerInterface]
  var stack = injektor.instance[RegularCardStack]
  //  var players: PlayerInterface = new PlayerList
  //  var field: FieldInterface = RegularField(15)
  //  var stack: CardInterface = new RegularCardStack
  val dict = Dictionary
  var gameStatus: GameStatus = IDLE
  var activePlayer: Option[Player] = None
  var roundManager: GameManagerState = new PreSetupManagerState
  private val undoManager = new UndoManager
  def dictToString: String = dict.dictToString
  def vectorToString: String = dict.vectorToString

  def newGame(): Unit = {
    field = RegularField(15)
    stack = new RegularCardStack
    roundManager = new SetupManagerState
    roundManager.start()
    //notifyObservers
  }

  def newPlayer(role:String, name:String): Unit = {
    players.put(Player(role, name))
    notifyObservers
  }


  override def next(): Unit = {
    if (roundManager.isInstanceOf[RoundManagerState]) {
      activePlayer = inactivePlayer
      roundManager = new RoundManagerState
      roundManager.start()
      notifyObservers
    }
  }

  def inactivePlayer: Option[Player] = {
    if (activePlayer.get == players.get("A").get) {
      players.get("B")
    } else {
      players.get("A")
    }
  }

  def getCard: Option[Card] = {
    stack.getCard
  }

  override def set(x: String, y: Int, value: String): Unit = {
    undoManager.doStep(new SetCommand(x, y, value, activePlayer))
    notifyObservers
  }
  override def undo(): Unit = {
    undoManager.undoStep
    notifyObservers
  }
  override def redo(): Unit = {
    undoManager.redoStep
    notifyObservers
  }

  override def update: Boolean = {notifyObservers; true}
}
