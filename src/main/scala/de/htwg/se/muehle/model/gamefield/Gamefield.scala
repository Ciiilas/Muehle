package de.htwg.se.muehle.model.gamefield

import de.htwg.se.muehle.model.gamefield.Stone


case class Gamefield(val muehleMatrix: Vector[Vector[Stone]]) {
  def this(n: Int, m: Int, default: Stone) = this(Vector.fill(n)(Vector.fill(m)(Stone.Empty)))
  def this() = this(3, 8, Stone.Empty)

  // Update method for a specific enumMatrix value at (row, col)
  def withEnumAt(row: Int, col: Int, newEnum: Stone): Gamefield = {
    val updatedMatrix =
      if row >= 0 && row < muehleMatrix.length && col >= 0 && col < muehleMatrix(row).length then
        muehleMatrix.updated(row, muehleMatrix(row).updated(col, newEnum))
      else muehleMatrix // No change if out of bounds

    this.copy(muehleMatrix = updatedMatrix)
  }

  override def toString: String = {
    val numberOfRings = muehleMatrix.size
    val numberOfPos = muehleMatrix.map(row => row.size)
    val eol = "\n"
    //val fieldAsString: String =

    return ""

  }

  val eol: String = sys.props("line.separator")

  def bar(lineWidth: Int, lineNum: Int, eolB: Boolean): String = ("0" + "─" * lineWidth) * lineNum + "0" + (if (eolB) eol else "")

  def spacer(lineWidth: Int, lineNum: Int, eolB: Boolean): String = ("│" + " " * lineWidth) * lineNum + "│" + (if (eolB) eol else "")

  def middelBar(lineNum: Int): String = bar(2, lineNum, false) + " " * 3 + bar(2, lineNum, true)

  def tinyBarFront(lineNum: Int): String = ("│" + " " * 2) * lineNum

  def tinyBarBack(lineNum: Int, eolB: Boolean): String = (" " * 2 + "│") * lineNum + (if (eolB) eol else "")


  def bar(outermostRing: Int,  thisRing: Int): String = {

    return ""
  }

  def barwithnumbers(outermostRing: Int, thisRing: Int): String = {
    val lenght: Int = 5 + outermostRing * 4
    var bar: Array[Char] = Array.fill(lenght)(' ')
    val numberOfOutsideRings = outermostRing - thisRing
    //val numberOfNot
    

    return ""
  }
  
  def barmiddle(outermostRing: Int): String = {
    
    
    return ""
  }



}




