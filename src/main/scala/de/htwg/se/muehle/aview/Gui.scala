package de.htwg.se.muehle
package aview

import scala.swing._
import scala.swing.event._
import de.htwg.se.muehle.controller.Controller
import util.Observer
import de.htwg.se.muehle.util.Event
import java.awt.{Graphics2D, Color, BasicStroke}
import java.awt.event.{MouseAdapter, MouseEvent}

class Gui(controller: Controller) extends MainFrame with Observer {
  title = "Mühle"
  preferredSize = new Dimension(800, 600)
  controller.add(this)
  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Undo") {
        controller.undo()
      })
      contents += new MenuItem(Action("Exit") {
        sys.exit(0)
      })
    }
  }
  pack()
  centerOnScreen()
  open()

  val boardPanel = new BoardPanel(controller)
  contents = new BorderPanel {
    layout(boardPanel) = BorderPanel.Position.Center
  }

  override def update(e: Event): Unit = {
    controller.setDecorator(false) // Dekorator ausschalten
    boardPanel.repaint()
  }
}

class BoardPanel(controller: Controller) extends Panel {
  preferredSize = new Dimension(800, 800)
  background = Color.white

  listenTo(mouse.clicks)
  reactions += {
    case e: MouseClicked =>
      val gridPos = getGridPosition(e.point)
      gridPos.foreach { case (i, j) => controller.setStone(i, j) }
  }

  override def paintComponent(g: Graphics2D): Unit = {
    super.paintComponent(g)
    drawBoard(g)
  }

  def drawBoard(g: Graphics2D): Unit = {
    g.setColor(Color.black)
    g.setStroke(new BasicStroke(2))

    // Draw the lines of the board
    val positions = List(
      (2, 0), (2, 1), (2, 2), (1, 0), (1, 1), (1, 2), (0, 0), (0, 1), (0, 2),
      (2, 7), (1, 7), (0, 7), (0, 3), (1, 3), (2, 3), (0, 6), (0, 5), (0, 4),
      (1, 6), (1, 5), (1, 4), (2, 6), (2, 5), (2, 4)
    )

    val coords = positions.map { case (i, j) => (i * 100 + 50, j * 100 + 50) }

    // Draw the lines connecting the positions
    for ((x1, y1) <- coords; (x2, y2) <- coords if (x1 == x2 || y1 == y2) && (Math.abs(x1 - x2) <= 100 && Math.abs(y1 - y2) <= 100)) {
      g.drawLine(x1, y1, x2, y2)
    }

    // Draw the circles for the positions
    for ((x, y) <- coords) {
      g.drawOval(x - 10, y - 10, 20, 20)
    }

    // Draw the stones based on the game state
    val mesh = controller.getMesh.render()
    val rows = mesh.split("\n")
    for ((i, j) <- positions) {
      val char = rows(i).charAt(j)
      if (char == 'B' || char == 'W') {
        g.setColor(if (char == 'B') Color.black else Color.white)
        g.fillOval(i * 100 + 40, j * 100 + 40, 20, 20)
      }
    }
  }

  def getGridPosition(point: Point): Option[(Int, Int)] = {
    val cellSize = 100
    val col = (point.x - 50) / cellSize
    val row = (point.y - 50) / cellSize
    val mappedRow = 2 - row // Adjust the row to map (0,0) to (2,0)
    if (col >= 0 && col < 8 && mappedRow >= 0 && mappedRow < 3) {
      Some((mappedRow, col))
    } else {
      None
    }
  }
}