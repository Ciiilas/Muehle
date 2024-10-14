package de.htwg.se.muehle
package model

class Mesh {

val eol: String = sys.props("line.separator")

def bar(lineWidth: Int, lineNum: Int, eolB: Boolean): String = ("0" + "─" * lineWidth) * lineNum + "0" + (if (eolB) eol else "")

def spacer(lineWidth: Int, lineNum: Int, eolB: Boolean): String = ("│" + " " * lineWidth) * lineNum + "│" + (if (eolB) eol else "")

def middelBar(lineNum: Int): String = bar(2, lineNum, false) + " " * 3 + bar(2, lineNum, true)

def tinyBarFront(lineNum: Int): String = ("│" + " " * 2) * lineNum

def tinyBarBack(lineNum: Int, eolB: Boolean): String = (" " * 2 + "│") * lineNum + (if (eolB) eol else "")

def mesh(lineWidth: Int, lineNum: Int): String = bar(lineWidth, lineNum, true) +
    spacer(lineWidth, lineNum, true) * 2 +
    tinyBarFront(lineNum - 1) + bar(lineWidth - 3, lineNum, false) + tinyBarBack(lineNum - 1, true) +
    (tinyBarFront(lineNum - 1) + spacer(lineWidth - 3, lineNum, false) + tinyBarBack(lineNum - 1, true)) * 2 +
    tinyBarFront(lineNum) + bar(lineWidth - 6, lineNum, false) + tinyBarBack(lineNum, true) +
    tinyBarFront(lineNum) + "│" + " " * 3 + "│" + tinyBarBack(lineNum, true) +
    middelBar(lineNum) +
    tinyBarFront(lineNum) + "│" + " " * 3 + "│" + tinyBarBack(lineNum, true) +
    tinyBarFront(lineNum) + bar(lineWidth - 6, lineNum, false) + tinyBarBack(lineNum, true) +
    (tinyBarFront(lineNum - 1) + spacer(lineWidth - 3, lineNum, false) + tinyBarBack(lineNum - 1, true)) * 2 +
    tinyBarFront(lineNum - 1) + bar(lineWidth - 3, lineNum, false) + tinyBarBack(lineNum - 1, true) +
    spacer(lineWidth, lineNum, true) * 2 +
    bar(lineWidth, lineNum, false)

}
