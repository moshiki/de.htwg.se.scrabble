package de.htwg.se.scrabble

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller
import de.htwg.se.scrabble.model.{CardStackInterface, FieldInterface, PlayerListInterface}
import de.htwg.se.scrabble.model.cards.RegularCardStack
import de.htwg.se.scrabble.model.field.RegularField
import de.htwg.se.scrabble.model.fileIoComponent.FileIOInterface
import de.htwg.se.scrabble.model.fileIoComponent._
import de.htwg.se.scrabble.model.player.PlayerList
import net.codingwell.scalaguice.ScalaModule

class ScrabbleModule extends AbstractModule with ScalaModule{
  val defaultSize:Int = 15

  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bind[FieldInterface].annotatedWithName("regular").toInstance(new RegularField(defaultSize))
    bind[ControllerInterface].to[Controller]
    bind[CardStackInterface].to[RegularCardStack]
    bind[PlayerListInterface].to[PlayerList]
    bind[FieldInterface].to[RegularField]
    bind[FileIOInterface].to[fileIoJsonImpl.FileIO]
  }
}
