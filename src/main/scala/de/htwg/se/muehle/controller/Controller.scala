package de.htwg.se.muehle
package controller

import util.Observable
import de.htwg.se.muehle.model.gamefield.meshComponentInterface
import de.htwg.se.muehle.model.Game
import de.htwg.se.muehle.model.gamefield.Stone
import de.htwg.se.muehle.model.gamefield.Stone.White


case class Controller(var game: Game) extends Observable {
  def this() = this(new Game())
  

  //-----------------------------------------------------
  //mechanic
  //-----------------------------------------------------

  def setStone(newRing: Int, newPosOnRing: Int): Unit = {
    game = game.setStone(newRing, newPosOnRing)
    notifyObservers()
  }


  def moveStone(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Unit = {
    game = game.moveStone(oldRing, oldPosOnRing, newRing, newPosOnRing)
    notifyObservers()
  }

  def jumpStone(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Unit = {
    game = game.jumpStone(oldRing, oldPosOnRing, newRing, newPosOnRing)
    
    notifyObservers()
  }

  def removeStone(newRing: Int, newPosOnRing: Int): Unit = {
    game = game.removeStone(newRing, newPosOnRing)
    notifyObservers()
  }

  //-----------------------------------------------------
  //field
  //-----------------------------------------------------

  override def toString: String = game.field.muehleMatrix.toString

  def setDecorator(enabled: Boolean): Unit = {
    game.setDecorator(enabled)
  }

  def getMesh: meshComponentInterface = {
    game.getMesh
  }
  
  
}

