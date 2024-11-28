package de.htwg.se.muehle.model.mechanic

import de.htwg.se.muehle.model.gamefield.{Gamefield, Stone}

trait EvaluateStrategy {
  def checkForMuehle(field: Gamefield, newRing: Int, newPosOnRing: Int, stone: Stone): Boolean
}
