package de.htwg.se
package muehle

//import de.htwg.se.muehle.aview.Tui
//import de.htwg.se.muehle.controller.Controller





object Muehle {


  val eol: String = sys.props("line.separator")

  def bar(lineWidth: Int, lineNum: Int, eolB: Boolean): String = ("0" + "─" * lineWidth) * lineNum + "0" + (if (eolB) eol else "")

  def spacer(lineWidth: Int, lineNum: Int, eolB: Boolean): String = ("│" + " " * lineWidth) * lineNum + "│" + (if (eolB) eol else "")

  def middelBar(lineWidth: Int, lineNum: Int): String = bar(2, lineNum, false) + " " * 3 + bar(2, lineNum, true)

  def tinyBarFront(lineNum: Int): String = ("│" + " " * 2) * lineNum

  def tinyBarBack(lineNum: Int, eolB: Boolean): String = (" " * 2 + "│") * lineNum + (if (eolB) eol else "")

  def mesh(lineWidth: Int, lineNum: Int): String = bar(lineWidth, lineNum, true) +
    spacer(lineWidth, lineNum, true) * 2 +
    tinyBarFront(lineNum - 1) + bar(lineWidth - 3, lineNum, false) + tinyBarBack(lineNum - 1, true) +
    (tinyBarFront(lineNum - 1) + spacer(lineWidth - 3, lineNum, false) + tinyBarBack(lineNum - 1, true)) * 2 +
    tinyBarFront(lineNum) + bar(lineWidth - 6, lineNum, false) + tinyBarBack(lineNum, true) +
    tinyBarFront(lineNum) + "│" + " " * 3 + "│" + tinyBarBack(lineNum, true) +
    middelBar(lineWidth, lineNum) +
    tinyBarFront(lineNum) + "│" + " " * 3 + "│" + tinyBarBack(lineNum, true) +
    tinyBarFront(lineNum) + bar(lineWidth - 6, lineNum, false) + tinyBarBack(lineNum, true) +
    (tinyBarFront(lineNum - 1) + spacer(lineWidth - 3, lineNum, false) + tinyBarBack(lineNum - 1, true)) * 2 +
    tinyBarFront(lineNum - 1) + bar(lineWidth - 3, lineNum, false) + tinyBarBack(lineNum - 1, true) +
    spacer(lineWidth, lineNum, true) * 2 +
    bar(lineWidth, lineNum, false)




  //val controller = new Controller(new Board)
  //val Tui = new Tui(controller)
  //val Board = new Board_output_on_console

  //todo komment delete later
  def main(args: Array[String]): Unit = {
    println("Welcome to Muehle")

    val lineWidth: Int = 7
    val lineNum: Int = 2
    val boolean: Boolean = true



    println(mesh(lineWidth, lineNum))


//    val size: Int = 36
//    val player = Player(Figure.Boot)
//    val card = Card(Street_Names.Schlossallee, 0, Figure.Empty)
//    val controller = new Controller(Board().fillCards)
//    val tui = new Tui(controller)
//    tui.run()
  }
}