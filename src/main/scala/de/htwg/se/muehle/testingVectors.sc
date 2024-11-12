
enum Stone:
  case S, W, Empty

val n = 3 // Beispielsweise 5 Zeilen
val rows: Vector[Vector[Stone]] = Vector.fill(n)(Vector.fill(8)(Stone.W))
print(rows.map(_.toString).map(" " + _ + " ").mkString(sys.props("line.separator")))

case class Mechanic(turns: Int) {
  def this() = this(0)

}

var test = new Mechanic()
var test2 = new Mechanic(5)
test.turns
test2.turns

def split(input: String): Array[String] = {
  input.split(" ")
}

val splitinput: Array[String] = split("move 3,5;6,11")
print(splitinput(0))