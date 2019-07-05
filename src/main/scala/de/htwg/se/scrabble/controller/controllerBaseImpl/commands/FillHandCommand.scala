package de.htwg.se.scrabble.controller.controllerBaseImpl.commands

import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.model.{CardStackInterface, PlayerInterface}
import de.htwg.se.scrabble.util.Command

import scala.collection.mutable.ListBuffer

class FillHandCommand(player: PlayerInterface, stack: CardStackInterface, activePlayer: Option[PlayerInterface], controller: ControllerInterface) extends Command {
  private val cardList = ListBuffer[Card]()

  override def doStep: Unit = {
    cardList.clear()
    while (!stack.isEmpty && player.getNrCardsInHand < 7) {
      cardList += stack.getCard.get
      player.addToHand(cardList.last)
    }
    controller.activePlayer(activePlayer)
  }
  override def undoStep: Unit = {
    for (card <- cardList) {
      player.putCard(card)
      stack.putCard(card)
    }
    cardList.clear()
    controller.activePlayer(Some(player))
  }

  override def redoStep: Unit = {
    doStep
  }
}

