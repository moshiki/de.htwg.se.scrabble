package de.htwg.se.scrabble.controller.controllerBaseImpl

import de.htwg.se.scrabble.controller.GameStatus.GameStatus
import de.htwg.se.scrabble.controller.StateCacheInterface
import de.htwg.se.scrabble.model.{CardStackInterface, FieldInterface, PlayerInterface, PlayerListInterface}

case class StateCache(field: FieldInterface,
                      stack: CardStackInterface,
                      players: PlayerListInterface,
                      roundManager: String,
                      gameStatus: GameStatus,
                      activePlayer: Option[PlayerInterface],
                      firstDraw: Boolean) extends StateCacheInterface
