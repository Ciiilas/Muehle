package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.mechanic.*
import de.htwg.se.muehle.model.gamefield.*




case class Game(mech: Mechanic, field: Gamefield, var muehleBoolean: Boolean) {
  def this() = this(Mechanic(), new Gamefield(), false)
  def this(gamefield: Gamefield) = this(Mechanic(), gamefield, false)

  //-----------------------------------------------------
  //mechanic
  //-----------------------------------------------------
  def getGameMechanic: Mechanic = mech


  def setStone(ring: Int, posOnRing: Int): Game = {
    if (PlayerState.roundCount < 9) {
      if (mech.isSetLegal(field, ring, posOnRing)) {
        val newField = mech.setStone(field, ring, posOnRing, PlayerState.stone)
        if (!isMuehle(ring, posOnRing)) {
          PlayerState.next()
          PlayerState.incrementCount()
        } else {
          this.muehleBoolean = true
        }
        return Game(this.mech, newField, this.muehleBoolean)
      }
    }
    println("Error! Es können keine weiteren Steine gesetzt werden!")
    Game(this.mech, field, this.muehleBoolean)

  }

  def moveStone(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game = {
    if (PlayerState.roundCount == 9 && field.muehleMatrix.flatten.count((stone:Stone) => stone == PlayerState.stone) > 3) {
      if (mech.isMoveLegal(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)) {
        val newField: Gamefield = mech.moveStone(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)
        if (!isMuehle(newRing, newPosOnRing)) {
          PlayerState.next()
        } else {
          this.muehleBoolean = true
        }
        return Game(this.mech, newField, this.muehleBoolean)
      }
    }
    println("Error! Stein darf nicht bewegt werden!")
    Game(this.mech, field, this.muehleBoolean)
  }

  def jumpStone(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game = {
    if (PlayerState.roundCount == 9 && field.muehleMatrix.flatten.count((stone:Stone) => stone == PlayerState.stone) <= 3) {
      if (mech.isJumpLegal(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)) {
        val newField: Gamefield = mech.jumpStone(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)
        if (!isMuehle(newRing, newPosOnRing)) {
          PlayerState.next()
        } else {
          this.muehleBoolean = true
        }
        return Game(this.mech, newField, this.muehleBoolean)
      }
    }
    println("Error! Mit dem Stein darf nicht gesprungen werden!")
    Game(this.mech, field, this.muehleBoolean)
  }

  def removeStone(ring: Int, posOnRing: Int): Game = {
    if (!muehleBoolean) {
      println("Error! Es gibt keine Mühle, es darf kein Stein entfernt werden" + "!")
      return Game(this.mech, this.field, this.muehleBoolean)
    }
    if (mech.isRemoveLegal(field, ring, posOnRing, PlayerState.stone)) {
     this.muehleBoolean = false
     val newField: Gamefield = mech.removeStone(field, ring, posOnRing, PlayerState.stone)
     PlayerState.next()
     if (PlayerState.roundCount < 9) {
       PlayerState.incrementCount()
     }
     return Game(this.mech, newField, this.muehleBoolean)
    }
    println("Error! Der Stein kann nicht entfernt werden!")
    Game(this.mech, this.field, this.muehleBoolean)
  }

  def isMuehle(ring: Int, posOnRing: Int): Boolean = {
    mech.evaluateStrategy.checkForMuehle(field, ring, posOnRing, PlayerState.stone)
  }

  
  //-----------------------------------------------------
  //gamefield
  //-----------------------------------------------------

  def getField: Gamefield = field

  private var useDecorator: Boolean = false

  def setDecorator(enabled: Boolean): Unit = {
    useDecorator = enabled
  }

  def getMesh: meshComponentInterface = {
    val baseMesh = ConcreteMesh(field)
    if (useDecorator) {
      ConcreteDecoratorMesh(baseMesh)
    } else {
      baseMesh
    }
  }


  //-----------------------------------------------------
  //game
  //-----------------------------------------------------
  
  def getCurrentPlayerState(): Stone = {
    PlayerState.stone
  }

}


object PlayerState:
  var stone: Stone = Stone.White
  def getStone: Stone = stone
  def player: String = stone.toString
  def next(): Unit = if stone.equals(Stone.White) then stone = Stone.Black else stone = Stone.White
  def undo(): Unit = {
    if stone.equals(Stone.White) then stone = Stone.Black else stone = Stone.White
    if stone.equals(Stone.White) then roundCount = roundCount - 1 else roundCount = roundCount
  }
  var roundCount: Int = 0
  def incrementCount(): Unit = if stone.equals(Stone.White) then roundCount = roundCount + 1 else roundCount = roundCount

