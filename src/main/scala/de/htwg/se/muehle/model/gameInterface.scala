package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.gameFieldComponent.gameFieldInterface
import de.htwg.se.muehle.model.gameFieldComponent.gamefield.{Gamefield, Stone, meshComponentInterface}
import de.htwg.se.muehle.model.mechanicComponent.mechanic.Mechanic
import de.htwg.se.muehle.model.mechanicComponent.mechanicInterface

trait gameInterface(mech: mechanicInterface, field: gameFieldInterface, message: Option[String], player: Stone, currentGameState: GameStateEnum) {
  //Getter
  def getMechanic: mechanicInterface = mech
  def getGameField: gameFieldInterface = field
  def getMessage: Option[String] = message
  def getPlayer: Stone = player
  def getCurrentGameState: GameStateEnum = currentGameState
  
  //def getGameMechanic: Mechanic
  def setStoneGame(ring: Int, posOnRing: Int): Game
  def moveStoneGame(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game
  def jumpStoneGame(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game
  def removeStoneGame(ring: Int, posOnRing: Int): Game
  def isMuehle(ring: Int, posOnRing: Int): Boolean
  //def getGameField: Gamefield
  def setDecorator(enabled: Boolean): Unit
  def getMesh: meshComponentInterface
  def checkAndSetGameState(): GameStateEnum
  def getOpponentPlayer: Stone
}