package de.htwg.se.scrabble.aview.gui

//import com.sun.glass.ui.Window.Level
import de.htwg.se.scrabble.Scrabble
import de.htwg.se.scrabble.controller.ControllerInterface
import scala.swing.event.{ButtonClicked, Event}
import scala.swing.{Button, Color, Dimension, GridPanel, Label}

class CellClicked(val row: Int, val column: Int) extends Event

class FieldPanel (controller: ControllerInterface) extends GridPanel(controller.field.getSize+1,controller.field.getSize+1){
  val btnSize = new Dimension(50,50)

  val cardCellColor = new Color(255, 255, 153)
  val cellColor = new Color(255, 204, 0)
  val highlightedCellColor = new Color(192, 255, 192)
  val WHITE = new Color(255, 255, 255)
  val actCellColor = new Color(51, 204, 51)
  var actField = new String
  var cells = Array.ofDim[swing.Button](controller.field.getSize, controller.field.getSize)
  var highlightedCell: Button = _

  def paintField {
    contents += new Label(){
      preferredSize = btnSize
      text = "♥"
      background = WHITE
    }
    for (col <- 0 until controller.field.getSize) {
      contents += new Label(){
        preferredSize = btnSize
        text = ((col + 65).toChar).toString
        background = WHITE
      }}
    for (row <- 0 until controller.field.getSize) {
      contents += new Label(){
        preferredSize = btnSize
        text = (1+row).toString
        background = WHITE
      }
      for (col <- 0 until controller.field.getSize) {
          val button = new Button(){
          preferredSize = btnSize
            controller.field.getCell((col + 65).toChar.toString, row+1).foreach(Cell => {
            if (Cell.isEmpty) text = ""
            else text = Cell.getValue
          })
          if(controller.field.getCell((col + 65).toChar.toString, row+1).get.isEmpty)  background = cardCellColor
          else background = cardCellColor
            reactions += {
              case _: ButtonClicked =>
                for (button <- contents) {
                  if (button.isInstanceOf[Button])
                    button.background = cardCellColor
                }
                background = actCellColor
                highlightedCell = this
                Scrabble.gui.act.setWord(1) = (col + 65).toChar.toString + (row+1)
                Scrabble.gui.act.redraw
            }
          }
        cells(col)(row) = button
        contents += button
        listenTo(button)
      }
    }
  }
  paintField

  def redraw = {
    for (col <- 0 until controller.field.getSize) {
      for (row <- 0 until controller.field.getSize) {
        val cell = cells(col)(row)
        cell.text = {
          val c = controller.field.getCell((col + 65).toChar.toString, row + 1).get
          if (c.getValue == "_") "" else c.getValue
        }
        if (cell == highlightedCell) cell.background = actCellColor
        else cell.background = cardCellColor
      }
    }
    repaint
  }
  def eraseInput = {
    highlightedCell = new Button
    redraw
  }

  visible = true
  }