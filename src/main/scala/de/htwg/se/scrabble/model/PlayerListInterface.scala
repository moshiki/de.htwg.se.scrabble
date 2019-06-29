package de.htwg.se.scrabble.model

import de.htwg.se.scrabble.model.player.Player

trait PlayerListInterface {

  def put(player: Player) : Option[Player]
  def get(string: String) : Option[Player]
  def getList : List[Player]

}
