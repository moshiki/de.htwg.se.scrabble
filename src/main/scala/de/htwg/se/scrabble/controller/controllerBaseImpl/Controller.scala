package de.htwg.se.scrabble.controller.controllerBaseImpl

import de.htwg.se.scrabble.Scrabble.injector
import com.google.inject.Inject
import de.htwg.se.scrabble.controller._
import de.htwg.se.scrabble.controller.GameStatus.{GameStatus, IDLE}
import de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager.{GameManagerState, PreSetupManagerState, RoundManagerState, SetupManagerState}
import de.htwg.se.scrabble.controller.controllerBaseImpl.SetWordStrategy.{SetWordHorizontal, SetWordStrategy, SetWordVertical}
import de.htwg.se.scrabble.model.field.Cell
import de.htwg.se.scrabble.model.player.Player
import de.htwg.se.scrabble.model._
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.util.UndoManager

import scala.collection.immutable
import scala.collection.immutable.ListMap
import scala.swing.Publisher

case class Controller @Inject()(
  var field : FieldInterface ,
  var stack : CardInterface ,
  var players : PlayerListInterface ) extends ControllerInterface with Publisher {
  val dict = Dictionary
  var roundManager: GameManagerState = new PreSetupManagerState(this)
  var gameStatus: GameStatus = IDLE
  var activePlayer: Option[PlayerInterface] = None
  var firstDraw = true
  var undoManager = new UndoManager // private val

  newGame()

  def dictToString: String = dict.dictToString

  def vectorToString: String = dict.vectorToString

  override def newGame(): Unit = {
    field = injector.getInstance(classOf[FieldInterface])
    stack = injector.getInstance(classOf[CardInterface])
    players = injector.getInstance(classOf[PlayerListInterface])
    firstDraw = true
    roundManager = new SetupManagerState(this)
    roundManager.start()
    //notifyObservers
    publish(new AllChanged)
  }

  override def newPlayer(role:String, name:String): Unit = {
    players.put(Player(role, name))
    //notifyObservers
    publish(new PlayerChanged)
  }

  override def next(): Unit = {
    if (roundManager.isInstanceOf[RoundManagerState]) {
      undoManager.doStep(new NextCommand(inactivePlayer, activePlayer,this))
      roundManager = new RoundManagerState(this)
      roundManager.start()
      //notifyObservers
      publish(new AllChanged)
    }
  }

  override def fillHand(): Unit = {
    for (player <- players.getList) {
      undoManager.doStep(new FillHandCommand(player, stack, activePlayer, this))
    }
    gameStatus = GameStatus.FILLHAND
    publish(new AllChanged)
  }

  override def switchHand(): Boolean = {
    if (roundManager.isInstanceOf[RoundManagerState]) {
      if (!activePlayer.getOrElse(return false).switchedHand) {
        undoManager.doStep(new SwitchHandCommand(activePlayer, stack, this))
        //notifyObservers
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
    //notifyObservers
    publish(new CellChanged)
  }
  override def set(cell: Cell, value: String): Unit = {
    val coord = field.getCoordinates(cell).getOrElse(return)
    set(coord.col.toString, coord.row, value)
  }
  override def set(placementMap: ListMap[Cell, String], surroundingWords: List[String]): Unit = {
    undoManager.doStep(new SetWordCommand(placementMap, surroundingWords, activePlayer, firstDraw, this))
    //notifyObservers
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
        gameStatus = GameStatus.ILLEGAL
      }
    } else {
      gameStatus = GameStatus.ACTIONPERMIT
    }
    //notifyObservers
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
    //notifyObservers
    publish(new AllChanged)
  }
  override def redo(): Unit = {
    undoManager.redoStep
    //notifyObservers
    publish(new AllChanged)
  }

  override def update: Boolean = {//notifyObservers;
    publish(new AllChanged); true}
}
