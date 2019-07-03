package de.htwg.se.scrabble.model.field

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.scrabble.model.FieldInterface

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, SortedMap}

class RegularField @Inject()(@Named("DefaultSize") size:Int)  extends FieldInterface {
  private var grid: SortedMap[Int, SortedMap[String, Cell]] = SortedMap.empty[Int, SortedMap[String, Cell]]
  private val starCell: Coordinate = Coordinate(size/2+1, (65+size/2).toChar)

  for (row <- 1 to size) {
    grid += (row -> SortedMap.empty[String, Cell])
    for (a <- 65 until (65 + size)) {
      var col = a.toChar.toString
      grid(row) += (col -> new Cell("_"))
    }
  }
  setCell(starCell.col.toString, starCell.row, "*")

  override def getCell(col: String, row: Int): Option[Cell] = {
    val X = col.toUpperCase().charAt(0)-65
    if (X < size && X >= 0 && row <= size && row > 0) {
      Some(grid(row)(col))
    } else {  // unterer zugrif bricht bedingung, greift nach oben um was zu ändern
      //controller.gameStatus = GameStatus.OOBOUND
      None
    }
  }

  override def getStarCell: Option[Cell] = getCell(starCell.col.toString, starCell.row)

  override def getNextCell(cell: Cell): Option[Cell] = {
    var coord = getCoordinates(cell).getOrElse(return None)
    getCell((coord.col+1).toChar.toString, coord.row)
  }

  override def getPrevCell(cell: Cell): Option[Cell] = {
    var coord = getCoordinates(cell).getOrElse(return None)
    getCell((coord.col-1).toChar.toString, coord.row)
  }

  override def getUpperCell(cell: Cell): Option[Cell] = {
    var coord = getCoordinates(cell).getOrElse(return None)
    getCell(coord.col.toString, coord.row-1)
  }

  override def getLowerCell(cell: Cell): Option[Cell] = {
    var coord = getCoordinates(cell).getOrElse(return None)
    getCell(coord.col.toString, coord.row+1)
  }

  override def setCell(row: String, col: Int, value: String): Boolean = {
    val c = getCell(row.toUpperCase(),col)
    if (c.isDefined) {
      c.get.setValue(value.toUpperCase())
      true
    } else false
  }

  override def toString: String = {
    var board: String = "|"
    val a = ArrayBuffer.tabulate(size)(n => 'A'+n)
    a.foreach(col => board += col.toChar + "|")
    board += "\n_______________________________\n"
    grid.foreach(row => {row._2.foreach(col => board += "|" + col._2.getValue)
                         board += "| " + row._1 + "\n"})
    board += "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n"
    board
  }

  override def getSize: Int = {
    size
  }

  def getCoordinates(cell: Cell): Option[Coordinate] = {
    grid.foreach(y => {
      val revMap: mutable.Map[Cell, String] = y._2 map {_.swap}
      if (revMap.contains(cell)) {
        return Some(Coordinate(y._1, revMap(cell).charAt(0)))
      }
    })
    None
  }
}

/*object RegularField {
  import play.api.libs.json._
  implicit val regularFieldWrites = Json.writes[RegularField]
}*/
