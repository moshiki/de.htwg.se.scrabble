package de.htwg.se.scrabble.aview

import de.htwg.se.scrabble.controller._
import de.htwg.se.scrabble.controller.{ControllerInterface, GameStatus}

import scala.swing.Reactor

class TUI(controller: ControllerInterface) extends Reactor {
  listenTo(controller)
  println(init)

  def init: String = artScrabble + head + help

  def artScrabble: String = """
      | .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.
      || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
      || |    _______   | || |     ______   | || |  _______     | || |      __      | || |   ______     | || |   ______     | || |   _____      | || |  _________   | |
      || |   /  ___  |  | || |   .' ___  |  | || | |_   __ \    | || |     /  \     | || |  |_   _ \    | || |  |_   _ \    | || |  |_   _|     | || | |_   ___  |  | |
      || |  |  (__ \_|  | || |  / .'   \_|  | || |   | |__) |   | || |    / /\ \    | || |    | |_) |   | || |    | |_) |   | || |    | |       | || |   | |_  \_|  | |
      || |   '.___`-.   | || |  | |         | || |   |  __ /    | || |   / ____ \   | || |    |  __'.   | || |    |  __'.   | || |    | |   _   | || |   |  _|  _   | |
      || |  |`\____) |  | || |  \ `.___.'\  | || |  _| |  \ \_  | || | _/ /    \ \_ | || |   _| |__) |  | || |   _| |__) |  | || |   _| |__/ |  | || |  _| |___/ |  | |
      || |  |_______.'  | || |   `._____.'  | || | |____| |___| | || ||____|  |____|| || |  |_______/   | || |  |_______/   | || |  |________|  | || | |_________|  | |
      || |            1 | || |            3 | || |            1 | || |            1 | || |            3 | || |            3 | || |            1 | || |            1 | |
      || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
      | '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'""".stripMargin

  def head: String = """
    ||                                                                                                                                                              |
    ||                                                                                                                                                              |
    ||                                                                   SCRABBLE IN SCALA                                                                          |
    ||                                                                                                                                                              |
    ||--------------------------------------------------------------------------------------------------------------------------------------------------------------|""".stripMargin

  def help: String = """
    ||                                                                                                                                                              |
    || commands                   function                                                                                                                          |
    ||                        |                                                                                                                                     |
    ||  new                   |   new game                                                                                                                          |
    ||                        |                                                                                                                                     |
    ||  pd                    |   print dictionary - prints the dictionary to console                                                                               |
    ||                        |                                                                                                                                     |
    ||  pv                    |   print vector - prints the alphabet vector to console                                                                              |
    ||                        |                                                                                                                                     |
    ||  players               |   displays a list of the players                                                                                                    |
    ||                        |                                                                                                                                     |
    ||  set                   |   set a Word into the Field                                                                                                         |
    ||                        |                                                                                                                                     |
    ||  help                  |   displays the command list                                                                                                         |
    ||                        |                                                                                                                                     |
    ||  exit                  |   exit scrabble                                                                                                                     |
    ||--------------------------------------------------------------------------------------------------------------------------------------------------------------|""".stripMargin

//Todo: direkte controller zugriffe, anstelle der methodenaufrufe und deren zugriff (kürzen) überall wo controller. steht (nicht alle, nur die welche nötig sind)
  def processCommand(com:String): Unit = {
      val command = com.split(" ")
      command(0) match {
        case "exit" => exit()
        case "help" => println(help)
        case "new" => controller.newGame()
        case "save" => controller.save
        case "load" => controller.load
        case "pd" => printDict()
        case "pv" => printVector()
        case "players" => players()
        case "undo" => controller.undo()
        case "redo" => controller.redo()
        case "set" => controller.setWord(command)
        case "switch" => controller.switchHand()
        case "next" => controller.next()
        case _ => com match {
          case r"""[A-Za-z]\d{1,2}\s[A-Za-z#]""" =>
            controller.set(command(0).charAt(0).toString, command(0).substring(1).toInt, command(1).charAt(0).toString)
          case _ =>
        }
      }
    implicit class Regex(sc: StringContext) {
      def r = new util.matching.Regex(sc.parts.mkString, sc.parts.tail.map(_ => "x"): _*)
    }
  }

  reactions += {
    case event: CellChanged => printTui
    case event: PlayerChanged => printTui
    case event: StackChanged => printTui
    case event: AllChanged => printTui
    case event: NewGame => printTui
    case event: NextPlayer => printTui
  }

  def exit(): Unit = {
    System.err.println("bye!")
    System.exit(0)
  }

  def printDict(): Unit = print(controller.dictToString)

  def printVector(): Unit = print(controller.vectorToString)

  def players(): Unit = {
    println(controller.players.toString)
  }

  def printTui = {
    println()
    println(controller.activePlayer.getOrElse(""))
    if (controller.activePlayer.isDefined) {
      println("hand     | " + controller.activePlayer.get.getHand.mkString(" "))
    }
    println("stack    | " + controller.stack.getSize + " cards")
    println()
    print(controller.field.toString)
    println(GameStatus.message(controller.gameStatus))
    //controller.gameStatus(GameStatus.IDLE)
    true

  }
}
