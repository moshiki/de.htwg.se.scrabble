package de.htwg.se.scrabble.aview

import de.htwg.se.scrabble.Scrabble
import de.htwg.se.scrabble.model.Dictionary
import de.htwg.se.scrabble.util.Observer

class TUI extends Observer {
  init()

  def init(): Unit = {
    print(artScrabble())
    print(head())
    println(help())
  }

  def artScrabble(): String =
    """
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
      | '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'
    """.stripMargin

  def head(): String = """
    |########################################################################################################|
    |########################################################################################################|
    |                                                                                                        |
    |                                       SCRABBLE IN SCALA                                                |
    |                                                                                                        |"""

  def help(): String = """
    |--------------------------------------------------------------------------------------------------------|
    |                                                                                                        |
    | Commands                   Function                                                                    |
    |                        |                                                                               |
    |  rl                    |   reload dictionary - reloads the dictionary from text file                   |
    |                        |                                                                               |
    |  pd                    |   print dictionary - prints the dictionary to console                         |
    |                        |                                                                               |
    |  pv                    |   print vector - prints the alphabet vector to console                        |
    |                        |                                                                               |
    |  player [a|b] [name]   |   adds a new player with specified role (a or b) and name                     |
    |                        |                                                                               |
    |  help                  |   displays the command list                                                   |
    |                        |                                                                               |
    |  exit                  |   exit scrabble                                                               |
    |--------------------------------------------------------------------------------------------------------|"""


  def processCommand(com:String): Unit = {
      val command = com.split(" ")
      command(0) match {
        case "exit" => System.err.println("Bye!")
                       System.exit(0)
        case "help" => println(help())
        case "rl" => Scrabble.dict = new Dictionary
        case "pd" => println("Word list:")
                     Scrabble.dict.dict.toStream.sorted.foreach(println)
        case "pv" => println("Alphabet vector:")
                     Scrabble.dict.alphabet.toSeq.sortBy(_._1).foreach(println)
        case "player" => println("ToDo: Player")
        case unknown => System.err.println("Command \'" + unknown +"\' does not exist! Use \'help\' to display commands.")
      }
  }

  override def update(): Unit = {
    /* TODO:
logger.info(NEWLINE + controller.getGridString());
 logger.info(NEWLINE + controller.getStatus());
 logger.info(NEWLINE
   + "Possible commands: q-quit, n-new, s-solve, r-reset, z-undo, y-redo, c-copy, p-paste, -,+,*-size, x-highlight, xy-show (x,y), xyz-set (x,y) to z");

*/
  }
}