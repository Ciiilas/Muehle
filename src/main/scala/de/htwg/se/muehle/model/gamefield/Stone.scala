package de.htwg.se.muehle.model.gamefield

enum Stone(stringRepresentation: String):
  override def toString = stringRepresentation
  case White extends Stone("W")
  case Black extends Stone("B")
  case Empty extends Stone("0")
