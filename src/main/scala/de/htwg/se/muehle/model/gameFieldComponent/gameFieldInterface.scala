package de.htwg.se.muehle.model.gameFieldComponent

import de.htwg.se.muehle.model.gameFieldComponent.gamefield.Stone

trait gameFieldInterface {
  //Getter
  def getMuehleMatrix: Vector[Vector[Stone]]
  
  def withEnumAt(row: Int, col: Int, newEnum: Stone): gameFieldInterface
  def mesh(): String
  def barSegmentLeft(times: Int): String
  def barSegmentRight(times: Int): String
  def spacerLeftTop(times: Int): String
  def spacerRightTop(times: Int): String
  def spacerLeftBottom(times: Int): String
  def spacerRightBottom(times: Int): String
  def barSegmentLeftBottom(times: Int): String
  def barSegmentRightBottom(times: Int): String
}