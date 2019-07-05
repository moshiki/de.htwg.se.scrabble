package de.htwg.se.scrabble.controller.controllerBaseImpl.commands

import de.htwg.se.scrabble.controller.GameStatus.GameStatus
import de.htwg.se.scrabble.controller.StateCacheInterface
import de.htwg.se.scrabble.model.{CardStackInterface, FieldInterface, PlayerInterface, PlayerListInterface}
import play.api.libs.json.{JsValue, Json}

case class StateCache(field: FieldInterface,
                      stack: CardStackInterface,
                      players: PlayerListInterface,
                      roundManager: String,
                      gameStatus: GameStatus,
                      activePlayer: Option[PlayerInterface],
                      firstDraw: Boolean) extends StateCacheInterface {

  /*override def toJson: JsValue = Json.toJson(this)
  override def fromJson(jsValue: JsValue): StateCache = jsValue.validate[StateCache].asOpt.get*/
}

/*object StateCache {
  import play.api.libs.json._
  implicit val stateCacheWrites: OWrites[StateCache] = Json.writes[StateCache]
  implicit val stateCacheReads: Reads[StateCache] = Json.reads[StateCache]
}*/
