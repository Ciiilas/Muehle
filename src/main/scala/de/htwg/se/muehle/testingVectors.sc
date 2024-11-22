
enum Stone:
  case S, W, Empty

val vector = Vector("A", "B", "C", "D")
vector(0)

val string = "." * 0
string
val n = 3 // Beispielsweise 5 Zeilen
val rows: Vector[Vector[Stone]] = Vector.fill(n)(Vector.fill(8)(Stone.W))
print(rows.map(_.toString).map(" " + _ + " ").mkString(sys.props("line.separator")))

println(rows(0)(1).toString)

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