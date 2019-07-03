package de.htwg.se.scrabble.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.scrabble.ScrabbleModule
import de.htwg.se.scrabble.controller.controllerBaseImpl.gameManager.GameManager
import de.htwg.se.scrabble.controller.StateCacheInterface
import de.htwg.se.scrabble.controller.GameStatus.GameStatus
import de.htwg.se.scrabble.controller.controllerBaseImpl.StateCache
import de.htwg.se.scrabble.model._
import de.htwg.se.scrabble.model.field.Cell
import de.htwg.se.scrabble.model.fileIoComponent.FileIOInterface
import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface {

  override def load: StateCacheInterface = {
    var field: FieldInterface = null
    var stack: CardStackInterface = null
    var players: PlayerListInterface = null
    var roundManager: GameManager = null
    var activePlayer: Option[PlayerInterface] = null
    var gameStatus: GameStatus = null
    var firstDraw: Boolean = false

    val file = scala.xml.XML.loadFile("grid.xml")

    val sizeAttr = (file \\ "field" \ "@size")
    val size = sizeAttr.text.toInt
    val injector = Guice.createInjector(new ScrabbleModule)
    size match {
      case 15 => field = injector.instance[FieldInterface](Names.named("DefaultSize"))
      case _ =>
    }
    val cellNodes = (file \\ "cell")
    for (cell <- cellNodes) {
      val row: String = (cell \ "@row").text.trim
      val col: Int = (cell \ "@col").text.trim.toInt
      val value: String = cell.text.trim
      field.setCell(row, col, value)
    }
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
    pw.close
  }

  def statesToXML(states: StateCacheInterface) = {
    <states game="scrabble">
      fieldToXML(states.field)
      stackToXML(states.stack)
      playerListToXML(states.players)
      variablesToXML(states.roundManager, states.gameStatus, states.activePlayer, states.firstDraw)
    </states>
  }

  def fieldToXML(field: FieldInterface) = {
    <grid size={ field.getSize.toString }>
      {
        for {
          row <- 0 until field.getSize
          col <- 65 until 65+field.getSize
        } yield cellToXml(field, row, col.toChar.toString)
      }
    </grid>
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
  def variablesToXML(rm: GameManager, gs: GameStatus, ap: Option[PlayerInterface], fd: Boolean) = {
    <roundManager>{ rm.toString }</roundManager>
    <gameStatus>{ gs }</gameStatus>
    <activePlayer>{ ap.get.role }</activePlayer>
    <firstDraw>{ fd }</firstDraw>
  }

}
