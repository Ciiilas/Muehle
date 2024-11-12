package de.htwg.se.muehle
package controller

import util.Observable
import de.htwg.se.muehle.model.Game
import de.htwg.se.muehle.model.gamefield.Stone
import de.htwg.se.muehle.model.gamefield.Stone.White


case class Controller(game: Game) extends Observable{
  def this() = this(new Game())

  override def toString: String = "Hallo"

  //-----------------------------------------------------
  //mechanic
  //-----------------------------------------------------

  def tryToSetStone(newRing: Int, newPosOnRing: Int): Controller = {
    val newgame = game.SetStone(newRing, newPosOnRing)
    this.copy(game = newgame)
  }

  def isSetLegal(newRing: Int, newPosOnRing: Int): Boolean = {
    game.isSetLegal(newRing, newPosOnRing)
  }

  def tryToMoveStone(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Controller = {
    val newgame = game.MoveStone(oldRing, oldPosOnRing, newRing, newPosOnRing)
    this.copy(game = newgame)
  }

  def isMoveLegal(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Boolean = {
    game.isMoveLegal(oldRing, oldPosOnRing, newRing, newPosOnRing)
  }

  def tryToJumpStone(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Controller = {
    val newgame = game.JumpStone(oldRing, oldPosOnRing, newRing, newPosOnRing)
    this.copy(game = newgame)
  }

  def isJumpLegal(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Boolean = {
    game.isJumpLegal(oldRing, oldPosOnRing, newRing, newPosOnRing)
  }

  def tryToRemoveStone(newRing: Int, newPosOnRing: Int): Controller = {
    val newgame = game.RemoveStone(newRing, newPosOnRing)
    this.copy(game = newgame)
  }

  def isRemoveLegal(newRing: Int, newPosOnRing: Int): Boolean = {
    game.isRemoveLegal(newRing, newPosOnRing)
  }

  object PlayerState:
    var stone = Stone.White
    def player = stone.toString
    def next = if stone == White then stone = Stone.Black else stone = Stone.White






}