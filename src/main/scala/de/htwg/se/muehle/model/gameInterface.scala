package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.gameComponent.Game
import de.htwg.se.muehle.model.gameFieldComponent.gameFieldInterface
import de.htwg.se.muehle.model.gameFieldComponent.gamefield.{Gamefield, Stone, meshComponentInterface}
import de.htwg.se.muehle.model.mechanicComponent.mechanic.Mechanic
import de.htwg.se.muehle.model.mechanicComponent.mechanicInterface

import de.htwg.se.muehle.model.gameComponent.Game
import de.htwg.se.muehle.model.gameFieldComponent.gameFieldInterface
import de.htwg.se.muehle.model.gameFieldComponent.gamefield.{Gamefield, Stone, meshComponentInterface}
import de.htwg.se.muehle.model.mechanicComponent.mechanic.Mechanic
import de.htwg.se.muehle.model.mechanicComponent.mechanicInterface

trait gameInterface {
  def getMechanic: mechanicInterface

  def getGameField: gameFieldInterface

  def getMessage: Option[String]

  def getPlayer: Stone

  def getCurrentGameState: GameStateEnum
  
  
  
  
  def setStoneGame(ring: Int, posOnRing: Int): Game
  def moveStoneGame(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game
  def jumpStoneGame(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game
  def removeStoneGame(ring: Int, posOnRing: Int): Game
  def isMuehle(newField: gameFieldInterface, ring: Int, posOnRing: Int): Boolean
  def setDecorator(enabled: Boolean): Unit
  def getMesh: meshComponentInterface
  def checkAndSetGameState(): GameStateEnum
  def getOpponentPlayer: Stone
  
  
  
  
  
}