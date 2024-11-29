package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.mechanic.*
import de.htwg.se.muehle.model.gamefield.*

case class Game(mech: Mechanic, field: Gamefield) {
  def this() = this(Mechanic(), new Gamefield())


  //-----------------------------------------------------
  //mechanic
  //-----------------------------------------------------

  def setStone(ring: Int, posOnRing: Int): Game = {
    if (PlayerState.roundCount < 9) {
      if (mech.isSetLegal(field, ring, posOnRing)) {
        val newField = mech.setStone(field, ring, posOnRing, PlayerState.stone)
        PlayerState.next()
        PlayerState.incrementCount()
        return Game(this.mech, newField)

      }
    }
    println("Error! Es können keine weiteren Steine gesetzt werden!")
    Game(this.mech, field)

  }

  def moveStone(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game = {
    if (PlayerState.roundCount == 9 && field.muehleMatrix.flatten.count((stone:Stone) => stone == PlayerState.stone) > 3) {
      if (mech.isMoveLegal(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)) {
        val newField: Gamefield = mech.moveStone(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)
        PlayerState.next()
        return Game(this.mech, newField)
      }
    }
    println("Error! Stein darf nicht bewegt werden!")
    Game(this.mech, field)
  }

  def jumpStone(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game = {
    if (PlayerState.roundCount == 9 && field.muehleMatrix.flatten.count((stone:Stone) => stone == PlayerState.stone) <= 3) {
      if (mech.isJumpLegal(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)) {
        val newField: Gamefield = mech.jumpStone(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)
        PlayerState.next()
        return Game(this.mech, newField)
      }
    }
    println("Error! Mit dem Stein darf nicht gesprungen werden!")
    Game(this.mech, field)
  }

  def removeStone(ring: Int, posOnRing: Int): Game = {
    if (mech.evaluateStrategy.checkForMuehle(field, ring, posOnRing, PlayerState.stone)) {
       if (mech.isRemoveLegal(field, ring, posOnRing, PlayerState.stone)) {
         val newField: Gamefield = mech.removeStone(field, ring, posOnRing, PlayerState.stone)
         return Game(this.mech, newField)
       }
    }
    println("Error! Der Stein kann nicht entfernt werden!")
    Game(this.mech, field)
  }

  
  //-----------------------------------------------------
  //gamefield
  //-----------------------------------------------------

  private var useDecorator: Boolean = false

  def setDecorator(enabled: Boolean): Unit = {
    useDecorator = enabled
  }

  def getMesh: meshComponentInterface = {
    val baseMesh = new ConcreteMesh(field)
    if (useDecorator) {
      new ConcreteDecoratorMesh(baseMesh) 
    } else {
      baseMesh
    }
  }

  
  


}

object PlayerState:
  var stone: Stone = Stone.White
  def player: String = stone.toString
  def next(): Unit = if stone.equals(Stone.White) then stone = Stone.Black else stone = Stone.White
  var roundCount: Int = 0
  def incrementCount():Unit = if stone.equals(Stone.White) then roundCount = roundCount + 1 else roundCount = roundCount


