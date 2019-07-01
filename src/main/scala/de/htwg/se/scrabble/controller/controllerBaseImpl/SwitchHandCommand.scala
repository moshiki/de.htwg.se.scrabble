package de.htwg.se.scrabble.controller.controllerBaseImpl

import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.model.cards.Card
import de.htwg.se.scrabble.model.{CardInterface, PlayerInterface}
import de.htwg.se.scrabble.util.Command
import scala.collection.mutable.ListBuffer

class SwitchHandCommand(activePlayer: Option[PlayerInterface], stack: CardInterface, controller: ControllerInterface) extends Command {
  private val oldCards = ListBuffer[Card]()
  private val newCards = ListBuffer[Card]()

  override def doStep: Unit = {
    oldCards.clear()
    newCards.clear()
    for (card <- activePlayer.get.getHand) {
      oldCards += activePlayer.get.putCard(card).get
    }
    while (!stack.isEmpty && activePlayer.get.getNrCardsInHand < 7) {
      newCards += stack.getCard.get
      activePlayer.get.addToHand(newCards.last)
    }
    for (card <- oldCards) {
      stack.putCard(card)
    }
    activePlayer.get.revokeSwitchedHand()
    controller.activePlayer = activePlayer
  }
  override def undoStep: Unit = {
    for (card <- newCards) {
      stack.putCard(activePlayer.get.putCard(card).get)
    }
    for (card <- oldCards) {
      stack.getSpecificCard(card)
    }
    oldCards.clear()
    newCards.clear()
    activePlayer.get.grantSwitchedHand()
    controller.activePlayer = activePlayer
  }

  override def redoStep: Unit = {
    if (!activePlayer.getOrElse(return).switchedHand) doStep
  }
}

