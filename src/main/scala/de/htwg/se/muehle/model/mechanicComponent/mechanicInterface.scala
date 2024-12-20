package de.htwg.se.muehle.model.mechanicComponent

import de.htwg.se.muehle.model.gameFieldComponent.gameFieldInterface
import de.htwg.se.muehle.model.gameFieldComponent.gamefield.{Gamefield, Stone}
import de.htwg.se.muehle.model.mechanicComponent.mechanic.{EvaluateStrategy, Mechanic}

trait mechanicInterface(CounterOfSetStone: Int, evaluateStrategy: EvaluateStrategy) {
  //Getter
  def getEvaluateStrategy: EvaluateStrategy = evaluateStrategy
  def getCounterOfSetStone: Int = CounterOfSetStone
  
  //Methoden
  def countStones(field: gameFieldInterface, stoneColor: Stone): Int
  def isSetLegal(field: gameFieldInterface, ring: Int, posOnRing: Int): Boolean
  def setStone(field: gameFieldInterface, ring: Int, posOnRing: Int, stone: Stone): (mechanicInterface, gameFieldInterface)
  def isMoveAllowed(field: gameFieldInterface, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Boolean
  def isMoveLegal(field: gameFieldInterface, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int, stone: Stone): Boolean
  def moveStone(field: gameFieldInterface, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int, stone: Stone): gameFieldInterface
  def isJumpLegal(field: gameFieldInterface, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int, stone: Stone): Boolean
  def jumpStone(field: gameFieldInterface, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int, stone: Stone): gameFieldInterface
  def isRemoveLegal(field: gameFieldInterface, ring: Int, posOnRing: Int, stone: Stone): Boolean
  def removeStone(field: gameFieldInterface, ring: Int, posOnRing: Int, stone: Stone): gameFieldInterface
}