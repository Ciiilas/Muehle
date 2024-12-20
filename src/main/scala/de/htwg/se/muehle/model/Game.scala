package de.htwg.se.muehle.model


import de.htwg.se.muehle.model.mechanic.*
import de.htwg.se.muehle.model.gamefield.*


case class Game(mech: Mechanic, field: Gamefield, var message: Option[String] = None, var player: Stone = Stone.White, var currentGameState: GameStateEnum = GameStateEnum.SET_STONE) {
  def this() = this(Mechanic(), new Gamefield())
  def this(gamefield: Gamefield) = this(Mechanic(), gamefield)

  //-----------------------------------------------------
  //mechanic
  //-----------------------------------------------------
  def getGameMechanic: Mechanic = mech

  def setStoneGame(ring: Int, posOnRing: Int): Game = {
    this.currentGameState = checkAndSetGameState()
    if (currentGameState == GameStateEnum.SET_STONE) {
      if (mech.isSetLegal(field, ring, posOnRing)) {
        println("setCount Before: " + mech.setCount)
        val (newMech, newField) = mech.setStone(field, ring, posOnRing, PlayerState.stone)
        println("setCount After: " + newMech.setCount)
        if (!isMuehle(newField, ring, posOnRing)) {
          val currentPlayer: Stone = PlayerState.stone // hilfsvalue um den Spieler temporär zu speichern
          PlayerState.next()
          this.player = PlayerState.stone
          return Game(newMech, newField, Some(currentPlayer.toString + " hat einen Stein gesetzt!"), player, currentGameState)
        } else {
          this.currentGameState = GameStateEnum.REMOVE_STONE
          return Game(newMech, newField, Some(this.player.toString + " hat eine Mühle! Bitte wählen Sie einen Stein den Sie entfernen möchten!"), player, currentGameState)
        }
      }
      return Game(mech, field, Some("Error! Der Stein darf hier nicht Platziert werden!"), player, currentGameState)
    }
    Game(mech, field, Some("Error! Aktuell darf kein Stein gesetzt werden, versuchen Sie einen anderen Befehl!"), player, currentGameState)
  }

  def moveStoneGame(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game = {
    this.currentGameState = checkAndSetGameState()
    if (this.currentGameState == GameStateEnum.MOVE_STONE) {
      if (mech.isMoveLegal(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)) {
        val newField: Gamefield = mech.moveStone(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)
        if (!isMuehle(newField, newRing, newPosOnRing)) {
          val currentPlayer: Stone = PlayerState.stone // hilfsvalue um den Spieler temporär zu speichern
          PlayerState.next()
          this.player = PlayerState.stone
          return Game(mech, newField, Some(this.player.toString + " hat einen Stein bewegt!"), player, currentGameState)
        } else {
          this.currentGameState = GameStateEnum.REMOVE_STONE
          return Game(mech, newField, Some(this.player.toString + " hat eine Mühle! Bitte wählen Sie einen Stein den Sie entfernen möchten!"), player, currentGameState)
        }
      }
      return Game(mech, field, Some("Error! Der Stein darf hier nicht bewegt werden!"), player, currentGameState)
    }
    Game(mech, field, Some("Error! Es darf aktuell kein Stein Bewegt werden!"), player, currentGameState)
  }

  def jumpStoneGame(oldRing: Int, oldPosOnRing: Int, newRing: Int, newPosOnRing: Int): Game = {
    this.currentGameState = checkAndSetGameState()
    if (this.currentGameState == GameStateEnum.JUMP_STONE) {
      if (mech.isJumpLegal(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)) {
        val newField: Gamefield = mech.jumpStone(field, oldRing, oldPosOnRing, newRing, newPosOnRing, PlayerState.stone)
        if (!isMuehle(newField, newRing, newPosOnRing)) {
          val currentPlayer: Stone = PlayerState.stone // hilfsvalue um den Spieler temporär zu speichern
          PlayerState.next()
          this.player = PlayerState.stone
          return Game(mech, newField, Some(PlayerState.opponent.toString + " ist mit einem Stein gesprungen!"), player, currentGameState)
        } else {
          this.currentGameState = GameStateEnum.REMOVE_STONE
          return Game(mech, newField, Some(PlayerState.player + " hat eine Mühle! Bitte wählen Sie einen Stein den Sie entfernen möchten!"), player, currentGameState)
        }
      }
      return Game(mech, field, Some("Error! Mit dem Stein darf nicht gesprungen werden!"), player, currentGameState)
    }
    Game(mech, field, Some("Error! Es darf aktuell nicht mit dem Stein gesprungen werden!"), player, currentGameState)
  }

  def removeStoneGame(ring: Int, posOnRing: Int): Game = {
    if (!this.currentGameState.equals(GameStateEnum.REMOVE_STONE)) {
      return Game(mech, field, Some("Error! Es gibt keine Mühle, es darf kein Stein entfernt werden!"), player, currentGameState)
    }
    if (mech.isRemoveLegal(field, ring, posOnRing, PlayerState.stone)) {
      val newField: Gamefield = mech.removeStone(field, ring, posOnRing, PlayerState.stone)
      val currentPlayer: Stone = PlayerState.stone // hilfsvalue um den Spieler temporär zu speichern

      PlayerState.next()
      this.player = PlayerState.stone
      this.currentGameState = checkAndSetGameState()
      return Game(mech, newField, Some(currentPlayer.toString + " hat einen Stein entfernt!"), player, currentGameState)
    }
    Game(mech, field, Some("Error! Der Stein kann nicht entfernt werden!"), player, currentGameState)
  }

  def isMuehle(field: Gamefield, ring: Int, posOnRing: Int): Boolean = {
    mech.evaluateStrategy.checkForMuehle(field, ring, posOnRing, PlayerState.stone)
  }

  
  //-----------------------------------------------------
  //gameField
  //-----------------------------------------------------

  def getField: Gamefield = field



  private var useDecorator: Boolean = false

  def setDecorator(enabled: Boolean): Unit = {
    useDecorator = enabled
  }

  def getMesh: meshComponentInterface = {
    val baseMesh = ConcreteMesh(field)
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
    if (mech.setCount < 18) { // 17, da die abfrage vor dem setzen des steins erfolgt //Note: muss noch überprüft werden
      GameStateEnum.SET_STONE
    } else if (mech.setCount == 18 && field.muehleMatrix.flatten.count((stone:Stone) => stone == PlayerState.stone) > 3) {
      GameStateEnum.MOVE_STONE
    } else if (mech.setCount == 18 && field.muehleMatrix.flatten.count((stone:Stone) => stone == PlayerState.stone) == 3) {
      GameStateEnum.JUMP_STONE
    } else {
      GameStateEnum.GAME_OVER
    }
  }

  def getOpponentPlayer: Stone = PlayerState.opponent

  override def equals(obj: Any): Boolean = super.equals(obj)
}


object PlayerState:
  var stone: Stone = Stone.White

  def getStone: Stone = stone
  def player: String = stone.toString
  def opponent: Stone = if stone.equals(Stone.White) then Stone.Black else Stone.White
  def next(): Unit = if stone.equals(Stone.White) then stone = Stone.Black else stone = Stone.White
  
  var roundCount: Int = 0
  def incrementCount(): Unit = if stone.equals(Stone.White) then roundCount = roundCount + 1 else roundCount = roundCount

