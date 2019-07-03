package de.htwg.se.scrabble.aview.gui

import java.awt.{Color, Font}

import de.htwg.se.scrabble.controller.ControllerInterface

import scala.swing.{Button, Dimension, FlowPanel, Font, Label, Panel, TextArea}
import java.io.{File, InputStream}
import java.awt.GraphicsEnvironment


class ActionPanel(controller: ControllerInterface) extends FlowPanel {
  preferredSize = new Dimension(200, 800)


  val headFont = new Font("Ariel", java.awt.Font.CENTER_BASELINE, 24)
  val basicFont = new Font("Ariel", java.awt.Font.PLAIN, 18)
  val highlFont = new Font("Ariel", java.awt.Font.BOLD, 20)
  val full = new Dimension(150, 40)
  val half = new Dimension(74, 40)

  class FreeSpace extends Panel { preferredSize  = full }


//  // ♥♥♥ Scrabble FONT ♥♥♥
//  var src: File = new File("C:\\01_IDEA_Projects\\IntelliJ\\de.htwg.se.scrabble\\src\\main\\scala\\de\\htwg\\se\\scrabble\\aview\\gui\\Scramble.ttf")
//  var scrabbleFont: Font = Font.createFont(Font.TRUETYPE_FONT, src)
//  var ge: GraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment
//  ge.registerFont(scrabbleFont)
//  val Scramble = new Font("Scramble", Font.PLAIN, 35)





  var wordToSet = new Label{
    text = "B5 - Word" // TODO Zusammensetzen aus Auswahl der Zellen
    preferredSize  = full
//    font = Scramble
    font = basicFont
  }

  contents += new FreeSpace
  contents += new Button() {
    text = "New Game!"
    preferredSize  = full
    font = basicFont
    background = Color.green
  }
  contents += new Button() {
    text = "undo"
    preferredSize  = half
    font = basicFont
  }
  contents += new Button() {
    text = "redo"
    preferredSize  = half
    font = basicFont

  }
  contents += new Button() {
    text = "(RAGE) QUIT"
    preferredSize  = full
    font = basicFont
    background = Color.red
  }
  contents += new FreeSpace
  contents += new Label {
    text = "Score"
    font = headFont
  }
  contents += new Label {
    text = controller.activePlayer.get.toString
    font = highlFont
    tooltip = "Active Player"

  }
  contents += new Label {
    text = controller.inactivePlayer.get.toString
    font = basicFont
    tooltip = "Next Player"
  }
  contents += new FreeSpace
  contents += new Label {
    text = "Char's left: " + controller.stack.getSize.toString
    font = highlFont
    tooltip = controller.stack.getSize.toString + "Cards left in Stack"
  }
  contents += new FreeSpace
  contents += new Label {
    text = "Your Cards: "
    font = headFont
  }
  contents += new Label {  // TODO: Felder anlegen, für jeden char eins, Hintergrund Bild + Anzahl der Punkte
    text = controller.activePlayer.get.getHand.mkString(" ")
    font = highlFont
  }
  contents += new TextArea() {
    text = ""
    background = Color.white
    preferredSize  = new Dimension(145, 35)
    font = basicFont
    tooltip = "Pls set your Word here"
  }
  contents += new Button() {
    text = "set Word"
    preferredSize  = full
    font = basicFont
  }

  contents += wordToSet
  contents += new FreeSpace

//  contents += new TextArea(){
  ////    """
  ////      |"A"->6, "B"->2, "C"->4,"D"->6,"E"->16,"F"->3,"G"->3,"H"->5,"I"->9,"J"->1,
  ////      |"K"->2,"L"->4,"M"->4,"N"->10,"O"->4,"P"->1,"Q"->1,"R"->7,"S"->8,"T"->5,"U"->6,"V"->1,"W"->2,"X"->1,"Y"->1,"Z"->2/*
  ////    """.stripMargin}
  visible = true
}