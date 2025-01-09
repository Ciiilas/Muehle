package de.htwg.se.muehle.controller.controllerComponent

import de.htwg.se.muehle.controller.*
import de.htwg.se.muehle.model.gameComponent.Game
import de.htwg.se.muehle.model.gameFieldComponent.gamefield.{Stone, meshComponentInterface}
import de.htwg.se.muehle.model.{GameStateEnum, gameInterface}
import de.htwg.se.muehle.util.{Command, Event, Observable, UndoManager}
import de.htwg.se.muehle.MuehleModule
import com.google.inject.{Guice, Inject, Injector}


case class Controller @Inject() (var game: gameInterface) extends controllerInterface with Observable {
  //def this() = this(new gameInterface(game))
  
  private val injector: Injector = Guice.createInjector(new MuehleModule)

  override def getGame: gameInterface = game
  
  //-----------------------------------------------------
  //undoManager
  //-----------------------------------------------------
  
  
  private val undoManager = new UndoManager[gameInterface]


  def undo(): Unit = {
    game = undoManager.undoStep(game)
    notifyObservers(Event.Set)
  }

  //-----------------------------------------------------
  //mechanic with undoManager inclusive
  //-----------------------------------------------------

  //val gamemech: Mechanic = game.getGameMechanic

  def getGameState: GameStateEnum = game.getCurrentGameState

  def doStep(command: Command[gameInterface]): Unit = {
    game = undoManager.doStep(game, command)
    notifyObservers(Event.Set)
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
  
  override def toString: String = game.getGameField.getMuehleMatrix.toString

  def getMuehleMatrix: Vector[Vector[Stone]] = {
    game.getGameField.getMuehleMatrix
  }

  def setDecorator(enabled: Boolean): Unit = {
    game.setDecorator(enabled)
  }

  def getMesh: meshComponentInterface = {
    game.getMesh
  }
  
  def getCurrentPlayer: Stone = {
    game.getPlayer
  }
  
  def getOpponentPlayer: Stone = {
    game.getOpponentPlayer
  }
}

