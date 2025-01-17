package de.htwg.se.muehle
package aview

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.muehle.Muehle
import de.htwg.se.muehle.aview.Tui
import de.htwg.se.muehle.controller.controllerComponent.Controller
import de.htwg.se.muehle.model.FileIOComponent.FileIOComponent
import de.htwg.se.muehle.model.gameComponent.Game

class TuiSpec extends AnyWordSpec {
  "Tui" should {
    
    val tui = new Tui()

    "get coordinates from input string" in {
      tui.getCoords("1,2;3,4") should be(Array(Array(1,2), Array(3, 4)))
      tui.getCoords("1,2") should be(Array(Array(1,2)))
    }



  }

}
