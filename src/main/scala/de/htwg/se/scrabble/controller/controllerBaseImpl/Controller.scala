package de.htwg.se.scrabble.controller.controllerBaseImpl

import de.htwg.se.scrabble.Scrabble.injector
import com.google.inject.Inject
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.scrabble.controller._
import de.htwg.se.scrabble.controller.GameStatus._
import de.htwg.se.scrabble.controller.controllerBaseImpl.SetWordStrategy.{SetWordHorizontal, SetWordStrategy, SetWordVertical}
import de.htwg.se.scrabble.controller.controllerBaseImpl.commands._
import de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager.GameManager
import de.htwg.se.scrabble.model._
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.model.field.Cell
import de.htwg.se.scrabble.model.fileIoComponent.FileIOInterface
import de.htwg.se.scrabble.model.player.Player
import de.htwg.se.scrabble.util.UndoManager

import scala.collection.immutable
import scala.collection.immutable.ListMap
import scala.swing.Publisher

class Controller @Inject() (var field : FieldInterface,
                            var stack : CardStackInterface,
                            var players : PlayerListInterface ) extends ControllerInterface with Publisher {
  val fileIo: FileIOInterface = injector.instance[FileIOInterface]
  val dict = Dictionary
  var roundManager: GameManager = GameManager("PreSetupManager", this)
  var gameStatus: GameStatus = IDLE
  var activePlayer: Option[PlayerInterface] = None
  var firstDraw = true
  private var undoManager = new UndoManager

  newGame()

  def dictToString: String = dict.dictToString

  def vectorToString: String = dict.vectorToString

  override def newGame(): Unit = {
    field = injector.instance[FieldInterface]
    stack = injector.instance[CardStackInterface]
    players = injector.instance[PlayerListInterface]
    firstDraw = true
    roundManager = GameManager("SetupManager", this)
    undoManager = new UndoManager
    roundManager.start()
    //notifyObservers
    publish(new NewGame)
  }

  override def save: Unit = {
    fileIo.save(this.getStateCache)
    gameStatus = SAVED
    publish(new AllChanged)
  }

  override def load: Unit = {
    val states = fileIo.load
    field = states.field
    stack = states.stack
    players = states.players
    firstDraw = states.firstDraw
    activePlayer = states.activePlayer
    roundManager = GameManager(states.roundManager, this)
    undoManager = new UndoManager
    gameStatus = LOADED
    publish(new NewGame)
  }
  override def newPlayer(role:String, name:String): Unit = {
    players.put(Player(role, name))
    //notifyObservers
    publish(new PlayerChanged)
  }

  override def next(): Unit = {
    if (roundManager.toString == "RoundManager") {
      undoManager.doStep(new NextCommand(inactivePlayer, activePlayer,this))
      roundManager = GameManager("RoundManager", this)
      roundManager.start()
      publish(new NextPlayer)
    }
  }

  override def fillHand(): Unit = {
    for (player <- players.getList) {
      undoManager.doStep(new FillHandCommand(player, stack, activePlayer, this))
    }
    gameStatus = FILLHAND
    publish(new AllChanged)
  }

  override def switchHand(): Boolean = {
    if (roundManager.toString == "RoundManager") {
      if (!activePlayer.getOrElse(return false).switchedHand) {
        undoManager.doStep(new SwitchHandCommand(activePlayer, stack, this))
        publish(new AllChanged)
        return true
      }
    }
    false
  }

  override def inactivePlayer: Option[PlayerInterface] = {
    if (activePlayer.get == players.get("A").get) {
      players.get("B")
    } else {
      players.get("A")
    }
  }

  def getCard: Option[Card] = {
    stack.getCard
  }

  override def getDict: immutable.HashSet[String] = dict.dict

  override def getAlphabet: immutable.TreeMap[String, Integer] = dict.alphabet

  override def set(x: String, y: Int, value: String): Unit = {
    undoManager.doStep(new SetCommand(x, y, value, activePlayer, this))
    publish(new CellChanged)
  }
  override def set(cell: Cell, value: String): Unit = {
    val coord = field.getCoordinates(cell).getOrElse(return)
    set(coord.col.toString, coord.row, value)
  }
  override def set(placementMap: ListMap[Cell, String], surroundingWords: List[String]): Unit = {
    undoManager.doStep(new SetWordCommand(placementMap, surroundingWords, activePlayer, firstDraw, this))
    publish(new AllChanged)
  }

  override def setWord(parameters: Array[String]): Unit = {
    if (parameters.length != 4) return
    if (activePlayer.get.actionPermitted) {
      val x: String = parameters(1).charAt(0).toString.toUpperCase()
      val y: Int = parameters(1).substring(1).toInt
      val alignment: SetWordStrategy = {
        if (parameters(2).matches("[-]")) new SetWordHorizontal(this)
        else if (parameters(2).matches("[|]")) new SetWordVertical(this)
        else return
      }
      val word: String = if (parameters(3).matches("[A-Za-z#]+")) parameters(3).toUpperCase() else return
      val cell: Cell = if (field.getCell(x, y).isDefined) field.getCell(x, y).get else return

      if (word.length >= 2 && getDict.contains(word.toUpperCase())) {
        alignment.setWord(word, cell, x, y)
      } else {
        gameStatus = ILLEGAL
      }
    } else {
      gameStatus = ACTIONPERMIT
    }
    publish(new AllChanged)
  }

  override def evalPoints(encounteredWords: List[String]): Int = {
    var points = 0
    for (word <- encounteredWords) {
      for (c <- word) {
        points += dict.alphabet(c.toString)
      }
    }
    points
  }

  override def undo(): Unit = {
    undoManager.undoStep
    publish(new AllChanged)
  }
  override def redo(): Unit = {
    undoManager.redoStep
    publish(new AllChanged)
  }

  override def update: Boolean = {publish(new AllChanged); true}

  override def activePlayer(player: Option[PlayerInterface]): Unit = this.activePlayer = player

  override def firstDraw(bool: Boolean): Unit = this.firstDraw = bool

  override def gameStatus(gs: GameStatus): Unit = this.gameStatus = gs

  override def roundManager(rm: GameManager): Unit = this.roundManager = rm

  override def getStateCache: StateCacheInterface = {
    StateCache(field,stack,players,roundManager.toString,gameStatus,activePlayer,firstDraw)
  }
}
