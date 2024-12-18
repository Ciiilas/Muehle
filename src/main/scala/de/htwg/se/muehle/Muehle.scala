package de.htwg.se
package muehle

import de.htwg.se.muehle.aview.{Gui, Tui, testGui}
import de.htwg.se.muehle.controller.Controller


object Muehle {
  
  val controller = new Controller()
  val Tui = new Tui(controller)
  val SimpleSwingGui = new testGui(controller)
  //val SwingGUI = new Gui(controller)


  def main(args: Array[String]): Unit = {
    println("Welcome to Muehle")

    SimpleSwingGui.top.visible = true

    //SwingGUI.visible = true
    
    Tui.run()
  }
}