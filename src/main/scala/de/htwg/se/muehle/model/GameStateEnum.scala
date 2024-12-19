package de.htwg.se.muehle.model

enum GameStateEnum(stringRepresentation: String) {
  override def toString: String = stringRepresentation
  case SET_STONE extends GameStateEnum("Stein setzen")
  case MOVE_STONE extends GameStateEnum("Stein bewegen")
  case JUMP_STONE extends GameStateEnum("Stein springen")
  case REMOVE_STONE extends GameStateEnum("Stein entfernen")
  case GAME_OVER extends GameStateEnum("Spiel beendet")
}