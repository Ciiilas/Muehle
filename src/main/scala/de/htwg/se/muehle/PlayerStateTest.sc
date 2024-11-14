import de.htwg.se.muehle.model.gamefield.Stone

object PlayerState:
  var stone: Stone = Stone.White
  def player: String = stone.toString
  def next(): Unit = if stone.equals(Stone.White) then stone = Stone.Black else stone = Stone.White
  var roundCount: Int = 0
  def incrementCount():Unit = if stone.equals(Stone.White) then roundCount = roundCount + 1 else roundCount = roundCount


PlayerState.stone
PlayerState.incrementCount()
PlayerState.roundCount
PlayerState.next()
PlayerState.stone
PlayerState.incrementCount()
PlayerState.roundCount
PlayerState.next()
PlayerState.stone
PlayerState.incrementCount()
PlayerState.roundCount
PlayerState.next()
PlayerState.stone
PlayerState.incrementCount()
PlayerState.roundCount
PlayerState.next()
PlayerState.stone
PlayerState.incrementCount()
PlayerState.roundCount