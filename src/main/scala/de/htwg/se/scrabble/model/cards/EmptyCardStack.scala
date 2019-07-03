package de.htwg.se.scrabble.model.cards

import de.htwg.se.scrabble.model.CardStackInterface

import scala.collection.mutable.ListBuffer

class EmptyCardStack extends CardStackInterface {
  private var cardList: ListBuffer[Card] = ListBuffer()

  override def getCard: Option[Card] = {
    if (cardList.nonEmpty) {
      Some(cardList.remove(util.Random.nextInt(cardList.size)))
    } else
      None
  }

  override def getSpecificCard(card:Card): Option[Card] = {
    if (cardList.contains(card)) {
      cardList -= card
      Some(card)
    } else
      None
  }

  override def putCard(card:Card): Unit = {
    cardList += card
  }

  override def getStack: List[Card] = {
    cardList.toList
  }

  override def getSize: Int = {cardList.size}

  override def isEmpty: Boolean = cardList.isEmpty
}

object EmptyCardStack {
  import play.api.libs.json._
  implicit val emptyCardStackWrites = OWrites[EmptyCardStack](_ => Json.obj())
  //implicit val cellReads = Reads[Cell](json =>
  //json.validate[JsObject].filter(_.values.isEmpty).map(_ => Cell()))
}
