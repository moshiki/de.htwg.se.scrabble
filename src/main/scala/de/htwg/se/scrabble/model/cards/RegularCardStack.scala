package de.htwg.se.scrabble.model.cards

import de.htwg.se.scrabble.model.CardStackInterface
import scala.collection.mutable.ListBuffer

class RegularCardStack extends CardStackInterface {
  private val cardCount: Map[String, Int] = Map("A"->6, "B"->2, "C"->4,"D"->6,"E"->16,"F"->3,"G"->3,"H"->5,"I"->9,"J"->1,
    "K"->2,"L"->4,"M"->4,"N"->10,"O"->4,"P"->1,"Q"->1,"R"->7,"S"->8,"T"->5,"U"->6,"V"->1,"W"->2,"X"->1,"Y"->1,"Z"->2/*,"#"->2*/)
  private var cardList: ListBuffer[Card] = ListBuffer()

  cardCount.foreach( x => for (i <- 1 to x._2) { cardList+=Card(x._1)})

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

object RegularCardStack {
  import play.api.libs.json._
  implicit val regularCardStackWrites = OWrites[RegularCardStack](_ => Json.obj())
  //implicit val cellReads = Reads[Cell](json =>
  //json.validate[JsObject].filter(_.values.isEmpty).map(_ => Cell()))
}
