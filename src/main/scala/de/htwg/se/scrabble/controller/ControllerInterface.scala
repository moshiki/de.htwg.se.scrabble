package de.htwg.se.scrabble.controller

import de.htwg.se.scrabble.controller.GameStatus.GameStatus
import de.htwg.se.scrabble.model.{CardInterface, FieldInterface, PlayerInterface}
import de.htwg.se.scrabble.model.player.{Player, PlayerList}
import de.htwg.se.scrabble.util.{Observable, Observer}

trait ControllerInterface extends Observable with Observer{

  def undo()
  def redo()
  def next()
  def set(x: String, y: Int, value: String)
  def newGame()
  def dictToString() : String
  def vectorToString() : String
  def players() : PlayerInterface
  def activePlayer() : Option[Player]
  def field() : FieldInterface
  var stack : CardInterface
  var gameStatus : GameStatus

}
