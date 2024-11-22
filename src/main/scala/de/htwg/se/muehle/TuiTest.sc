val eol = sys.props("line.separator")
//def bar(cellWidth: Int = 3, cellNum: Int = 3) = (("+" + "-" * cellWidth) * cellNum) + "+" + eol
//def cells(cellWidth: Int = 3, cellNum: Int = 3) = ("|" + " " * cellWidth) * cellNum + "|" + eol
//def mesh(cellWidth: Int = 3, cellNum: Int = 3) = (bar(cellWidth, cellNum) + cells(cellWidth, cellNum)) * cellNum + bar(cellWidth, cellNum)

//println(mesh())

if(true) {
  println("hi")
}
var string: String = ""
val spacer: String = "│" + " "
for (i <- 0 until 2) {
  string += spacer
  println(i)
}
println(string)

val lineWidth: Int = 7
val lineNum: Int = 2
val boolean: Boolean = true

def bar(lineWidth: Int, lineNum: Int, eolB: Boolean)          = ("0" + "─" * lineWidth) * lineNum + "0" + (if(eolB) eol else "")
def spacer(lineWidth: Int, lineNum: Int, eolB: Boolean)       = ("│" + " " * lineWidth) * lineNum + "│" + (if(eolB) eol else "")
def middelBar(lineWidth: Int, lineNum: Int)                   = bar(2, lineNum, false) + " " * 3 + bar(2, lineNum, true)
def tinyBarFront(lineNum: Int)                                = ("│" + " " * 2) * lineNum
def tinyBarBack(lineNum: Int, eolB: Boolean)                  = (" " * 2 + "│") * lineNum + (if(eolB) eol else "")
def mesh(lineWidth: Int, lineNum: Int)                        = bar(lineWidth, lineNum, true) +
                                                                spacer(lineWidth, lineNum, true) * 2 +
                                                                tinyBarFront(lineNum - 1) + bar(lineWidth - 3, lineNum, false) + tinyBarBack(lineNum - 1, true) +
                                                                (tinyBarFront(lineNum - 1) + spacer(lineWidth - 3, lineNum, false) + tinyBarBack(lineNum - 1, true)) * 2 +
                                                                tinyBarFront(lineNum) + bar(lineWidth-6, lineNum, false) + tinyBarBack(lineNum, true) +
                                                                tinyBarFront(lineNum) + "│" + " " * 3 + "│"  + tinyBarBack(lineNum, true) +
                                                                middelBar(lineWidth, lineNum) +
                                                                tinyBarFront(lineNum) + "│" + " " * 3 + "│" + tinyBarBack(lineNum, true) +
                                                                tinyBarFront(lineNum) + bar(lineWidth-6, lineNum, false) + tinyBarBack(lineNum, true) +
                                                                (tinyBarFront(lineNum - 1) + spacer(lineWidth - 3, lineNum, false) + tinyBarBack(lineNum - 1, true)) * 2 +
                                                                tinyBarFront(lineNum - 1) + bar(lineWidth - 3, lineNum, false) + tinyBarBack(lineNum - 1, true) +
                                                                spacer(lineWidth, lineNum, true) * 2 +
                                                                bar(lineWidth, lineNum, false)


println(mesh(lineWidth, lineNum))