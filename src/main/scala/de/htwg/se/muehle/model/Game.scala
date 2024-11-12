package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.mechanic.*
import de.htwg.se.muehle.model.gamefield.*

case class Game(mech: Mechanic, field: Gamefield) {
  def this() = this(new Mechanic(), new Gamefield())


  //-----------------------------------------------------
  //mechanic
  //-----------------------------------------------------

  def isSetLegal(ring: Int, posOnRing: Int): Boolean = {
    mech.isSetLegal(field, ring, posOnRing)
  }

  def SetStone(ring: Int, posOnRing: Int): Game = {
    val newfield = mech.setStone(field, ring, posOnRing)
    this.copy(field = newfield)
  }

  def isMoveLegal(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Boolean = {
    mech.isMoveLegal(field, oldRing, oldPosOnRing, newRing, newPosOnRing)
  }

  def MoveStone(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game = {
    val newfield = mech.MoveStone(field, oldRing, oldPosOnRing, newRing, newPosOnRing)
    this.copy(field = newfield)
  }

  def isJumpLegal(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Boolean = {
    mech.isJumpLegal(field, oldRing, oldPosOnRing, newRing, newPosOnRing)
  }

  def JumpStone(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game = {
    val newfield = mech.JumpStone(field, oldRing, oldPosOnRing, newRing, newPosOnRing)
    this.copy(field = newfield)
  }

  def isRemoveLegal(ring: Int, posOnRing: Int): Boolean = {
    mech.isRemoveLegal(field, ring, posOnRing)
  }

  def RemoveStone(ring: Int, posOnRing: Int): Game = {
    val newfield = mech.RemoveStone(field, ring, posOnRing)
    this.copy(field = newfield)
  }

  def checkForMuehle(ring: Int, posOnRing: Int): Boolean = {
    mech.checkForMuehle(field, ring, posOnRing)
  }

  
  //-----------------------------------------------------
  //gamefield
  //-----------------------------------------------------











  
  
}
