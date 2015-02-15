package game;

import game.elements.Hex;
import game.elements.HexImpl;
import game.elements.HexValue;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class CheckGameStateImplTest {

    @Test
    public void testGetGameStateReturnsDrawWhenMoveIsLastEmptyHex() throws Exception {
        Set<Hex> testState = TestHelpers.getHexCollection(9,9,0);
        Hex drawingMove = new HexImpl(-1, -1);
        testState.add(drawingMove);
        assertEquals(GameState.DRAW, new CheckGameStateImpl().getGameState(testState, drawingMove, HexValue.BLUE));
    }
    @Test
    public void testGetGameStateReturnsStillToPlayForWhenEmptyHexesRemain() throws Exception{
        Set<Hex> testState = TestHelpers.getHexCollection(9,9,1);
        Hex move = new HexImpl(-1, -1);
        testState.add(move);
        assertEquals(new CheckGameStateImpl().getGameState(testState, move, HexValue.BLUE), GameState.STILLTOPLAYFOR);
    }
    @Test
    public void testCanDetectACornerWinWhenMoveIsACorner() throws Exception{
        Set<HexImpl> gameBoard = new HexGenerator(3).generateHexes();
        Set<Hex> currentState = new HashSet<Hex>();
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
        Set<Hex> currentState = new HashSet<Hex>();
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
        Set<Hex> currentState = new HashSet<Hex>();
        Hex move = new HexImpl(0,-19);
        for(HexImpl hex: gameBoard){
            if(!hex.equals(move)){
                hex.setHexValue(HexValue.BLUE);
            }
            currentState.add(hex);
        }
        assertEquals(GameState.WINNER,new CheckGameStateImpl().getGameState(currentState,move,HexValue.BLUE));
    }
}