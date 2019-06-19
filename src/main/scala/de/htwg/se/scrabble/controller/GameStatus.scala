package de.htwg.se.scrabble.controller

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, ILLEGAL, LEGAL, OOBOUND, ONEP, TWOP, FILLHAND, GAME_OVER = Value

  val map: Map[GameStatus, String] = Map[GameStatus, String](
    IDLE -> "",
    ILLEGAL -> "illegal word",
    LEGAL -> "legal word",
    OOBOUND -> "coordinates exceed field size",
    ONEP -> "1 human player created",
    TWOP -> "2 human players created",
    FILLHAND -> "filled hands from card stack",
    GAME_OVER -> "\nGAME OVER -> Press 'new' for new game"
  )

  def message(gameStatus: GameStatus): String = map(gameStatus)
}
