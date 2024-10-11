package de.htwg.se.muehle
package aview

import controller.Controller
import util.Observer



class Tui(controller: Controller) extends Observer{

  //hallo, hiermit wird versucht ein merge conflict zu erzeugen

  controller.add(this)
  
  def run(): Unit =
    println("Hallo")







  override def update(): Unit = {
    
  }
}
