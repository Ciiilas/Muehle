package de.htwg.se.muehle
package controller

import util.Observable

import de.htwg.se.muehle.model.gamefield.{Gamefield, meshComponentInterface}
import de.htwg.se.muehle.model.{Game, PlayerState}
import de.htwg.se.muehle.model.mechanic.Mechanic
import de.htwg.se.muehle.util.Event.Move
import de.htwg.se.muehle.util.{Command, UndoManager}


case class Controller(var game: Game) extends Observable {
  def this() = this(new Game())

  //-----------------------------------------------------
  //undoManager
  //-----------------------------------------------------

  private val undoManager = new UndoManager[Game]


  def undo(): Unit = {
    game = undoManager.undoStep(game)
    PlayerState.next()
    notifyObservers(Move)
  }

  //-----------------------------------------------------
  //mechanic with undoManager inclusive
  //-----------------------------------------------------

  val gamemech: Mechanic = game.getGameMechanic
  def getMuehleBoolean: Boolean = game.muehleBoolean

  def doStep(command: Command[Game]): Unit = {
    game = undoManager.doStep(game, command)
    notifyObservers(Move)
  }

  def setStone(newRing: Int, newPosOnRing: Int): Unit = {
    doStep(new SetCommand(game, newRing, newPosOnRing))
  }


  def moveStone(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Unit = {
    doStep(new MoveCommand(game, oldRing, oldPosOnRing, newRing, newPosOnRing))
  }

  def jumpStone(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Unit = {
    doStep(new JumpCommand(game, oldRing, oldPosOnRing, newRing, newPosOnRing))
  }

  def removeStone(newRing: Int, newPosOnRing: Int): Unit = {
    doStep(new RemoveCommand(game, newRing, newPosOnRing))
  }
  
  def checkForMuehle(ring: Int, posOnRing: Int): Boolean = {
    game.isMuehle(ring, posOnRing)
  }

  //-----------------------------------------------------
  //field
  //-----------------------------------------------------

  val field: Gamefield = game.getField
  override def toString: String = game.field.muehleMatrix.toString

  def setDecorator(enabled: Boolean): Unit = {
    game.setDecorator(enabled)
  }

  def getMesh: meshComponentInterface = {
    game.getMesh
  }
  
  
}

