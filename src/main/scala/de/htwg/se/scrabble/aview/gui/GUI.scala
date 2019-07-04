package de.htwg.se.scrabble.aview.gui

import de.htwg.se.scrabble.controller._
import scala.swing.BorderPanel.Position._
import scala.swing._

class GUI(controller: ControllerInterface) extends MainFrame {
//  def top = new MainFrame { // top is a required method
    listenTo(controller)
    title = "Scrabble - HTWG Software Engineering"
    size = new Dimension(300, 200)

    var act = new ActionPanel(controller: ControllerInterface)  // TODO: implementierung
//    var opt = new OptionPanel(controller: ControllerInterface)  // TODO: implementierung
    var field = new FieldPanel(controller: ControllerInterface)
    val statusText = new TextField {
      columns = 10
      text = "TODO: Game Status ausgeben"
   //   text = controller.gameStatus. TODO: Game Status ausgeben
    }

    contents = new BorderPanel {
//      layout(gridPanel) = North
//      layout(opt) = West
      layout(field) = Center
      layout(act) = East
      layout(statusText) = South

    }

    menuBar = new MenuBar {
      contents += new Menu("File") {
        contents += new MenuItem(Action("New") {  controller.newGame()})  // controller.createNewGrid
//        contents += new MenuItem(Action("Loade") { )
//        contents += new MenuItem(Action("Save") { )
        contents += new MenuItem(Action("Exit") { sys.exit(0)         })
      }
      contents += new Menu("Edit") {
        //        mnemonic = Key.E
        contents += new MenuItem(Action("Undo") { controller.undo })
        contents += new MenuItem(Action("Redo") { controller.redo })
      }
    }

    listenTo(field.mouse.clicks)

  reactions += {
    case event: CellChanged => redraw
    case event: PlayerChanged => redraw
    case event: StackChanged => redraw
    case event: AllChanged => redraw
  }

  def redraw = {
    act.redraw
    field.repaintField
    repaint
  }

  visible = true
}