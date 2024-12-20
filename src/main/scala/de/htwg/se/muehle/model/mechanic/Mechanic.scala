package de.htwg.se.muehle.model.mechanic

import de.htwg.se.muehle.model.PlayerState
import de.htwg.se.muehle.model.gamefield.{Gamefield, Stone}

case class Mechanic(var setCount: Int = 0, evaluateStrategy: EvaluateStrategy = new ComplexEvaltuateStrategy) {


  def countStones(field: Gamefield, stoneColor: Stone): Int = {
    field.muehleMatrix.flatten.count((stone: Stone) => stone == stoneColor)
  }

  def isSetLegal(field: Gamefield, ring: Int, posOnRing: Int): Boolean = {
    if (!(ring >= 0 && ring < field.muehleMatrix.size &&
        posOnRing >= 0 && posOnRing < field.muehleMatrix(ring).size)) {
      return false
    }
    if (field.muehleMatrix(ring)(posOnRing) == Stone.Empty) {
      true
    } else {
      false
    }
  }

  def setStone(field: Gamefield, ring: Int, posOnRing: Int, stone: Stone): (Mechanic, Gamefield) = {
    if (isSetLegal(field, ring, posOnRing)) {
      val updateRing: Vector[Vector[Stone]] = field.muehleMatrix.updated(ring, field.muehleMatrix(ring).updated(posOnRing, stone))
      val newMechanic = this.copy(setCount = this.setCount + 1)
      (newMechanic, Gamefield(updateRing))
    } else {
      (this, field)
    }
  }


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


  def isMoveLegal(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int, stone: Stone): Boolean = {
    //when Stone on oldPos equals the player Stone then continue

    if (!(field.muehleMatrix(oldRing)(oldPosOnRing) == stone)) {
        return false
      }
      //when Stone on newPos equals the player Stone then continue
      if (!(field.muehleMatrix(newRing)(newPosOnRing) == Stone.Empty)) {
        return false
      }

      if (isMoveAllowed(field, oldRing, oldPosOnRing, newRing, newPosOnRing)) {
        true
      } else {
        false
      }
  }

  def moveStone(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int, stone: Stone): Gamefield = {
    if (isMoveLegal(field, oldRing, oldPosOnRing, newRing, newPosOnRing, stone)) {
      val removeStone: Vector[Vector[Stone]] = field.muehleMatrix.updated(oldRing, field.muehleMatrix(oldRing).updated(oldPosOnRing, Stone.Empty))
      val placeStone: Vector[Vector[Stone]] = removeStone.updated(newRing, removeStone(newRing).updated(newPosOnRing, stone))
      Gamefield(placeStone)
    } else {
      field
    }
  }

  def isJumpLegal(field: Gamefield, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int, stone: Stone): Boolean = {
    if (!(oldRing >= 0 && oldRing < field.muehleMatrix.size &&
      oldPosOnRing >= 0 && oldPosOnRing < field.muehleMatrix(oldRing).size)) {
      return false
    }
    if (!(newRing >= 0 && newRing < field.muehleMatrix.size &&
      newPosOnRing >= 0 && newPosOnRing < field.muehleMatrix(newRing).size)) {
      return false
    }

    if (!(field.muehleMatrix(oldRing)(oldPosOnRing) == stone)) {
      return false
    }
    //when Stone on newPos equals the player Stone then continue
    if (!(field.muehleMatrix(newRing)(newPosOnRing) == Stone.Empty)) {
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
      field
    }
  }

  def isRemoveLegal(field: Gamefield, ring: Int, posOnRing: Int, stone: Stone): Boolean = {
    PlayerState.next()
    val opponentStone = PlayerState.stone
    PlayerState.next()

    if (!(ring >= 0 && ring < field.muehleMatrix.size &&
      posOnRing >= 0 && posOnRing < field.muehleMatrix(ring).size)) {
      return false
    }

    if (field.muehleMatrix(ring)(posOnRing) == stone) {
      return false
    }

    if (field.muehleMatrix(ring)(posOnRing) == Stone.Empty) {
      return false
    }

    if (evaluateStrategy.checkForMuehle(field, ring, posOnRing, opponentStone)) {
      val removableStones = for {
        r <- field.muehleMatrix.indices
        p <- field.muehleMatrix(r).indices
        if field.muehleMatrix(r)(p) == opponentStone && !evaluateStrategy.checkForMuehle(field, r, p, opponentStone)
      } yield (r, p)

      if (removableStones.nonEmpty) {
        return false
      }
    }
    true
  }

  def removeStone(field: Gamefield, ring: Int, posOnRing: Int, stone: Stone): Gamefield = {
    if (isRemoveLegal(field, ring, posOnRing, stone)) {
      val updateRing = field.muehleMatrix.updated(ring, field.muehleMatrix(ring).updated(posOnRing, Stone.Empty))
      Gamefield(updateRing)
    } else {
      field
    }
  }
  
}
