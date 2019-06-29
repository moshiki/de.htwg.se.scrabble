package de.htwg.se.scrabble

import com.google.inject.AbstractModule
import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller
import de.htwg.se.scrabble.model.{CardInterface, FieldInterface, PlayerInterface, ProcessWordInterface}
import de.htwg.se.scrabble.model.cards.{Card, RegularCardStack}
import de.htwg.se.scrabble.model.field.RegularField
import de.htwg.se.scrabble.model.player.PlayerList
import net.codingwell.scalaguice.ScalaModule

class ScrabbleModule extends AbstractModule with ScalaModule{
  val defaultSize:Int = 15

  override def configure(): Unit = {
    bind[ControllerInterface].to[Controller]
    bind[CardInterface].to[RegularCardStack]
    bind[FieldInterface].toInstance(RegularField(defaultSize))
    bind[PlayerInterface].to[PlayerList]
    //bind[ProcessWordInterface].to[ProcessWord]
  }

//  def configure() = {             // Bogers Code
//    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
//    bind[GridInterface].to[Grid]
//    bind[ControllerInterface].to[controllerBaseImpl.Controller]


//    bind[GridInterface].annotatedWithName("tiny").toInstance(new Grid(1))
//    bind[GridInterface].annotatedWithName("small").toInstance(new Grid(4))
//    bind[GridInterface].annotatedWithName("normal").toInstance(new Grid(9))
//
//  }

}
