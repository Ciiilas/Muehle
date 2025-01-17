enum Stone(stringRepresentation: String):
  override def toString = stringRepresentation
  case White extends Stone("W")
  case Black extends Stone("B")
  case Empty extends Stone("0")


var muehleMatrix: Vector[Vector[Stone]] = Vector(
  Vector(Stone.Empty, Stone.Empty, Stone.Empty, Stone.Black, Stone.Empty, Stone.Empty, Stone.Empty, Stone.Empty), //Ring 0
  Vector(Stone.White, Stone.White, Stone.Empty, Stone.Empty, Stone.Empty, Stone.Empty, Stone.Empty, Stone.Empty), //Ring 1
  Vector(Stone.Empty, Stone.Empty, Stone.Empty, Stone.Empty, Stone.Empty, Stone.Empty, Stone.Empty, Stone.Empty)) //Ring 2

println(muehleMatrix)
def checkForMuehle(muehleMatrix: Vector[Vector[Stone]], newRing: Int, newPosOnRing: Int, stone: Stone): String = {
  //check Muehle from Ring Center
  if (newPosOnRing % 2 == 1) { // false
    //checking On Ring
    //newPosOnRing == (oldPosOnRing + 1) % 8 || newPosOnRing == (oldPosOnRing - 1 + 8) % 8
    if (muehleMatrix(newRing)((newPosOnRing - 1 + 8) % 8) == stone && muehleMatrix(newRing)((newPosOnRing + 1) % 8) == stone) {
      return "true1"
    }
    //checking other Rings
    if (muehleMatrix((newRing - 1 + 3) % 3) == stone && muehleMatrix((newRing + 1) % 3) == stone) {
      return "true2"
    }
    return "false1"
  }
  //check Muehle from Ring Corner
  if (newPosOnRing % 2 == 0) {
    //Corner right
    if (muehleMatrix(newRing)((newPosOnRing - 1 + 8) % 8) == stone && muehleMatrix(newRing)((newPosOnRing - 2 + 8) % 8) == stone
      || muehleMatrix(newRing)((newPosOnRing + 1) % 8) == stone && muehleMatrix(newRing)((newPosOnRing + 2) % 8) == stone) {
      return "true3"
    }
    return "false3"
  }
  "false2"
}
checkForMuehle(muehleMatrix, 1, 2, Stone.Black)


