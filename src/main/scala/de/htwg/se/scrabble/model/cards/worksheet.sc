import scala.collection.mutable.ListBuffer

object CardStack {
  private val cardCount: Map[String, Integer] = Map("a"->6, "b"->2, "c"->4,"d"->6,"e"->16,"f"->3,"g"->3,"h"->5,"i"->9,"j"->1,
    "k"->2,"l"->4,"m"->4,"n"->10,"o"->4,"p"->1,"q"->1,"r"->7,"s"->8,"t"->5,"u"->6,"v"->1,"w"->2,"x"->1,"y"->1,"z"->2,"#"->2)
  private var cardList: ListBuffer[String] = ListBuffer()

  cardCount.foreach( x => for (i <- 1 to x._2) { cardList+=x._1})

  def getCard: String = {
    val r = util.Random.nextInt(cardCount.size)
    val c = cardList.remove(r)
    c
  }
}