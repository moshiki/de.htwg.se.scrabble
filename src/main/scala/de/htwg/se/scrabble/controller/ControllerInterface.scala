package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.controller.GameStatus.GameStatus
import de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager.GameManager
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.model.field.Cell
import de.htwg.se.scrabble.model.{CardStackInterface, FieldInterface, PlayerInterface, PlayerListInterface}
import de.htwg.se.scrabble.util.{Observable, Observer}

import scala.collection.immutable
import scala.collection.immutable.ListMap

trait ControllerInterface extends Observable with Observer{

  def undo()
  def redo()
  def next()
  def set(x: String, y: Int, value: String)
  def set(cell:Cell, value:String)
  def set(placementMap: ListMap[Cell, String], surroundingWords: List[String])
  def setWord(parameters: Array[String]): Unit
  def newGame()
  def dictToString : String
  def getDict: immutable.HashSet[String]
  def getAlphabet: immutable.TreeMap[String, Integer]
  def getCard: Option[Card]
  def vectorToString : String
  def players : PlayerListInterface
  def newPlayer(role:String, name:String)
  def fillHand(): Unit
  def switchHand(): Boolean
  def inactivePlayer: Option[PlayerInterface]
  def evalPoints(words: List[String]): Int
  def activePlayer : Option[PlayerInterface]
  def activePlayer(player: Option[PlayerInterface])
  def firstDraw: Boolean
  def firstDraw(bool: Boolean)
  def field : FieldInterface
  def stack : CardStackInterface
  def gameStatus : GameStatus
  def gameStatus(gs: GameStatus)
  def roundManager: GameManager
  def roundManager(rm: GameManager)
  def getStateCache(): StateCacheInterface
}
trait StateCacheInterface {
  def field : FieldInterface
  def stack : CardStackInterface
  def players : PlayerListInterface
  def roundManager: GameManager
  def gameStatus : GameStatus
  def activePlayer : Option[PlayerInterface]
  def firstDraw: Boolean
}
