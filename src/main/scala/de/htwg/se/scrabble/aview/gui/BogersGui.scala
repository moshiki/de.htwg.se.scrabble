//package de.htwg.se.scrabble.aview.gui
//import de.htwg.se.scrabble.controller.ControllerInterface
//
//import scala.swing.{Action, BorderPanel, FlowPanel, Label, MainFrame, Menu, MenuBar, MenuItem, SimpleSwingApplication, Swing, TextField}
//import scala.swing.event.{EditDone, Key}
//
//class BogersGui(controller: ControllerInterface) extends SimpleSwingApplication {
//
//
//  def top = new MainFrame {
//    title = "Celsius/Fahrenheit Converter"
//    object celsius extends TextField { columns = 5 }
//    object fahrenheit extends TextField { columns = 5 }
//    contents = new FlowPanel {
//      contents += celsius
//      contents += new Label(" Celsius  =  ")
//      contents += fahrenheit
//      contents += new Label(" Fahrenheit")
//      border = Swing.EmptyBorder(15, 10, 10, 10)
//
//      listenTo(celsius, fahrenheit)
//      reactions += {
//        case EditDone(`fahrenheit`) =>
//          val f = fahrenheit.text.toInt
//          val c = (f - 32) * 5 / 9
//          celsius.text = c.toString
//        case EditDone(`celsius`) =>
//          val c = celsius.text.toInt
//          val f = c * 9 / 5 + 32
//          fahrenheit.text = f.toString
//      }
//    }
//    visible = true
//  }
//
//
//
//
////          class CellClicked(val row: Int, val column: Int) extends Event
//
////    listenTo(controller)
//
////    var cells = Array.ofDim[CellPanel](controller.gridSize, controller.gridSize)
////
////    def highlightpanel = new FlowPanel {
////      contents += new Label("Highlight:")
////      for {index <- 0 to controller.gridSize} {
////        val button = Button(if (index == 0) "" else index.toString) {
////          controller.highlight(index)
////        }
////        button.preferredSize_=(new Dimension(30, 30))
////        contents += button
////        listenTo(button)
////      }
////    }
////
////    def gridPanel = new GridPanel(controller.blockSize, controller.blockSize) {
////      border = LineBorder(java.awt.Color.BLACK, 2)
////      background = java.awt.Color.BLACK
////      for {
////        outerRow <- 0 until controller.blockSize
////        outerColumn <- 0 until controller.blockSize
////      } {
////        contents += new GridPanel(controller.blockSize, controller.blockSize) {
////          border = LineBorder(java.awt.Color.BLACK, 2)
////          for {
////            innerRow <- 0 until controller.blockSize
////            innerColumn <- 0 until controller.blockSize
////          } {
////            val x = outerRow * controller.blockSize + innerRow
////            val y = outerColumn * controller.blockSize + innerColumn
////            val cellPanel = new CellPanel(x, y, controller)
////            cells(x)(y) = cellPanel
////            contents += cellPanel
////            listenTo(cellPanel)
////          }
////        }
////      }
////    }
////    val statusline = new TextField(controller.statusText, 20)
////
//    contents = new BorderPanel {
//      add(highlightpanel, BorderPanel.Position.North)
//      add(gridPanel, BorderPanel.Position.Center)
//      add(statusline, BorderPanel.Position.South)
//    }
//
//    menuBar = new MenuBar {
//      contents += new Menu("File") {
//        mnemonic = Key.F
//        contents += new MenuItem(Action("Empty") {         }) //  controller.createEmptyGrid
//        contents += new MenuItem(Action("New") {           })  // controller.createNewGrid
//        contents += new MenuItem(Action("Quit") { System.exit(0) })
//      }
//      contents += new Menu("Edit") {
//        mnemonic = Key.E
//        contents += new MenuItem(Action("Undo") { controller.undo })
//        contents += new MenuItem(Action("Redo") { controller.redo })
//      }
////      contents += new Menu("Solve") {
////        mnemonic = Key.S
////        contents += new MenuItem(Action("Solve") { controller.solve })
////      }
////      contents += new Menu("Highlight") {
////        mnemonic = Key.H
////        for { index <- 0 to controller.gridSize } {
////          contents += new MenuItem(Action(index.toString) { controller.highlight(index) })
////        }
////      }
////      contents += new Menu("Options") {
////        mnemonic = Key.O
////        contents += new MenuItem(Action("Show all candidates") { controller.toggleShowAllCandidates })
////        contents += new MenuItem(Action("Size 1*1") { controller.resize(1) })
////        contents += new MenuItem(Action("Size 4*4") { controller.resize(4) })
////        contents += new MenuItem(Action("Size 9*9") { controller.resize(9) })
////
////      }
////    }
//
//    visible = true
//    redraw
//
////    reactions += {
////      case event: GridSizeChanged => resize(event.newSize)
////      case event: CellChanged     => redraw
////      case event: CandidatesChanged => redraw
////    }
////
////    def resize(gridSize: Int) = {
////      cells = Array.ofDim[CellPanel](controller.gridSize, controller.gridSize)
////      contents = new BorderPanel {
////        add(highlightpanel, BorderPanel.Position.North)
////        add(gridPanel, BorderPanel.Position.Center)
////        add(statusline, BorderPanel.Position.South)
////      }
////    }
////    def redraw = {
////      for {
////        row <- 0 until controller.gridSize
////        column <- 0 until controller.gridSize
////      } cells(row)(column).redraw
////      statusline.text = controller.statusText
////      repaint
////    }
//}