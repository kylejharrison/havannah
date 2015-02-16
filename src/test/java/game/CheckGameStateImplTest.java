package game;

import game.elements.Hex;
import game.elements.HexImpl;
import game.elements.HexValue;
import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static game.GameHelpers.addHexToSet;
import static org.testng.AssertJUnit.assertEquals;

public class CheckGameStateImplTest {

    //TODO: fix tests for draws using real board (as otherwise are detected as loops)
    @Test(enabled = false)
    public void testGetGameStateReturnsDrawWhenMoveIsLastEmptyHex() throws Exception {
        Set<HexImpl> gameBoard = new HexGenerator(8).generateHexes();
        Set<Hex> currentState = new HashSet<>();
        Hex drawingMove = new HexImpl(0,0);
        for (HexImpl hex: gameBoard){
            if(!hex.equals(drawingMove)){
                hex.setHexValue(HexValue.BLUE);
            }
            currentState.add(hex);
        }
        assertEquals(GameState.DRAW, new CheckGameStateImpl().getGameState(currentState, drawingMove, HexValue.BLUE));
    }
    @Test(enabled = false)
    public void testGetGameStateReturnsStillToPlayForWhenEmptyHexesRemain() throws Exception{
        Set<Hex> testState = TestHelpers.getHexCollection(9,9,1);
        Hex move = new HexImpl(-1, -1);
        testState.add(move);
        assertEquals(GameState.STILLTOPLAYFOR, new CheckGameStateImpl().getGameState(testState, move, HexValue.BLUE));
    }
    @Test
    public void testCanDetectACornerWinWhenMoveIsACorner() throws Exception{
        Set<HexImpl> gameBoard = new HexGenerator(3).generateHexes();
        Set<Hex> currentState = new HashSet<>();
        for(HexImpl hex: gameBoard){
            if(hex.getYAxis() == -2 && hex.getXAxis() < 2){
                hex.setHexValue(HexValue.BLUE);
            }
            currentState.add(hex);
        }
        Hex move = new HexImpl(2,-2);
        assertEquals(GameState.WINNER,new CheckGameStateImpl().getGameState(currentState,move,HexValue.BLUE));
    }

    @Test
    public void testCanDetectACornerWinWhenMoveIsNotACorner() throws Exception{
        Set<HexImpl> gameBoard = new HexGenerator(3).generateHexes();
        Set<Hex> currentState = new HashSet<>();
        Hex preMove1 = new HexImpl(0,-2);
        Hex preMove2 = new HexImpl(2,-2);
        for(HexImpl hex: gameBoard){
            if(hex.equals(preMove1) || hex.equals(preMove2)){
                hex.setHexValue(HexValue.BLUE);
            }
            currentState.add(hex);
        }
        Hex move = new HexImpl(1,-2);
        assertEquals(GameState.WINNER,new CheckGameStateImpl().getGameState(currentState,move,HexValue.BLUE));
    }

    @Test
    public void testCanDetectACornerWinOnAMassiveBoard() throws Exception{
        Set<HexImpl> gameBoard = new HexGenerator(20).generateHexes();
        Set<Hex> currentState = new HashSet<>();
        Hex move = new HexImpl(0,-19);
        for(HexImpl hex: gameBoard){
            if(!hex.equals(move) && !hex.getEdge().isAnEdge()){
                hex.setHexValue(HexValue.BLUE);
            }
            currentState.add(hex);
        }
        assertEquals(GameState.WINNER,new CheckGameStateImpl().getGameState(currentState,move,HexValue.BLUE));
    }

    @Test
    public void testCanDetectAnEdgeWinWhenMoveIsNotAnEdge() throws Exception{
        Set<HexImpl> gameBoard = new HexGenerator(3).generateHexes();
        Set<Hex> currentState = new HashSet<>();
        Hex move = new HexImpl(0,0);
        Set<Hex> preMoves = new HashSet<>();
        addHexToSet(preMoves, -1, -1, HexValue.BLUE);
        addHexToSet(preMoves, 0,-1,HexValue.BLUE);
        addHexToSet(preMoves,1,-2,HexValue.BLUE);
        addHexToSet(preMoves,1,0,HexValue.BLUE);
        addHexToSet(preMoves,1,1,HexValue.BLUE);
        for(HexImpl hex: gameBoard){
            if(preMoves.contains(hex)){
                hex.setHexValue(HexValue.BLUE);
            }
            currentState.add(hex);
        }
        assertEquals(GameState.WINNER,new CheckGameStateImpl().getGameState(currentState,move,HexValue.BLUE));
    }
    @Test
    public void testCanDetectAnEdgeWinWhenMoveIsAnEdge() throws Exception{
        Set<HexImpl> gameBoard = new HexGenerator(3).generateHexes();
        Set<Hex> currentState = new HashSet<>();
        Hex move = new HexImpl(-1,-1);
        Set<Hex> preMoves = new HashSet<>();
        addHexToSet(preMoves, 0, 0, HexValue.BLUE);
        addHexToSet(preMoves, 0,-1,HexValue.BLUE);
        addHexToSet(preMoves,1,-2,HexValue.BLUE);
        addHexToSet(preMoves,1,0,HexValue.BLUE);
        addHexToSet(preMoves,1,1,HexValue.BLUE);
        for(HexImpl hex: gameBoard){
            if(preMoves.contains(hex)){
                hex.setHexValue(HexValue.BLUE);
            }
            currentState.add(hex);
        }
        assertEquals(GameState.WINNER,new CheckGameStateImpl().getGameState(currentState,move,HexValue.BLUE));
    }
    @Test
    public void testCanDetectAnEdgeWinOnAMassiveBoard() throws Exception{
        Set<HexImpl> gameBoard = new HexGenerator(20).generateHexes();
        Set<Hex> currentState = new HashSet<>();
        Hex move = new HexImpl(0,0);
        for(HexImpl hex: gameBoard){
            if(!hex.equals(move) && !hex.getCorner().isACorner()){
                hex.setHexValue(HexValue.BLUE);
            }
            currentState.add(hex);
        }
        assertEquals(GameState.WINNER,new CheckGameStateImpl().getGameState(currentState,move,HexValue.BLUE));
    }
    @Test
    public void testCanDetectSimpleLoop() throws Exception {
        Set<HexImpl> gameBoard = new HexGenerator(3).generateHexes();
        Set<Hex> currentState = new HashSet<>();
        Set<Hex> loop = new HashSet<>();
//        addHexToSet(loop,-1,0,HexValue.BLUE);
        addHexToSet(loop,0,-1,HexValue.BLUE);
        addHexToSet(loop,1,-1,HexValue.BLUE);
        addHexToSet(loop,1,0,HexValue.BLUE);
        addHexToSet(loop,0,1,HexValue.BLUE);
        addHexToSet(loop,-1,1,HexValue.BLUE);
        for(HexImpl hex: gameBoard){
            if(loop.contains(hex)){
                hex.setHexValue(HexValue.BLUE);
            }
            currentState.add(hex);
        }
        HexImpl testHex = new HexImpl(-1,0);
        Assert.assertEquals(GameState.WINNER, new CheckGameStateImpl().getGameState(currentState,testHex,HexValue.BLUE));
    }
}