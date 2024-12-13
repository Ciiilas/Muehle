package de.htwg.se
package muehle

import de.htwg.se.muehle.aview.{Gui, Tui}
import de.htwg.se.muehle.controller.Controller

import de.htwg.se.muehle.model.gamefield.Mesh




object Muehle {
  
  val controller = new Controller()
  val mesh = new Mesh()
  val Tui = new Tui(controller)
  val Gui = new Gui(controller)
  //val Board = new Board_output_on_console

  //todo komment delete later
  def main(args: Array[String]): Unit = {


    println("Welcome to Muehle")

    Gui.visible = true
    
    Tui.run()
  }
}