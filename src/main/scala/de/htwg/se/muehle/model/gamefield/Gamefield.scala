package de.htwg.se.muehle.model.gamefield

import de.htwg.se.muehle.model.gamefield.Stone

class Gamefield(size: Int):
  var field: Vector[Vector[Stone]] = Vector.fill(size, 8)(Stone.None) // Defaulting to White for initialization

  def displayBoard(): Unit =
    field.foreach(row => println(row.mkString(" ")))

  def getStone(ring: Int, posOnRing: Int): Stone = {
    field(ring)(posOnRing)
  }

  def tech_SetStone(ring: Int, posOnRing: Int, color: Stone) = {
    field(ring)(posOnRing) = color
  }


/*@main def runExample() =
  val grid = Gamefield(3) // Creates a 3x3 board of Stone.White
  grid.displayBoard()

  print(grid.field(0))*/





