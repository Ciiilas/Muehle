package de.htwg.se.muehle.model.gamefield

import de.htwg.se.muehle.model.gamefield.Stone


class Gamefield(size: Int):
  val field: Vector[Vector[Stone]] = Vector.fill(8, size)(Stone.None) // Defaulting to White for initialization

  def displayBoard(): Unit =
    field.foreach(row => println(row.mkString(" ")))

@main def runExample() =
  val field = Gamefield(3) // Creates a 3x3 board of Stone.White
  field.displayBoard()
  print(field(0)(0))


