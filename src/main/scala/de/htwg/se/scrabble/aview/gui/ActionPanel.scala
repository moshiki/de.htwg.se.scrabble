package de.htwg.se.scrabble.aview.gui

import java.awt.Color

import de.htwg.se.scrabble.controller.ControllerInterface

import scala.swing.event.{ButtonClicked, ValueChanged}
import scala.swing.{Button, ButtonGroup, Dimension, FlowPanel, Font, Label, Panel, RadioButton, TextArea}

class ActionPanel(controller: ControllerInterface) extends FlowPanel{
  preferredSize = new Dimension(200, 800)

  val headFont = new Font("Ariel", java.awt.Font.CENTER_BASELINE, 24)
  val basicFont = new Font("Ariel", java.awt.Font.PLAIN, 18)
  val highlFont = new Font("Ariel", java.awt.Font.BOLD, 20)
  val full = new Dimension(150, 40)
  val half = new Dimension(74, 40)

  var direction : String = "-"
  var setWord = Array.fill[String](4)("")

  initSetWord()

  def initSetWord() = {
    setWord(0) = "set"
    setWord(1) = {
      val c = controller.field.getCoordinates(controller.field.getStarCell.get).get; c.col.toString + c.row
    }
    setWord(2) = "-"
    setWord(3) = ""
  }


  class FreeSpace extends Panel { preferredSize  = full }

//  // ♥♥♥ Scrabble FONT ♥♥♥
//  var src: File = new File("C:\\01_IDEA_Projects\\IntelliJ\\de.htwg.se.scrabble\\src\\main\\resources\\ScrambleMixed.ttf")
//  var scrabbleFont: Font = Font.createFont(Font.TRUETYPE_FONT, src)
//  var ge: GraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment
//  ge.registerFont(scrabbleFont)
//  val Scramble = new Font("Scramble", Font.PLAIN, 35)


  contents += new FreeSpace
  contents += new Button() {
    text = "new game"
    tooltip = "↻"
    preferredSize  = full
    font = basicFont
    background = Color.green
    reactions += {
      case e: ButtonClicked => controller.newGame()
    }
  }
  contents += new Button() {
    text = "↶"
    tooltip = "undo"
    preferredSize  = half
    font = basicFont
    reactions += {
      case _: ButtonClicked =>
       controller.undo()
    }
  }
  contents += new Button() {
    text = "↷"
    tooltip = "redo"
    preferredSize  = half
    font = basicFont
    reactions += {
      case _: ButtonClicked =>
        controller.redo()
    }
  }
  contents += new Button() {
    text = "quit game"
    tooltip = "✗"
    preferredSize  = full
    font = basicFont
    background = Color.red
    reactions += {
      case _: ButtonClicked =>
        sys.exit(0)
    }
  }

  val infoPanel = new InfoPanel
  contents += infoPanel

  class InfoPanel extends FlowPanel {
    preferredSize = new Dimension(200, 800)
    contents += new FreeSpace
    contents += new Label {
      text = "score"
      font = headFont
    }

    val actPlayerL = new Label {
      text = controller.activePlayer.get.toString
      font = highlFont
      tooltip = "active player"
    }
    contents += actPlayerL
    val inactPlayerL =  new Label {
      text = controller.inactivePlayer.get.toString
      font = basicFont
      tooltip = "next player"
    }
    contents += inactPlayerL
    contents += new FreeSpace
    val stackL = new Label {
      text = "cards in stack: " + controller.stack.getSize.toString
      font = highlFont
      tooltip = controller.stack.getSize.toString + "cards left in stack"
    }
    contents += stackL
    contents += new FreeSpace
    contents += new Label {
      text = "your cards: "
      font = headFont
    }
    val cardsL = new Label {
      text = controller.activePlayer.get.getHand.mkString(" ")
      font = highlFont
//          font = Scramble
    }
    contents += cardsL
    val wordTA = new TextArea() {
      text = ""
      background = Color.white
      preferredSize = new Dimension(145, 35)
      font = basicFont
      tooltip = "type word here"
      reactions += {
        case _: ValueChanged =>
          setWord(3) = this.text
          redraw
      }
    }
    contents += wordTA
    contents += new Button() {
      text = "set Word \u2B8B"
      preferredSize = full
      font = basicFont
      reactions += {
        case _: ButtonClicked =>
          setWord(3) = wordTA.text
          controller.setWord(setWord)
      }
    }
    val directionChoose = List(
      new RadioButton() {
        name = "-"
        text = "↦"
        selected = true
        font = headFont
        tooltip = "set word horizontal"
        reactions += { case _: ButtonClicked =>
                                      setWord(2) = "-"
                                      redraw}
      },
      new RadioButton() {
        name = "|"
        text = "↧"
        font = headFont
        tooltip = "set word vertical"
        reactions += {
          case _: ButtonClicked =>
            setWord(2) = "|"
            redraw}
      }
    )
    new ButtonGroup(directionChoose: _*)
    contents ++= directionChoose
    directionChoose.foreach(listenTo(_))
    reactions += {
      case ButtonClicked(button) =>
        button.name match {
          case "-" => direction = " - "
          case "|" => direction = " | "
        }
    }
    val wordToSet = new Label{
      text = setWord.mkString(" ")
      preferredSize  = full
      font = basicFont
    }
    contents += wordToSet
    contents += new Button() {
      text = "⬔ \u2BB0"
      tooltip = "new cards"
      preferredSize = half
      font = basicFont
      reactions += { case _: ButtonClicked => controller.switchHand }
    }
    contents += new Button() {
      text = "\u2B9A"
      tooltip = "skip | next player"
      preferredSize = half
      font = basicFont
      reactions += { case _: ButtonClicked => controller.next }
    }
    visible = true

    def redraw = {
      stackL.text = "cards in stack: " + controller.stack.getSize.toString
      actPlayerL.text = controller.activePlayer.get.toString
      inactPlayerL.text = controller.inactivePlayer.get.toString
      cardsL.text = controller.activePlayer.get.getHand.mkString("")
      wordToSet.text = setWord.mkString(" ")
    }
    def eraseInput = {
      wordTA.text = ""
      initSetWord()
    }
  }
  visible = true

  def redraw = {
    infoPanel.redraw
    repaint
  }
  def eraseInput = {
    infoPanel.eraseInput
    redraw
  }
}