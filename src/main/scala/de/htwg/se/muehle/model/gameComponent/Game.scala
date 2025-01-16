package de.htwg.se.muehle.model.gameComponent

import PlayerState.currentStone
import com.google.inject.Inject
import de.htwg.se.muehle.model.gameFieldComponent.gamefield.*
import de.htwg.se.muehle.model.gameFieldComponent.gameFieldInterface
import de.htwg.se.muehle.model.mechanicComponent.mechanic.*
import de.htwg.se.muehle.model.mechanicComponent.mechanicInterface
import de.htwg.se.muehle.model.{GameStateEnum, gameInterface}
import de.htwg.se.muehle.model.gameFieldComponent.gameFieldInterface
import de.htwg.se.muehle.model.gameFieldComponent.gamefield.*
import de.htwg.se.muehle.model.mechanicComponent.mechanic.*
import de.htwg.se.muehle.model.mechanicComponent.mechanicInterface
import de.htwg.se.muehle.model.{GameStateEnum, gameInterface}


case class Game @Inject() (
                            mech: mechanicInterface, 
                            field: gameFieldInterface, 
                            message: Option[String] = None, 
                            player: Stone = Stone.White, 
                            currentGameState: GameStateEnum = GameStateEnum.SET_STONE
                          ) extends gameInterface {
  def this() = this(Mechanic(), new Gamefield())
  def this(gamefield: gameFieldInterface) = this(Mechanic(), gamefield)
  
  
  override def getMechanic: mechanicInterface = mech

  override def getGameField: gameFieldInterface = field

  override def getMessage: Option[String] = message

  override def getPlayer: Stone = player

  override def getCurrentGameState: GameStateEnum = currentGameState
  
  //-----------------------------------------------------
  //mechanic
  //-----------------------------------------------------
  //def getGameMechanic: mechanicInterface = mech
  
  
  def setStoneGame(ring: Int, posOnRing: Int): Game = {
    val gameState = checkAndSetGameState()
    if (gameState == GameStateEnum.SET_STONE) {
      if (mech.isSetLegal(field, ring, posOnRing)) {
        val (newMech, newField) = mech.setStone(field, ring, posOnRing, PlayerState.stone)
        val currentPlayer: Stone = PlayerState.stone // hilfsvalue um den Spieler temporär zu speichern
        if (!isMuehle(ring, posOnRing)) {
          PlayerState.next()
          val playerStone = PlayerState.stone
          return Game(newMech, newField, Some(currentPlayer.toString + " hat einen Stein gesetzt!"), playerStone, gameState)
        } else {
          return Game(newMech, newField, Some(currentPlayer.toString + " hat eine Mühle! Bitte wählen Sie einen Stein den Sie entfernen möchten!"), currentPlayer, GameStateEnum.REMOVE_STONE)
        }
      }
      return Game(mech, field, Some("Error! Der Stein darf hier nicht Platziert werden!"), player, gameState)
    }
    Game(mech, field, Some("Error! Aktuell darf kein Stein gesetzt werden, versuchen Sie einen anderen Befehl!"), player, gameState)
  }

  def moveStoneGame(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game = {
    val gameState = checkAndSetGameState()
    if (gameState == GameStateEnum.MOVE_STONE) {
      if (mech.isMoveLegal(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)) {
        val newField: gameFieldInterface = mech.moveStone(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)
        val currentPlayer: Stone = PlayerState.stone // hilfsvalue um den Spieler temporär zu speichern
        if (!isMuehle(newRing, newPosOnRing)) {
         
          PlayerState.next()
          val playerStone = PlayerState.stone
          return Game(mech, newField, Some(currentPlayer.toString + " hat einen Stein bewegt!"), playerStone, gameState)
        } else {
          return Game(mech, newField, Some(currentPlayer.toString + " hat eine Mühle! Bitte wählen Sie einen Stein den Sie entfernen möchten!"), currentPlayer, GameStateEnum.REMOVE_STONE)
        }
      }
      return Game(mech, field, Some("Error! Der Stein darf hier nicht bewegt werden!"), player, gameState)
    }
    Game(mech, field, Some("Error! Es darf aktuell kein Stein Bewegt werden!"), player, gameState)
  }

  def jumpStoneGame(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game = {
    val gameState = checkAndSetGameState()
    if (gameState == GameStateEnum.JUMP_STONE) {
      if (mech.isJumpLegal(field.asInstanceOf[Gamefield], oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)) {
        val newField: gameFieldInterface = mech.jumpStone(field.asInstanceOf[Gamefield], oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)
        val currentPlayer: Stone = PlayerState.stone // hilfsvalue um den Spieler temporär zu speichern
        if (!isMuehle(newRing, newPosOnRing)) {
          PlayerState.next()
          return Game(mech, newField, Some(PlayerState.opponent.toString + " ist mit einem Stein gesprungen!"), currentPlayer, gameState)
        } else {
          return Game(mech, newField, Some(PlayerState.player + " hat eine Mühle! Bitte wählen Sie einen Stein den Sie entfernen möchten!"), currentPlayer, GameStateEnum.REMOVE_STONE)
        }
      }
      return Game(mech, field, Some("Error! Mit dem Stein darf nicht gesprungen werden!"), player, gameState)
    }
    Game(mech, field, Some("Error! Es darf aktuell nicht mit dem Stein gesprungen werden!"), player, gameState)
  }

  def removeStoneGame(ring: Int, posOnRing: Int): Game = {
    if (!this.currentGameState.equals(GameStateEnum.REMOVE_STONE)) {
      return Game(mech, field, Some("Error! Es gibt keine Mühle, es darf kein Stein entfernt werden!"), player, currentGameState)
    }
    val currentPlayer: Stone = PlayerState.stone // hilfsvalue um den Spieler temporär zu speichern
    if (mech.isRemoveLegal(field, ring, posOnRing, PlayerState.stone)) {
      val newField: gameFieldInterface = mech.removeStone(field, ring, posOnRing, PlayerState.stone)
      PlayerState.next()
      
      val gameState = checkAndSetGameState()
      return Game(mech, newField, Some(currentPlayer.toString + " hat einen Stein entfernt!"), player, gameState)
    }
    Game(mech, field, Some("Error! Der Stein kann nicht entfernt werden!"), currentPlayer, currentGameState)
  }

  def isMuehle(ring: Int, posOnRing: Int): Boolean = {
    mech.getEvaluateStrategy.checkForMuehle(field, ring, posOnRing, PlayerState.stone)
  }

  
  //-----------------------------------------------------
  //gameField
  //-----------------------------------------------------

  //def getGameField: gameFieldInterface = field

  private var useDecorator: Boolean = false

  def setDecorator(enabled: Boolean): Unit = {
    useDecorator = enabled
  }

  def getMesh: meshComponentInterface = {
    val baseMesh = ConcreteMesh(field.asInstanceOf[Gamefield])
    if (useDecorator) {
      ConcreteDecoratorMesh(baseMesh)
    } else {
      baseMesh
    }
  }



  //-----------------------------------------------------
  //game
  //-----------------------------------------------------

  def checkAndSetGameState(): GameStateEnum = {
    if (mech.getCounterOfSetStone < 18) { // 17, da die abfrage vor dem setzen des steins erfolgt //Note: muss noch überprüft werden
      GameStateEnum.SET_STONE
    } else if (mech.getCounterOfSetStone == 18 && field.getMuehleMatrix.flatten.count((stone:Stone) => stone == PlayerState.stone) > 3) {
      GameStateEnum.MOVE_STONE
    } else if (mech.getCounterOfSetStone == 18 && field.getMuehleMatrix.flatten.count((stone:Stone) => stone == PlayerState.stone) == 3) {
      GameStateEnum.JUMP_STONE
    } else {
      GameStateEnum.GAME_OVER
    }
  }

  def getOpponentPlayer: Stone = PlayerState.opponent
}


object PlayerState:
  private var currentStone: Stone = Stone.White

  def stone: Stone = currentStone
  
  def stone_=(newStone: Stone): Unit = currentStone = newStone
  
  def syncWithGame(game: Game): Unit = currentStone = game.player
  
  def getStone: Stone = stone
  def player: String = stone.toString
  def opponent: Stone = if stone.equals(Stone.White) then Stone.Black else Stone.White
  def next(): Unit = {
    currentStone = if (currentStone == Stone.White) Stone.Black else Stone.White
  }
  
  var roundCount: Int = 0
  def incrementCount(): Unit = if stone.equals(Stone.White) then roundCount = roundCount + 1 else roundCount = roundCount

