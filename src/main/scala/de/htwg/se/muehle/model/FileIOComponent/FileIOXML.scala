package de.htwg.se.muehle.model.FileIOComponent

import de.htwg.se.muehle.model.gameInterface
import de.htwg.se.muehle.model.gameFieldComponent
import de.htwg.se.muehle.model.mechanicComponent

import java.io.*


class FileIOXML extends FileIOComponent {

  def load(): gameInterface = {

    return ???
  }

  def save(game: gameInterface): Unit = {
    val data = <game><gamefield><number_of_rings>{game.getGameField.getMuehleMatrix.size}</number_of_rings></gamefield></game>
    
    val pw = new PrintWriter(new File("SaveGame.xml"))
    pw.write(data.toString)
    pw.close()
    
  }
}
