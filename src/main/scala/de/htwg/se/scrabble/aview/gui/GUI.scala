package de.htwg.se.scrabble.aview.gui

import java.awt.Color

import de.htwg.se.scrabble.controller.ControllerInterface

import scala.swing.BorderPanel.Position._
import scala.swing._

// (controller: ControllerInterface)
class GUI(controller: ControllerInterface) extends MainFrame {
//  def top = new MainFrame { // top is a required method
    title = "Scrabble - HTWG Software Engineering"
    size = new Dimension(300, 200)

    val act = new ActionPanel(controller: ControllerInterface)  // TODO: implementierung
    val opt = new OptionPanel(controller: ControllerInterface)  // TODO: implementierung
    val field = new FieldPanel(controller: ControllerInterface)




    val statusText = new TextField {
      columns = 10
      text = "TODO: Game Status ausgeben"
   //   text = controller.gameStatus. TODO: Game Status ausgeben
    }
    val button = new Button {
      text = "Throw!"
      foreground = Color.blue
      background = Color.red
      borderPainted = true
      enabled = true
      tooltip = "Click to throw a dart"
    }
    val textArea = new TextArea {
      text = "initial text\nline two"
      background = Color.green
    }
    val gridPanel = new GridPanel(1, 2) {
//      contents += checkBox
//      contents += label
      contents += textArea
    }

    // choose a top-level Panel and put components in it
    // Components may include other Panels
    contents = new BorderPanel {
//      layout(gridPanel) = North
//      layout(opt) = West
      layout(field) = Center
      layout(act) = East
      layout(statusText) = South

    }
    menuBar = new MenuBar {
      contents += new Menu("File") {
        //        mnemonic = Key.F
        contents += new MenuItem(Action("New") {  controller.newGame()})  // controller.createNewGrid
        contents += new MenuItem(Action("Exit") { sys.exit(0)         })
      }
      contents += new Menu("Edit") {
        //        mnemonic = Key.E
        contents += new MenuItem(Action("Undo") { controller.undo })
        contents += new MenuItem(Action("Redo") { controller.redo })
      }
    }





    // specify which Components produce events of interest
    listenTo(button)
//    listenTo(toggle)
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
  visible = true
}