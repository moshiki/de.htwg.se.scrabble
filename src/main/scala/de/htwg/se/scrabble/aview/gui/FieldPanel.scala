package de.htwg.se.scrabble.aview.gui

import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.model.field.Cell

import scala.swing.event.Event
import scala.swing.{Button, Color, Dimension, GridPanel, Label}

class CellClicked(val row: Int, val column: Int) extends Event

class FieldPanel (controller: ControllerInterface) extends GridPanel(controller.field.getSize+1,controller.field.getSize+1){
  val f = controller.field      //kürzel für bessere Lesbarkeit

  val btnSize = new Dimension(50,50)

  // https://www.w3schools.com/colors/colors_picker.asp
  val cardCellColor = new Color(255, 255, 153)
  val cellColor = new Color(255, 204, 0)
  val highlightedCellColor = new Color(192, 255, 192)
  val WHITE = new Color(255, 255, 255)





  // Loade all Cells
  def rePaintField {

    for (i <- 0 until f.getSize) {
      contents += new Label(){
        preferredSize = btnSize
        text = i.toString
        background = WHITE

      }}
    for (i <- 0 until f.getSize) {
      contents += new Label(){
        preferredSize = btnSize
        text = ((i + 65).toChar).toString
        background = WHITE

      }
    for (j <- 1 until f.getSize) {
    //      var zellChar = new String
    //      var
    //      val cellChar: String = f.getCell((i + 65).toString, j).foreach(Cell => Cell.getValue).toString
    //      var cellEmp:Boolean =

    contents += new Button(){
    preferredSize = btnSize
    f.getCell(((i + 65).toChar).toString, j).foreach(Cell => { text = Cell.getValue})
    if(f.getCell((i + 65).toString, j).isEmpty == true)  background = cardCellColor
    else background = cardCellColor

  }
  }
  }
  }


  rePaintField



  // alles weg






//  reactions += {
//    case ButtonClicked(component) if component == button =>
//      val x = Random.nextInt(100)
//      val y = Random.nextInt(100)
    //        val c = new Color(Random.nextInt(Int.MaxValue))
    //        field.throwDart(new Dart(x, y, c))
    //        textField.text = s"Dart thrown at $x, $y"
    //      case ButtonClicked(component) if component == toggle =>
    //        toggle.text = if (toggle.selected) "On" else "Off"
    //      case MouseClicked(_, point, _, _, _) =>
    //        field.throwDart(new Dart(point.x, point.y, Color.black))
    //        textField.text = (s"You clicked in the Canvas at x=${point.x}, y=${point.y}.")
//  }

  /** Get a "Cell" from Array to display */
  def getCell(cell: Cell) {
    cell.getValue
    repaint()     // Tell Scala that the display should be repainted
  }
//    def myCell = controller.cell(row, column)
//
//    def cellText(row: Int, col: Int) = if (controller.isSet(row, column)) " " + controller.cell(row, column).value.toString else " "
//
//    val label =
//      new Label {
//        text = cellText(row, column)
//        font = new Font("Verdana", 1, 36)
//      }
//
//    val cell = new BoxPanel(Orientation.Vertical) {
//      contents += label
//      preferredSize = new Dimension(51, 51)
//      background = if (controller.isGiven(row, column)) givenCellColor else cellColor
//      border = Swing.BeveledBorder(Swing.Raised)
//      listenTo(mouse.clicks)
//      listenTo(controller)
//      reactions += {
//        case e: CellChanged => {
//          label.text = cellText(row, column)
//          repaint
//        }
//        case MouseClicked(src, pt, mod, clicks, pops) => {
//          controller.showCandidates(row, column)
//          repaint
//        }

  visible = true
  }