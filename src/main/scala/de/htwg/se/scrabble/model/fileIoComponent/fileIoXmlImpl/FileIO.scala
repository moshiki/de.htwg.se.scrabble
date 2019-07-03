package de.htwg.se.scrabble.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.scrabble.ScrabbleModule
import de.htwg.se.scrabble.controller.StateCacheInterface
import de.htwg.se.scrabble.controller.GameStatus._
import de.htwg.se.scrabble.controller.controllerBaseImpl.StateCache
import de.htwg.se.scrabble.model._
import de.htwg.se.scrabble.model.cards.{Card, EmptyCardStack}
import de.htwg.se.scrabble.model.field.Cell
import de.htwg.se.scrabble.model.fileIoComponent.FileIOInterface
import de.htwg.se.scrabble.model.player.{Player, PlayerList}

import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface {

  override def load: StateCacheInterface = {
    val injector = Guice.createInjector(new ScrabbleModule)
    val file = scala.xml.XML.loadFile("savedstate.xml")
    var field: FieldInterface = null
    val sizeAttr = (file \\ "field" \ "@size")
    val size = sizeAttr.text.toInt
    val stack: CardStackInterface = new EmptyCardStack
    val players: PlayerListInterface = injector.instance[PlayerListInterface]
    val roundManager = (file \ "roundManager").text.trim
    var gameStatus = IDLE
    var activePlayer: Option[PlayerInterface] = None
    val firstDraw = (file \ "firstDraw").text.toBoolean


    size match {
      case 15 => field = injector.instance[FieldInterface](Names.named("regular"))
      case _ =>
    }
    val cellNodes = (file \\ "cell")
    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.trim.toInt
      val col: String = (cell \ "@col").text.trim
      val value: String = cell.text.trim
      field.setCell(col, row, value)
    }

    val stackSize = (file \\ "stack" \ "@size")
    val cardStack = (file \\ "stack" \ "card")
    for (card <- cardStack) {
      stack.putCard(Card(card.text.trim))
    }

    val playerList = (file \\ "players" \ "player")
    for (player <- playerList) {
      val role = (player \ "@role")
      val name = (player \ "@name")
      val handSize = (player \ "hand" \ "@size")
      val hand = (player \ "hand" \\ "card")
      val points = (player \ "points")
      val actionPerm = (player \ "actionPermit")
      val switchedHand = (player \ "switchedHand")

      players.put(Player(role.toString.trim, name.toString.trim))
      for (card <- hand) {
        players.get(role.toString.trim).get.addToHand(Card(card.text.trim))
      }
      players.get(role.toString.trim).get.addPoints(points.text.trim.toInt)
      if (actionPerm.text.trim.toBoolean) {
        players.get(role.toString.trim).get.grantActionPermit()
      } else {
        players.get(role.toString.trim).get.revokeActionPermit()
      }
      if (switchedHand.text.trim.toBoolean) {
        players.get(role.toString.trim).get.grantSwitchedHand()
      } else {
        players.get(role.toString.trim).get.revokeSwitchedHand()
      }
    }
    activePlayer = players.get((file \ "activePlayer").text.trim)

    StateCache(field,stack,players,roundManager,gameStatus,activePlayer,firstDraw)
  }

  def save(states: StateCacheInterface): Unit = saveString(states)

  def saveXML(states: StateCacheInterface): Unit = {
    scala.xml.XML.save("savedstate.xml", fieldToXML(states.field))
  }

  def saveString(states: StateCacheInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("savedstate.xml"))
    val pp = new PrettyPrinter(120, 4)
    val xml = pp.format(statesToXML(states))
    pw.write(xml)
    pw.close()
  }

  def statesToXML(states: StateCacheInterface) = {
    <states game="scrabble">
      { fieldToXML(states.field) }
      { stackToXML(states.stack) }
      { playerListToXML(states.players) }
      { variablesToXML(states.roundManager, states.gameStatus, states.activePlayer, states.firstDraw) }
    </states>
  }

  def fieldToXML(field: FieldInterface) = {
    <field size={ field.getSize.toString }>
      {
        for {
          row <- 0 until field.getSize
          col <- 65 until 65+field.getSize
        } yield cellToXml(field, row, col.toChar.toString)
      }
    </field>
  }

  def cellToXml(field: FieldInterface, row: Int, col: String) = {
    <cell row={ row.toString } col={ col }>
      { field.getCell(col, row).getOrElse(new Cell("")).getValue }
    </cell>
  }

  def stackToXML(stack: CardStackInterface) = {
    <stack size={ stack.getSize.toString }>
      { for (card <- stack.getStack) yield <card>{ card.value }</card> }
    </stack>
  }
  def playerListToXML(players: PlayerListInterface) = {
    <players>
      { for (player <- players.getList) yield playerToXML(player) }
    </players>
  }

  def playerToXML(player: PlayerInterface) = {
    <player role={ player.role } name={ player.name }>
      <hand size={ player.getNrCardsInHand.toString }>
        { for (card <- player.getHand) yield <card>{ card.value }</card> }
      </hand>
      <points>{ player.getPoints }</points>
      <actionPermit>{ player.actionPermitted }</actionPermit>
      <switchedHand>{ player.switchedHand }</switchedHand>
    </player>
  }
  def variablesToXML(rm: String, gs: GameStatus, ap: Option[PlayerInterface], fd: Boolean) = {
    <roundManager>{ rm }</roundManager>
    //<gameStatus>{ gs }</gameStatus>
    <activePlayer>{ if (ap.isDefined) ap.get.role else "" }</activePlayer>
    <firstDraw>{ fd }</firstDraw>
  }

}
