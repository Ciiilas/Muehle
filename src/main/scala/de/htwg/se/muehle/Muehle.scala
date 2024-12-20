package de.htwg.se
package muehle

import de.htwg.se.muehle.aview.{Gui, Tui, testGui}
import de.htwg.se.muehle.controller.controllerComponent.Controller
import de.htwg.se.muehle.gameComponent.Game
import de.htwg.se.muehle.model.gameFieldComponent.gameFieldInterface
import de.htwg.se.muehle.model.gameFieldComponent.gamefield.Gamefield
import de.htwg.se.muehle.model.gameInterface
import de.htwg.se.muehle.model.mechanicComponent.mechanic.Mechanic
import de.htwg.se.muehle.model.mechanicComponent.mechanicInterface


object Muehle {
  val mech: mechanicInterface = new Mechanic
  val gameField: gameFieldInterface = new Gamefield
  val game: gameInterface = new Game(mech, gameField)
  val controller = new Controller(game)
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