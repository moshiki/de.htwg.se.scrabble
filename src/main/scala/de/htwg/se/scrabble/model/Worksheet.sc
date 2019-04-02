case class Cell(letter: String){
  def isSet: Boolean = letter != null

}

val cell1 = Cell("M")

cell1
cell1.letter
cell1.isSet

val cell2 = cell1.copy(null)

cell2
cell2.letter
cell2.isSet