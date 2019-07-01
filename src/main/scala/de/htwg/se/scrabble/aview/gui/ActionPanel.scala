package de.htwg.se.scrabble.aview.gui

import java.awt.Color

import de.htwg.se.scrabble.controller.ControllerInterface

import scala.swing.{Dimension, Font, Label, Panel}

class ActionPanel(controller: ControllerInterface) extends Panel {
  preferredSize = new Dimension(100, 100)
  background = Color.blue
  // declare Components here
  val label = new Label {
    text = "I'm a big label!."
    font = new Font("Ariel", java.awt.Font.ITALIC, 24)
  }
//  contents += label

  visible = true
}