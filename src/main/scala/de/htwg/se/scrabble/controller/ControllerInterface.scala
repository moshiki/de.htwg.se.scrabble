package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.controller.GameStatus.GameStatus
import de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager.GameManagerState
import de.htwg.se.scrabble.model.field.Cell
import de.htwg.se.scrabble.model.{CardInterface, FieldInterface, PlayerInterface}
import de.htwg.se.scrabble.model.player.Player
import de.htwg.se.scrabble.util.{Observable, Observer}

import scala.collection.immutable

trait ControllerInterface extends Observable with Observer{

  def undo()
  def redo()
  def next()
  def set(x: String, y: Int, value: String)
  def set(cell:Cell, value:String)
  def newGame()
  def dictToString() : String
  def getDict: immutable.HashSet[String]
  def getCard: Option[CardInterface]
  def vectorToString() : String
  def players() : PlayerInterface
  def newPlayer(role:String, name:String)
  def inactivePlayer: Option[Player]
  var activePlayer : Option[Player]
  var field : FieldInterface
  var stack : CardInterface
  var gameStatus : GameStatus
  var roundManager: GameManagerState

}
