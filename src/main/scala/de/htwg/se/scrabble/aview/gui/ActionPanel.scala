package de.htwg.se.scrabble.aview.gui

import java.awt.Color

import de.htwg.se.scrabble.controller.ControllerInterface

import scala.swing.{Button, Dimension, FlowPanel, Font, Label, Panel, TextArea}

class ActionPanel(controller: ControllerInterface) extends FlowPanel {
  preferredSize = new Dimension(200, 800)


  val headFont = new Font("Ariel", java.awt.Font.CENTER_BASELINE, 24)
  val basicFont = new Font("Ariel", java.awt.Font.PLAIN, 18)
  val highlFont = new Font("Ariel", java.awt.Font.BOLD, 20)
  val full = new Dimension(150, 40)
  val half = new Dimension(74, 40)

  // declare Components here
  val l0 = new Label {
    text = "Score"
    font = headFont
  }
  val l1 = new Label {
    text = controller.activePlayer.get.toString
    font = highlFont
    tooltip = "Active Player"

  }
  val l2 = new Label {
    text = controller.inactivePlayer.get.toString
    font = basicFont
    tooltip = "Next Player"
  }
  val l3 = new Label {
    text = "Char's left: " + controller.stack.getSize.toString
    font = basicFont
    tooltip = controller.stack.getSize.toString + "Cards left in Stack"
  }
  val l4 = new Label {
    text = "Your Cards: "
    font = headFont
  }
  val l5 = new Label {  // TODO: Felder anlegen, für jeden char eins, Hintergrund Bild + Anzahl der Punkte
    text = controller.activePlayer.get.getHand.mkString(" ")
    font = highlFont
  }


  val t1 = new TextArea() {
    text = ""
    background = Color.white
    preferredSize  = full
    font = basicFont
    tooltip = "Pls set your Word here"
  }
  val btn1 = new Button() {
    text = "set Word"
    preferredSize  = full
    font = basicFont
  }
  val btn2 = new Button() {
    text = "undo"
    preferredSize  = half
    font = basicFont
  }
  val btn3 = new Button() {
    text = "redo"
    preferredSize  = half
    font = basicFont

  }







  contents += l0
  contents += l1
  contents += l2
  contents += l3
  contents += l4
  contents += l5
  contents += t1
  contents += btn1
  contents += btn2
  contents += btn3

  visible = true
}