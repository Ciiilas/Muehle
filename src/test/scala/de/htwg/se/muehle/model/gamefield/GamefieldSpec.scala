package de.htwg.se.muehle.model.gamefield

import de.htwg.se.muehle.model.gameFieldComponent.gamefield.{Gamefield, Stone}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class GamefieldSpec extends AnyWordSpec {
  "mesh should return correct string representation for default gamefield" in {
    val gamefield = new Gamefield()
    val expectedMesh = "0─────0─────0\n" +
      "│     │     │\n" +
      "│ 0───0───0 │\n" +
      "│ │   │   │ │\n" +
      "│ │ 0─0─0 │ │\n" +
      "│ │ │   │ │ │\n" +
      "0─0─0   0─0─0\n" +
      "│ │ │   │ │ │\n" +
      "│ │ 0─0─0 │ │\n" +
      "│ │   │   │ │\n" +
      "│ 0───0───0 │\n" +
      "│     │     │\n" +
      "0─────0─────0\n"
    gamefield.mesh() should be(expectedMesh)
  }

  "mesh should return correct string representation for custom gamefield" in {
    val gamefield = new Gamefield("WBEBEWEWE", 2, 5)
    val expectedMesh = "W─────B─────E\n" +
      "│     │     │\n" +
      "│ E───W───E │\n" +
      "│ │   │   │ │\n" +
      "│ │ E─E─E │ │\n" +
      "│ │ │   │ │ │\n" +
      "0─0─0   0─0─0\n" +
      "│ │ │   │ │ │\n" +
      "│ │ 0─0─0 │ │\n" +
      "│ │   │   │ │\n" +
      "│ 0───0───0 │\n" +
      "│     │     │\n" +
      "0─────0─────0\n"
    gamefield.mesh() should be(expectedMesh)
  }

  "mesh should handle empty gamefield" in {
    val gamefield = new Gamefield(0, 0, Stone.Empty)
    gamefield.mesh() should be("")
  }

  "mesh should handle single row gamefield" in {
    val gamefield = new Gamefield(1, 3, Stone.White)
    val expectedMesh = "W─────W─────W\n" +
      "│     │     │\n" +
      "│ 0───0───0 │\n" +
      "│ │   │   │ │\n" +
      "│ │ 0─0─0 │ │\n" +
      "│ │ │   │ │ │\n" +
      "0─0─0   0─0─0\n" +
      "│ │ │   │ │ │\n" +
      "│ │ 0─0─0 │ │\n" +
      "│ │   │   │ │\n" +
      "│ 0───0───0 │\n" +
      "│     │     │\n" +
      "0─────0─────0\n"
    gamefield.mesh() should be(expectedMesh)
  }

  "mesh should handle single column gamefield" in {
    val gamefield = new Gamefield(3, 1, Stone.Black)
    val expectedMesh = "B─────B─────B\n" +
      "│     │     │\n" +
      "│ 0───0───0 │\n" +
      "│ │   │   │ │\n" +
      "│ │ 0─0─0 │ │\n" +
      "│ │ │   │ │ │\n" +
      "0─0─0   0─0─0\n" +
      "│ │ │   │ │ │\n" +
      "│ │ 0─0─0 │ │\n" +
      "│ │   │   │ │\n" +
      "│ 0───0───0 │\n" +
      "│     │     │\n" +
      "0─────0─────0\n"
    gamefield.mesh() should be(expectedMesh)
  }

  "should change muehlematrix" in {
    val field = new Gamefield("EEEEEEEE", 1, 8)
    field.withEnumAt(0, 0, Stone.White) should be(new Gamefield("WEEEEEEE", 1, 8))
  }
  "should made a bar" in {
    val field = new Gamefield()
    field.barSegmentLeft(2) should be("--")
  }
  "should made a bar" in {
    val field = new Gamefield()
    field.barSegmentRight(2) should be("--")
  }
}