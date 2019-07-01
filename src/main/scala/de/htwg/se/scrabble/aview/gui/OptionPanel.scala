package de.htwg.se.scrabble.aview.gui

import java.awt.Color

import de.htwg.se.scrabble.controller.ControllerInterface

import scala.swing.{Dimension, Panel}

class OptionPanel (controller: ControllerInterface) extends Panel{
  preferredSize = new Dimension(300, 800)
  background = Color.white
  visible = true
}
