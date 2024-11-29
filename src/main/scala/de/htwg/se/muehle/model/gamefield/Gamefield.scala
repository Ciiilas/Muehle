package de.htwg.se.muehle.model.gamefield

import de.htwg.se.muehle.model.gamefield.Stone


case class Gamefield(val muehleMatrix: Vector[Vector[Stone]]) {
  def this(n: Int, m: Int, default: Stone) = this(Vector.fill(n)(Vector.fill(m)(Stone.Empty)))

  def this() = this(3, 8, Stone.Empty)

  def this(boardString: String, rows: Int, cols: Int) = { // val board = new Board("WBEBEWEWE", 2, 5) -> Vector(Vector(White, Black, Empty, Black, Empty), Vector(White, Empty, White, Empty, Empty))
    this(
      boardString
        .grouped(cols)
        .map(row =>
          row.map {
            case 'W' => Stone.White
            case 'B' => Stone.Black
            case 'E' => Stone.Empty
            case _ => throw new IllegalArgumentException("Invalid character in boardString")
          }.toVector
        ).toVector
    )
  }

  // Update method for a specific enumMatrix value at (row, col)
  def withEnumAt(row: Int, col: Int, newEnum: Stone): Gamefield = {
    val updatedMatrix =
      if row >= 0 && row < muehleMatrix.length && col >= 0 && col < muehleMatrix(row).length then
        muehleMatrix.updated(row, muehleMatrix(row).updated(col, newEnum))
      else muehleMatrix // No change if out of bounds

    this.copy(muehleMatrix = updatedMatrix)
  }

  override def toString: String = mesh()
  
  val eol: String = sys.props("line.separator")
  var numberRings: Int = muehleMatrix.size //wird heruntergezählt
  var spacer: Int = 1 // bleibt gleich
  var leftSpacer: Int = 0 //wird hochgezählt
  var rightSpacer: Int = 0 //wird hochgezählt
  var leftSpacerInBetween: Int = 0 //wird hochgezählt
  var rightSpacerInBetween: Int = 0 //wird hochgezählt

  def mesh(): String = {
    var meshString: String = ""
    for (index <- muehleMatrix.indices.reverse) {
      meshString += spacerLeftTop(leftSpacer) // +1
      meshString += barSegmentLeft(index) // "E─────"
      meshString += muehleMatrix(index)(1).toString // "E"
      meshString += barSegmentRight(index) // "─────E"
      meshString += spacerRightTop(rightSpacer) // +1
      meshString += eol // eol
      if (index >= 1) {
        meshString += spacerLeftTop(leftSpacer) // "│ "           // +1
        //meshString += barSegmentLeftInBetween(leftSpacerInBetween)  //
        meshString += " " * (index * 2) + "│"
        meshString += " " * (index * 2)
        //meshString += barSegmentRightInBetween(leftSpacerInBetween) //
        meshString += spacerRightTop(rightSpacer) // " │"
        meshString += eol
        this.leftSpacer -= 1
        this.rightSpacer -= 1
      }
    }
    meshString += spacerLeftTop(leftSpacer) // "│ "
    meshString += " "
    meshString += spacerRightTop(rightSpacer) // " │"
    meshString += eol //│ │ │   │ │ │
    // ----- MiddleBar ------
    for (index <- muehleMatrix.indices.reverse) {
      meshString += muehleMatrix(index)(7)
      if (index >= 1) {
        meshString += "─"
      }
    }
    meshString += " " * 3
    for (index <- muehleMatrix.indices) {
      meshString += muehleMatrix(index)(3)
      if (index < muehleMatrix.size - 1) {
        meshString += "─"
      }
    }
    meshString += eol
    // ----- MiddleBar ------
    this.leftSpacer = muehleMatrix.size
    this.rightSpacer = muehleMatrix.size
    meshString += spacerLeftBottom(leftSpacer) // "│ " -1
    meshString += " "
    meshString += spacerRightBottom(rightSpacer) // " │" -1
    meshString += eol //│ │ │   │ │ │


    for (index <- muehleMatrix.indices) {

      meshString += spacerLeftBottom(leftSpacer) // "│ " -1
      meshString += barSegmentLeftBottom(index) // "E─────"
      meshString += muehleMatrix(index)(5).toString // "E"
      meshString += barSegmentRightBottom(index) // "─────E"
      meshString += spacerRightBottom(rightSpacer) // " │" -1
      meshString += eol // eol
      this.leftSpacer += 1
      this.rightSpacer += 1
      if (index < muehleMatrix.size - 1) {
        meshString += spacerLeftBottom(leftSpacer) // "│ "           // -1

        meshString += " " * ((index + 1) * 2) + "│"
        meshString += " " * ((index + 1) * 2)

        meshString += spacerRightBottom(rightSpacer) // " │"         //-1
        meshString += eol
      }
    }
    meshString
  }


  private def barSegmentLeft(times: Int): String = {
    muehleMatrix(times)(0).toString + "─" * (times * 2 + 1)
  }

  private def barSegmentRight(times: Int): String = {
    "─" * (times * 2 + 1) + muehleMatrix(times)(2).toString
  }

  private def spacerLeftTop(times: Int): String = {
    val string: String = ("│" + " ") * times
    this.leftSpacer += 1
    string
  }

  private def spacerRightTop(times: Int): String = {
    val string: String = (" " + "│") * times
    this.rightSpacer += 1
    string
  }

  private def spacerLeftBottom(times: Int): String = {
    val string: String = ("│" + " ") * times
    this.leftSpacer -= 1
    string
  }

  private def spacerRightBottom(times: Int): String = {
    val string: String = (" " + "│") * times
    this.rightSpacer -= 1
    string
  }

  private def barSegmentLeftBottom(times: Int): String = {
    muehleMatrix(times)(6).toString + "─" * (times * 2 + 1)
  }

  private def barSegmentRightBottom(times: Int): String = {
    "─" * (times * 2 + 1) + muehleMatrix(times)(4).toString
  }
}