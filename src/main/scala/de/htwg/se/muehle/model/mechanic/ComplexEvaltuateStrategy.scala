package de.htwg.se.muehle.model.mechanic

import de.htwg.se.muehle.model.gamefield.{Gamefield, Stone}

class ComplexEvaltuateStrategy extends EvaluateStrategy {
  override def checkForMuehle(field: Gamefield, newRing: Int, newPosOnRing: Int, stone: Stone): Boolean = {
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
