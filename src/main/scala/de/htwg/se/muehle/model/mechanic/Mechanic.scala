package de.htwg.se.muehle.model.mechanic

import de.htwg.se.muehle.model.gamefield.{Gamefield, Stone}

case class Mechanic() {


  // Methode zur Überprüfung eines Zugs
  private def isMoveAllowed(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Boolean = {
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

  private def countStones(field: Gamefield, stoneColor: Stone): Int = {
    field.muehleMatrix.flatten.count((stone:Stone) => stone == stoneColor)
  }
  

  def isSetLegal(field: Gamefield, ring: Int, posOnRing: Int): Boolean = {
    if (!(ring >= 0 && ring < field.muehleMatrix.size &&
        posOnRing >= 0 && posOnRing < field.muehleMatrix(ring).size)) {
      println("Error! Position ist außerhalb des Spielfeldes")
      return false
    }

    if (field.muehleMatrix(ring)(posOnRing) == Stone.Empty) {
      true
    } else {
      println("Error! Der Stein darf nur auf eine leere Position platziert werden!")
      false
    }
  }

  def setStone(field: Gamefield, ring: Int, posOnRing: Int, stone: Stone): Gamefield = {
    if (isSetLegal(field, ring, posOnRing)) {
      val updateRing: Vector[Vector[Stone]] = field.muehleMatrix.updated(ring, field.muehleMatrix(ring).updated(posOnRing, stone))
      Gamefield(updateRing)
    } else {
      field
    }
  }

  def isMoveLegal(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int, stone: Stone): Boolean = {
    //when Stone on oldPos equals the player Stone then continue

    if (!(field.muehleMatrix(oldRing)(oldPosOnRing) == stone)) {
        println("Error! Stein der bewegt wird ist nicht Ihr Stein!")
        return false
      }
      //when Stone on newPos equals the player Stone then continue
      if (!(field.muehleMatrix(newRing)(newPosOnRing) == Stone.Empty)) {
        println("Error! Position zu der bewegt wird ist nicht Leer!")
        return false
      }

      if (isMoveAllowed(field, oldRing, oldPosOnRing, newRing, newPosOnRing)) {
        true
      } else {
        println("Error! Der Zug ist so nicht erlaubt")
        false
      }
  }

  def moveStone(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int, stone: Stone): Gamefield = {
    if (isMoveLegal(field, oldRing, oldPosOnRing, newRing, newPosOnRing, stone)) {
      val removeStone: Vector[Vector[Stone]] = field.muehleMatrix.updated(oldRing, field.muehleMatrix(oldRing).updated(oldPosOnRing, Stone.Empty))
      val placeStone: Vector[Vector[Stone]] = removeStone.updated(newRing, removeStone(newRing).updated(newPosOnRing, stone))
      Gamefield(placeStone)
    } else {
      println("Error, Stein kann nicht bewegt werden!")
      field
    }
  }

  def isJumpLegal(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int, stone: Stone): Boolean = {
    if (!(oldRing >= 0 && oldRing < field.muehleMatrix.size &&
      oldPosOnRing >= 0 && oldPosOnRing < field.muehleMatrix(oldRing).size)) {
      println("Error! oldPosition ist außerhalb des Spielfeldes")
      return false
    }
    if (!(newRing >= 0 && newRing < field.muehleMatrix.size &&
      newPosOnRing >= 0 && newPosOnRing < field.muehleMatrix(newRing).size)) {
      println("Error! newPosition ist außerhalb des Spielfeldes")
      return false
    }

    if (!(field.muehleMatrix(oldRing)(oldPosOnRing) == stone)) {
      println("Error! Stein der bewegt wird ist nicht Ihr Stein!")
      return false
    }
    //when Stone on newPos equals the player Stone then continue
    if (!(field.muehleMatrix(newRing)(newPosOnRing) == Stone.Empty)) {
      println("Error! Position zu der bewegt wird ist nicht Leer!")
      return false
    }
    true
  }

  def jumpStone(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int, stone: Stone): Gamefield = {
    if (isJumpLegal(field, oldRing, oldPosOnRing, newRing, newPosOnRing, stone)) {
      val removeStone = field.muehleMatrix.updated(oldRing, field.muehleMatrix(oldRing).updated(oldPosOnRing, Stone.Empty))
      val placeStone = removeStone.updated(newRing, removeStone(newRing).updated(newPosOnRing, stone))
      Gamefield(placeStone)
    } else {
      println("Error, Stein kann nicht bewegt werden!")
      field
    }
  }

  def isRemoveLegal(field: Gamefield, ring: Int, posOnRing: Int, stone: Stone): Boolean = {
    if (!(ring >= 0 && ring < field.muehleMatrix.size &&
      posOnRing >= 0 && posOnRing < field.muehleMatrix(ring).size)) {
      println("Error! Position ist außerhalb des Spielfeldes")
      return false
    }

    if (field.muehleMatrix(ring)(posOnRing) == stone) {
      println("Error! Stein der entfernt werden soll ist nicht Ihr Stein!")
      return false
    }

    if (field.muehleMatrix(ring)(posOnRing) == Stone.Empty) {
      println("Error! Die Position die Sie entfernen wollen ist Leer!")
      false
    } else {
      true
    }
  }

  def removeStone(field: Gamefield, ring: Int, posOnRing: Int, stone: Stone): Gamefield = {
    if (isRemoveLegal(field, ring, posOnRing, stone)) {
      val updateRing = field.muehleMatrix.updated(ring, field.muehleMatrix(ring).updated(posOnRing, Stone.Empty))
      Gamefield(updateRing)
    } else {
      field
    }
  }

  def checkForMuehle(field: Gamefield, newRing: Int, newPosOnRing: Int, stone: Stone): Boolean = {
    
    //check Muehle from Ring Center
    if (newPosOnRing % 2 == 1) {
      //checking left right
      //newPosOnRing == (oldPosOnRing + 1) % 8 || newPosOnRing == (oldPosOnRing - 1 + 8) % 8
      if (field.muehleMatrix(newRing)((newPosOnRing - 1 + 8) % 8) == stone && field.muehleMatrix(newRing)((newPosOnRing + 1) % 8) == stone) {
        return true
      }
      //checking down up
      if (field.muehleMatrix((newRing - 1 + 3) % 3) == stone && field.muehleMatrix((newRing + 1) % 3) == stone) {
        return true
      }
      return false
    }
    //check Muehle from Ring Corner
    if (newPosOnRing % 2 == 0) {
            //Corner right
      if (field.muehleMatrix(newRing)((newPosOnRing - 1 + 8) % 8) == stone && field.muehleMatrix(newRing)((newPosOnRing - 2 + 8) % 8) == stone 
        || field.muehleMatrix(newRing)((newPosOnRing + 1) % 8) == stone && field.muehleMatrix(newRing)((newPosOnRing + 2) % 8) == stone) {
        return true
      }
    }
    false        
  }
  
}
