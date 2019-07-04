package de.htwg.se.scrabble.aview.gui

import de.htwg.se.scrabble.controller._
import scala.swing.BorderPanel.Position._
import scala.swing._

class GUI(controller: ControllerInterface) extends MainFrame {
    listenTo(controller)
    title = "Scrabble - HTWG Software Engineering"
    size = new Dimension(300, 200)

    var act = new ActionPanel(controller: ControllerInterface)
//    var opt = new OptionPanel(controller: ControllerInterface)
    var field = new FieldPanel(controller: ControllerInterface)
    val statusText = new TextField {
      columns = 10
      text = GameStatus.message(controller.gameStatus)
    }

    contents = new BorderPanel {
      layout(field) = Center
      layout(act) = East
      layout(statusText) = South

    }

    menuBar = new MenuBar {
      contents += new Menu("Scrabble") {
        contents += new MenuItem(Action("new") {controller.newGame()})
        //contents += new MenuItem(Action("load") { controller.load() }
        //contents += new MenuItem(Action("save") { controller.save() }
        contents += new MenuItem(Action("exit") { sys.exit(0)         })
      }
    }

    listenTo(field.mouse.clicks)

  reactions += {
    case event: CellChanged => redraw
    case event: PlayerChanged => redraw
    case event: StackChanged => redraw
    case event: AllChanged => redraw
    case event: NewGame => eraseInput; redraw
    case event: NextPlayer => eraseInput; redraw
  }

  def redraw = {
    act.redraw
    field.redraw
    statusText.text = GameStatus.message(controller.gameStatus)
    controller.gameStatus = GameStatus.IDLE
    repaint
  }
  def eraseInput = {
    act.eraseInput
    field.eraseInput
    repaint

  }
  visible = true
}