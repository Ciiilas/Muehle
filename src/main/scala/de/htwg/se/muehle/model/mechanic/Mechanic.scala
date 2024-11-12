package de.htwg.se.muehle.model.mechanic

import de.htwg.se.muehle.model.gamefield.{Gamefield, Stone}

case class Mechanic(turns: Int) {
  def this() = this(0)

  // Methode zur Berechnung der erlaubten Züge
  def allowedMoves(field: Gamefield, ringIndex: Int, posOnRing: Int): List[(Int, Int)] = {
    // Alle möglichen Bewegungen um einen Schritt
    val potentialMoves = List(
      (ringIndex - 1, posOnRing), // Einen Ring tiefer, gleiche Position
      (ringIndex + 1, posOnRing), // Einen Ring höher, gleiche Position
      (ringIndex, (posOnRing - 1 + 8) % 8), // Gleicher Ring, eine Position nach links (zyklisch)
      (ringIndex, (posOnRing + 1) % 8)      // Gleicher Ring, eine Position nach rechts (zyklisch)
    )

    // Filtern nach erlaubten Zügen mit isMoveAllowed
    potentialMoves.filter { case (newRing, newPos) =>
      isMoveAllowed(field: Gamefield, ringIndex, posOnRing, newRing, newPos)
    }
  }

  // Methode zur Überprüfung eines Zugs
  def isMoveAllowed(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Boolean = {
    // Prüfen, ob die neue Position innerhalb der Spielfeldgrenzen liegt
    val isWithinBounds =
      newRing >= 0 && newRing < field.muehleMatrix.size &&
      newPosOnRing >= 0 && newPosOnRing < field.muehleMatrix(newRing).size

    // Prüfen, ob die Bewegung eine Position entlang des Rings ist
    val isSameRingMove =
      oldRing == newRing &&
        (newPosOnRing == (oldPosOnRing + 1) % 8 || newPosOnRing == (oldPosOnRing - 1 + 8) % 8) // zyklische Bewegung

    // Prüfen, ob der Ringwechsel erlaubt ist
    val isValidRingMove = (oldRing, newRing) match {
      case (0, 1) | (1, 0) | (1, 2) | (2, 1) => true
      case _ => false
    }

    // Prüfen, ob die Bewegung erlaubt ist
    val isEvenPosition = oldPosOnRing % 2 == 0
    isWithinBounds && (
      isSameRingMove || // Bewegung auf demselben Ring
        (!isEvenPosition && isValidRingMove) // Ringwechsel nur, wenn ungerade Position
      )
  }

  // Update method for turns
  def updateTurns(newturns: Int): Mechanic =
    this.copy(turns = newturns)


  def isSetLegal(field: Gamefield, ring: Int, posOnRing: Int): Boolean = {
    field.muehleMatrix(ring)(posOnRing) == Stone.Empty
  }

  def setStone(field: Gamefield, ring: Int, posOnRing: Int): Gamefield = {

    //todo

    return null
  }

  def isMoveLegal(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Boolean = {
    val vec = field.muehleMatrix
    //todo
    
    //moven auf selben ring +/-1 in der mitte ok
    //moven auf selben ring 0 -> max
    //moven auf selben ring max -> 0
    //moven auf neuen ring +/-1
    //moven auf selben platz
    return false
  }

  def MoveStone(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Gamefield = {

    //todo

    return null
  }

  def isJumpLegal(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Boolean = {

    //todo

    return false
  }

  def JumpStone(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Gamefield = {

    //todo

    return null
  }

  def isRemoveLegal(field: Gamefield, ring: Int, posOnRing: Int): Boolean = {

    //todo

    return false
  }

  def RemoveStone(field: Gamefield, ring: Int, posOnRing: Int): Gamefield = {

    //todo

    return null
  }

  def checkForMuehle(field: Gamefield, ring: Int, posOnRing: Int): Boolean = {

    //todo

    return false
  }









  
  
}
