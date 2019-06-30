package de.htwg.se.scrabble.controller.controllerBaseImpl

import de.htwg.se.scrabble.Scrabble.injector
import com.google.inject.Inject
import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}
import de.htwg.se.scrabble.controller.GameStatus.{GameStatus, IDLE}
import de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager.{GameManagerState, PreSetupManagerState, RoundManagerState, SetupManagerState}
import de.htwg.se.scrabble.controller.controllerBaseImpl.SetWordStrategy.{SetWordHorizontal, SetWordStrategy, SetWordVertical}
import de.htwg.se.scrabble.model.field.{Cell, RegularField}
import de.htwg.se.scrabble.model.player.Player
import de.htwg.se.scrabble.model._
import de.htwg.se.scrabble.util.UndoManager

import scala.collection.immutable
import scala.collection.immutable.ListMap

case class Controller @Inject() (
  var field : FieldInterface ,
  var stack : CardInterface ,
  var players : PlayerListInterface ) extends ControllerInterface {

  val dict = Dictionary

  var roundManager: GameManagerState = new PreSetupManagerState(this)
  var gameStatus: GameStatus = IDLE
  var activePlayer: Option[PlayerInterface] = None
  private val undoManager = new UndoManager

  def dictToString: String = dict.dictToString

  def vectorToString: String = dict.vectorToString

  def newGame(): Unit = {
    field = RegularField(15)
    stack = injector.getInstance(classOf[CardInterface])
    players = injector.getInstance(classOf[PlayerListInterface])
    roundManager = new SetupManagerState(this)
    roundManager.start()
    notifyObservers
  }

  def newPlayer(role:String, name:String): Unit = {
    players.put(Player(role, name))
    notifyObservers
  }


  override def next(): Unit = {
    if (roundManager.isInstanceOf[RoundManagerState]) {
      activePlayer = inactivePlayer
      roundManager = new RoundManagerState(this)
      roundManager.start()
      notifyObservers
    }
  }

  def inactivePlayer: Option[PlayerInterface] = {
    if (activePlayer.get == players.get("A").get) {
      players.get("B")
    } else {
      players.get("A")
    }
  }

  def getCard: Option[CardInterface] = {
    stack.getCard
  }

  def getDict: immutable.HashSet[String] = dict.dict

  def getAlphabet: immutable.TreeMap[String, Integer] = dict.alphabet

  override def set(x: String, y: Int, value: String): Unit = {
    undoManager.doStep(new SetCommand(x, y, value, activePlayer, this))
    notifyObservers
  }
  override def set(cell: Cell, value: String): Unit = {
    val coord = field.getCoordinates(cell).getOrElse(return)
    set(coord.col.toString, coord.row, value)
  }
  override def set(placementMap: ListMap[Cell, String], surroundingWords: List[String]): Unit = {
    undoManager.doStep(new SetWordCommand(placementMap, surroundingWords, activePlayer, this))
    notifyObservers
  }

  override def setWord(parameters: Array[String]): Unit = {
    if (parameters.length != 4) return

    val x: String = parameters(1).charAt(0).toString.toUpperCase()
    val y: Int = parameters(1).substring(1).toInt
    val alignment: SetWordStrategy = {
      if (parameters(2).matches("[-]")) new SetWordHorizontal(this)
      else if (parameters(2).matches("[|]")) new SetWordVertical(this)
      else return}
    val word: String = if (parameters(3).matches("[A-Za-z#]+")) parameters(3).toUpperCase() else return
    val cell: Cell = if (field.getCell(x, y).isDefined) field.getCell(x, y).get else return

    if (word.length >= 2 && getDict.contains(word.toUpperCase())) {
      alignment.setWord(word, cell, x, y)
    } else {
      gameStatus = GameStatus.ILLEGAL
    }
    notifyObservers
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
    notifyObservers
  }
  override def redo(): Unit = {
    undoManager.redoStep
    notifyObservers
  }

  override def update: Boolean = {notifyObservers; true}
}
