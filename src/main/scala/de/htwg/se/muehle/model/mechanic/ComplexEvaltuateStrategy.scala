package de.htwg.se.muehle.model.mechanic

import de.htwg.se.muehle.model.gamefield.{Gamefield, Stone}

class ComplexEvaltuateStrategy extends EvaluateStrategy {
  override def checkForMuehle(field: Gamefield, newRing: Int, newPosOnRing: Int, stone: Stone): Boolean = {
      //check Muehle an this Ring Center
      if (newPosOnRing % 2 == 1) {
        //checking On Ring
        //newPosOnRing == (oldPosOnRing + 1) % 8 || newPosOnRing == (oldPosOnRing - 1 + 8) % 8
        if ((field.muehleMatrix(newRing)((newPosOnRing - 1 + 8) % 8) == stone) && (field.muehleMatrix(newRing)((newPosOnRing + 1) % 8) == stone)) {
          return true
        }
        //checking other Rings
        if ((field.muehleMatrix((newRing - 1 + 3) % 3)(newPosOnRing) == stone) && (field.muehleMatrix((newRing + 1) % 3)(newPosOnRing) == stone)) {
          return true
        }
        return false
      }
      //check Muehle from Ring Corner
      if (newPosOnRing % 2 == 0) {
        //Corner right
        if ((field.muehleMatrix(newRing)((newPosOnRing - 1 + 8) % 8) == stone) && (field.muehleMatrix(newRing)((newPosOnRing - 2 + 8) % 8) == stone)
          || (field.muehleMatrix(newRing)((newPosOnRing + 1) % 8) == stone) && (field.muehleMatrix(newRing)((newPosOnRing + 2) % 8) == stone)) {
          return true
        }
      }
      false
    }
}
