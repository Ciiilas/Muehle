package de.htwg.se.muehle.model.gamefield

enum Stone(stringRepresentation: String):
  override def toString = stringRepresentation
  case White extends Stone("White")
  case Black extends Stone("Black")
  case None extends Stone("None")
