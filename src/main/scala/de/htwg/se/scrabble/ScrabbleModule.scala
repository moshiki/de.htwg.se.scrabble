package de.htwg.se.scrabble

import com.google.inject.AbstractModule
import de.htwg.se.scrabble.controller.ControllerInterface
import net.codingwell.scalaguice.ScalaModule

class ScrabbleModule extends AbstractModule with ScalaModule{
  val defaultSize:Int = 15

  override def configure(): Unit = {

//    bind[ControllerInterface].to[controllerBaseImpl.Controller]
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
