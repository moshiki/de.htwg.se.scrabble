package de.htwg.se.scrabble.aview.gui

import java.awt.Color

import de.htwg.se.scrabble.controller.ControllerInterface
import de.htwg.se.scrabble.util.Observer

import scala.swing.BorderPanel.Position._
import scala.swing._

// (controller: ControllerInterface)
class GUI(controller: ControllerInterface) extends MainFrame with Observer {
//  def top = new MainFrame { // top is a required method
//  listenTo(controller)
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

    // react to events
//    reactions += {
//      case ButtonClicked(component) if component == button =>
//        val x = Random.nextInt(100)
//        val y = Random.nextInt(100)
////        val c = new Color(Random.nextInt(Int.MaxValue))
////        field.throwDart(new Dart(x, y, c))
////        textField.text = s"Dart thrown at $x, $y"
////      case ButtonClicked(component) if component == toggle =>
////        toggle.text = if (toggle.selected) "On" else "Off"
////      case MouseClicked(_, point, _, _, _) =>
////        field.throwDart(new Dart(point.x, point.y, Color.black))
////        textField.text = (s"You clicked in the Canvas at x=${point.x}, y=${point.y}.")
//    }
//  }

  def update: Boolean = {
     act.repaint()  // TODO: implementierung
//     opt = new OptionPanel(controller)  // TODO: implementierung
     field.repaint()  //= new FieldPanel(controller)
    true
  }
  visible = true
}