package de.htwg.se.muehle.controller

import de.htwg.se.muehle.model.{Game, PlayerState}
import de.htwg.se.muehle.util.Command

class SetCommand(game: Game, newRing: Int, newPosOnRing: Int) extends Command[Game] {
  private var memento: Game = game
  override def doStep(game: Game): Game = {
    memento = game.copy()
    game.setStoneGame(newRing, newPosOnRing)
  }

  override def undoStep(game: Game): Game = {
    memento
  }
}

class MoveCommand(game: Game, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int) extends Command[Game] {
  private var memento: Game = game
  
  override def doStep(game: Game): Game = {
    memento = game.copy()
    game.moveStoneGame(oldRing, oldPosOnRing, newRing, newPosOnRing)
  }

  override def undoStep(game: Game): Game = {
    memento
  }
}

class JumpCommand(game: Game, oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int) extends Command[Game] {
  private var memento: Game = game

  override def doStep(game: Game): Game = {
    memento = game.copy()
    game.jumpStoneGame(oldRing, oldPosOnRing, newRing, newPosOnRing)
  }

  override def undoStep(game: Game): Game = {
    memento
  }
}

class RemoveCommand(game: Game, newRing: Int, newPosOnRing: Int) extends Command[Game] {
  private var memento: Game = game

  override def doStep(game: Game): Game = {
    memento = game.copy()
    game.removeStoneGame(newRing, newPosOnRing)
  }

  override def undoStep(game: Game): Game = {
    memento
  }
}
