package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.gameComponent.Game
import de.htwg.se.muehle.model.gameFieldComponent.gamefield.Gamefield
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class GameSpec extends AnyWordSpec {
  "Game" should {
    "set a stone" in {
      val game = new Game(new Gamefield("EEEEEEEE", 1, 8))
      game.setStoneGame(0,0).field should be(new Game(new Gamefield("WEEEEEEE", 1, 8)).field)
    }
    "return the mechanic" in {
      val game = new Game()
      game.getGameMechanic should be(game.mech)
    }
/*    "move a stone" in {
      val game = new Game(new Gamefield("EEEWEEEE", 1, 8))
      game.moveStoneGame(0, 3, 0, 4) should be(new Game(new Gamefield("EEEEWEEE", 1, 8)))
    }*/

  }


}
