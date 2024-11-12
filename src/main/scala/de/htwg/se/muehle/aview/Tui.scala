package de.htwg.se.muehle
package aview

import de.htwg.se.muehle.controller.Controller
import util.Observer
import scala.io.StdIn

class Tui(controller: Controller) extends Observer{
  def this() = this(new Controller())
  



  def run(): Unit = {

    while(true) {
      try {
        var textInput: String = StdIn.readLine()
        val coords = getCoords(textInput.split(" ")(1))

        val textProcessing: Array[String] = textInput.split(" ")
        if (textProcessing.length == 2) {
          textProcessing(0) match {
            case "move" => print("try to move from: (" + coords(0)(0) + ", " + coords(0)(1) + ") to (" + coords(1)(0) + ", " + coords(1)(1) + ")")
            case "set" => print("try to set Stone at: (" + coords(0)(0) + ", " + coords(0)(1) + ")")
            case "jump" => print("try to jump from: (" + coords(0)(0) + ", " + coords(0)(1) + ") to (" + coords(1)(0) + ", " + coords(1)(1) + ")")
            case "remove" => print("try to remove Stone at: (" + coords(0)(0) + ", " + coords(0)(1) + ")")
            case _ => print("Error falsches Eingabeformat")
          }
        }
      } catch {
        case e: ArrayIndexOutOfBoundsException => print("Error falsches Eingabeformat")
        case e: Exception => print("Error falsches Eingabeformat")
      }
      print(controller.game.field.muehleMatrix)

    }
  }
  
  def getCoords(input: String): Array[Array[Int]] = {
    val coords = input.split(";")
    if (coords.length == 2) {
      val oldCoords = coords(0).split(",").map(_.toInt)
      val newCoords = coords(1).split(",").map(_.toInt)
      Array(oldCoords, newCoords)
    } else {
      val oldCoords = coords(0).split(",").map(_.toInt)
      Array(oldCoords)
    }
  }


  override def update(): Unit = {
    
  }
}
