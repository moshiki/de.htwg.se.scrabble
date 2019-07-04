package de.htwg.se.scrabble.model.cards

import com.google.inject.Inject
import de.htwg.se.scrabble.model.CardStackInterface

case class Card (letter: String) {
  val value: String =
    if (isLetter(letter)) {letter}
    else "_"

  def isLetter(str: String): Boolean = str.matches("[A-Z#*]")

  override def toString = this.value

  def isEmpty: Boolean = true // TODO: Implement
}

object Card {
  import play.api.libs.json._
  implicit val cardWrites = OWrites[Card](_ => Json.obj())
  //implicit val cellReads = Reads[Cell](json =>
  //json.validate[JsObject].filter(_.values.isEmpty).map(_ => Cell()))
}
