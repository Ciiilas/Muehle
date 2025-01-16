package de.htwg.se.muehle.model.FileIOComponent

import de.htwg.se.muehle.model.gameInterface
import de.htwg.se.muehle.model.gameFieldComponent
import de.htwg.se.muehle.model.mechanicComponent

import play.api.libs.json.*
import java.io.*


class FileIOJSON extends FileIOComponent {
  
  def load(): gameInterface = {
    
    return ???
  }

  def save(game: gameInterface): Unit = {
    val json: JsValue = Json.obj(
      "number_of_rings" -> game.getGameField.getMuehleMatrix.size
    )
    val pw = new PrintWriter(new File("SaveGame.txt"))
    pw.write(json.toString)
    pw.close()
  }
}
