package de.htwg.se.muehle.controller

import de.htwg.se.muehle.util.Observer
import de.htwg.se.muehle.model.{GameStateEnum, gameInterface}
import de.htwg.se.muehle.model.gameFieldComponent.gamefield.Stone

trait controllerInterface(game: gameInterface) {
  def getGame: gameInterface = game
  
  def add(observer: Observer): Unit
  def remove(observer: Observer): Unit
  def getMuehleMatrix: Vector[Vector[Stone]]
  def getGameState: GameStateEnum 
  def setStone(squareIndex: Int, pointIndex: Int): Unit
  def moveStone(fromSquareIndex: Int, fromPointIndex: Int, toSquareIndex: Int, toPointIndex: Int): Unit
  def jumpStone(fromSquareIndex: Int, fromPointIndex: Int, toSquareIndex: Int, toPointIndex: Int): Unit
  def removeStone(squareIndex: Int, pointIndex: Int): Unit
  def undo(): Unit
}