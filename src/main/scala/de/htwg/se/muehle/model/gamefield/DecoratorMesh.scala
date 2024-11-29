package de.htwg.se.muehle.model.gamefield

import de.htwg.se.muehle.model.Game

case class DecoratorMesh() {
  val field: Gamefield = new Gamefield()
  def meshMesh(): String = {
    //erste zeile deco
    field.meshMesh()
    //zweite zeile deco
  }
  
  
}
