package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.controller.GameStatus.GameStatus
import de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager.GameManagerState
import de.htwg.se.scrabble.model.field.Cell
import de.htwg.se.scrabble.model.player.Player
import de.htwg.se.scrabble.model.{CardInterface, FieldInterface, PlayerInterface, PlayerListInterface}
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
  def getCard: Option[CardInterface]
  def vectorToString : String
  def players : PlayerListInterface
  def newPlayer(role:String, name:String)
  def inactivePlayer: Option[PlayerInterface]
  def evalPoints(words: List[String]): Int
  var activePlayer : Option[Player]
  var field : FieldInterface
  var stack : CardInterface
  var gameStatus : GameStatus
  var roundManager: GameManagerState

}
