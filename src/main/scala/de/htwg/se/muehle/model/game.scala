package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.mechanic.*
import de.htwg.se.muehle.model.gamefield.*

case class game(mech: Mechanic, field: Gamefield) {
  
  //-----------------------------------------------------
  //mechanic
  //-----------------------------------------------------

  def isSetLegal(ring: Int, posOnRing: Int): Boolean = {
    mech.isSetLegal(ring, posOnRing)
  }

  def SetStone(ring: Int, posOnRing: Int): Boolean = {
    mech.SetStone(ring, posOnRing)
  }

  def isMoveLegal(ring: Int, posOnRing: Int): Boolean = {
    mech.isMoveLegal(ring, posOnRing)
  }

  def MoveStone(ring: Int, posOnRing: Int): Boolean = {
    mech.MoveStone(ring, posOnRing)
  }

  def isJumpLegal(ring: Int, posOnRing: Int): Boolean = {
    mech.isJumpLegal(ring, posOnRing)
  }

  def JumpStone(ring: Int, posOnRing: Int): Boolean = {
    mech.JumpStone(ring, posOnRing)
  }

  def isRemoveLegal(ring: Int, posOnRing: Int): Boolean = {
    mech.isRemoveLegal(ring, posOnRing)
  }

  def RemoveStone(ring: Int, posOnRing: Int): Boolean = {
    mech.RemoveStone(ring, posOnRing)
  }

  def checkForMuehle(ring: Int, posOnRing: Int): Boolean = {
    mech.checkForMuehle(ring, posOnRing)
  }

  
  //-----------------------------------------------------
  //gamefield
  //-----------------------------------------------------
  
  
  
  
  
  
}
