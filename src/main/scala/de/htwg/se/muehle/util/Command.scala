package de.htwg.se.muehle.util


trait Command[T] {
  def doStep(t: T): T
  def undoStep(t: T): T
}

class UndoManager[T] {
  private var undoStack: List[Command[T]] = Nil
  private var redoStack: List[Command[T]] = Nil

  def doStep(t: T, command: Command[T]): T = {
    undoStack = command :: undoStack
    command.doStep(t)
  }
  def undoStep(t: T): T =
    undoStack match {
      case Nil => t
      case head :: stack => 
        val result = head.undoStep(t)
        undoStack = stack
        redoStack = head :: redoStack
        result
    }
}