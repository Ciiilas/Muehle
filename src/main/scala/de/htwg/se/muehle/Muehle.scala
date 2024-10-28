package de.htwg.se
package muehle

//import de.htwg.se.muehle.aview.Tui
//import de.htwg.se.muehle.controller.Controller

import de.htwg.se.muehle.model.gamefield.Mesh




object Muehle {
  
  //val controller = new Controller(new Board)
  val mesh = new Mesh()
  //val Tui = new Tui(controller)
  //val Board = new Board_output_on_console

  //todo komment delete later
  def main(args: Array[String]): Unit = {
    
    println("Welcome to Muehle")

    val lineWidth: Int = 7
    val lineNum: Int = 2
    val boolean: Boolean = true



    println(mesh.mesh(lineWidth, lineNum))


//    val size: Int = 36
//    val player = Player(Figure.Boot)
//    val card = Card(Street_Names.Schlossallee, 0, Figure.Empty)
//    val controller = new Controller(Board().fillCards)
//    val tui = new Tui(controller)
//    tui.run()
  }
}