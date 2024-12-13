package de.htwg.se.muehle
package aview

import scala.swing._
import scala.swing.event._
import de.htwg.se.muehle.controller.Controller
import util.Observer
import de.htwg.se.muehle.util.Event

class Gui(controller: Controller) extends MainFrame with Observer {
  title = "Mühle"
  preferredSize = new Dimension(800, 600)
  controller.add(this)

  val gridSize = 7 // Assuming a 7x7 grid for the game board
  val buttons = Array.ofDim[Button](gridSize, gridSize)

  val boardPanel = new GridPanel(gridSize, gridSize) {
    for (i <- 0 until gridSize; j <- 0 until gridSize) {
      val button = new Button {
        reactions += {
          case ButtonClicked(_) => controller.setStone(i, j)
        }
      }
      buttons(i)(j) = button
      contents += button
    }
  }

  contents = new BorderPanel {
    layout(boardPanel) = BorderPanel.Position.Center
  }

  override def update(e: Event): Unit = {
    val mesh = controller.getMesh.render()
    updateBoard(mesh)
  }

  def updateBoard(mesh: String): Unit = {
    val rows = mesh.split("\n")
    for (i <- 0 until gridSize; j <- 0 until gridSize) {
      if (rows(i).charAt(j) == '0') {
        buttons(i)(j).text = ""
        buttons(i)(j).enabled = true
      } else {
        buttons(i)(j).text = rows(i).charAt(j).toString
        buttons(i)(j).enabled = false
      }
    }
  }
}