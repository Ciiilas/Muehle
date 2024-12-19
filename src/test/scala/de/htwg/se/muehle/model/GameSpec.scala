package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.gamefield.Gamefield
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class GameSpec extends AnyWordSpec {
  "Game" should {

    "set a stone" in {
      var game = new Game(new Gamefield("EEEEEEEE", 1, 8))
      game.setStoneGame(0,0) should be(new Game(new Gamefield("WEEEEEEE", 1, 8)))

    }

  }


}
