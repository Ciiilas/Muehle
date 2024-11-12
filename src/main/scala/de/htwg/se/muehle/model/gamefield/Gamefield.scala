package de.htwg.se.muehle.model.gamefield

import de.htwg.se.muehle.model.gamefield.Stone

// Step 2: Define the case class with default values and update methods
case class Gamefield(val muehleMatrix: Vector[Vector[Stone]]):
  def this(n: Int, m: Int, default: Stone) = this(Vector.fill(n)(Vector.fill(m)(Stone.Empty)))
  def this() = this(3, 8, Stone.Empty)


  // Update method for a specific enumMatrix value at (row, col)
  def withEnumAt(row: Int, col: Int, newEnum: Stone): Gamefield = {
    val updatedMatrix =
      if row >= 0 && row < muehleMatrix.length && col >= 0 && col < muehleMatrix(row).length then
        muehleMatrix.updated(row, muehleMatrix(row).updated(col, newEnum))
      else muehleMatrix // No change if out of bounds

    this.copy(muehleMatrix = updatedMatrix)
  }








