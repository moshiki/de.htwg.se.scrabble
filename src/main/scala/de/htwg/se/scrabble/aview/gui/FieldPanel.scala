package de.htwg.se.scrabble.aview.gui

//import com.sun.glass.ui.Window.Level
import de.htwg.se.scrabble.Scrabble
import de.htwg.se.scrabble.controller.ControllerInterface
import scala.swing.event.{ButtonClicked, Event}
import scala.swing.{Button, Color, Dimension, GridPanel, Label}

class CellClicked(val row: Int, val column: Int) extends Event

class FieldPanel (controller: ControllerInterface) extends GridPanel(controller.field.getSize+1,controller.field.getSize+1){
  val f = controller.field

  val btnSize = new Dimension(50,50)

  // https://www.w3schools.com/colors/colors_picker.asp
  val cardCellColor = new Color(255, 255, 153)
  val cellColor = new Color(255, 204, 0)
  val highlightedCellColor = new Color(192, 255, 192)
  val WHITE = new Color(255, 255, 255)
  val actCellColor = new Color(51, 204, 51)
  var actField = new String

  //import com.sun.javafx.tk.FontLoader
  import java.awt.Font
  import java.awt.FontFormatException
  import java.io.IOException
  import java.io.InputStream

  def paintField {
    contents += new Label(){
      preferredSize = btnSize
      text = "♥"
      background = WHITE
    }
    for (i <- 0 until f.getSize) {
      contents += new Label(){
        preferredSize = btnSize
        text = ((i + 65).toChar).toString
        background = WHITE
      }}
    for (i <- 0 until f.getSize) {
      contents += new Label(){
        preferredSize = btnSize
        text = (1+i).toString
        background = WHITE
      }
      for (j <- 1 to f.getSize) {
          val button = new Button(){
          preferredSize = btnSize
          //    font = Scramble
          f.getCell(((i + 65).toChar).toString, j).foreach(Cell => {
            if (Cell.isEmpty) text = ""
            else text = Cell.getValue
          })
          if(f.getCell((i + 65).toString, j).isEmpty == true)  background = cardCellColor
          else background = cardCellColor
            reactions += {
              case _: ButtonClicked =>
                for (button <- contents) {
                  if (button.isInstanceOf[Button])
                    button.background = cardCellColor
                }
                background = actCellColor
                Scrabble.gui.act.setWord(1) = ((i + 65).toChar).toString + j
                Scrabble.gui.act.redraw
            }
          }
        contents += button
        listenTo(button)
      }
    }
  }
  paintField

  def repaintField = {
    contents.clear()
    paintField
    repaint
  }
  visible = true
  }