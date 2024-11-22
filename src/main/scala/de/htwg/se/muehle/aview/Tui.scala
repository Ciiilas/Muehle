package de.htwg.se.muehle
package aview

import de.htwg.se.muehle.controller.Controller
import de.htwg.se.muehle.model.gamefield.Mesh
import util.Observer

import scala.io.StdIn

class Tui(controller: Controller) extends Observer{
  controller.add(this)

  def run(): Unit = {

    while(true) {
      try {
        println(controller.game.mesh())
        var textInput: String = StdIn.readLine()
        val coords = getCoords(textInput.split(" ")(1))

        val textProcessing: Array[String] = textInput.split(" ")
        if (textProcessing.length == 2) {
          textProcessing(0) match {
            case "set" => println("try to set Stone at: (" + coords(0)(0) + ", " + coords(0)(1) + ")"); controller.setStone(coords(0)(0), coords(0)(1))
            case "move" => println("try to move from: (" + coords(0)(0) + ", " + coords(0)(1) + ") to (" + coords(1)(0) + ", " + coords(1)(1) + ")"); controller.moveStone(coords(0)(0), coords(0)(1), coords(1)(0), coords(1)(1))
            case "jump" => println("try to jump from: (" + coords(0)(0) + ", " + coords(0)(1) + ") to (" + coords(1)(0) + ", " + coords(1)(1) + ")"); controller.jumpStone(coords(0)(0), coords(0)(1), coords(1)(0), coords(1)(1))
            case "remove" => println("try to remove Stone at: (" + coords(0)(0) + ", " + coords(0)(1) + ")"); controller.removeStone(coords(0)(0), coords(0)(1))
            case _ => println("Error falsches Eingabeformat")
          }
        }
      } catch {
        case e: ArrayIndexOutOfBoundsException => println("Error falsches Eingabeformat")
        case e: Exception => println("Error falsches Eingabeformat")
      }
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
    controller.game.field.muehleMatrix.foreach { row =>
      println(row.map(_.toString).mkString(" ")) // Jedes Element der Zeile in String umwandeln und mit Leerzeichen trennen
    }
  }
}
