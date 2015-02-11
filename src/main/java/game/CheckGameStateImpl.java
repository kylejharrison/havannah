package game;

import game.elements.Hex;
import game.elements.HexValue;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of GameOver
 * Created by kyle on 2/8/15.
 */
public class CheckGameStateImpl implements CheckGameState {

    @Override
    public GameState getGameState(Set<Hex> currentState, Hex move, HexValue hexValue) {
        if(isWinningMove(currentState, move, hexValue)){
            return GameState.WINNER;
        }else if(areAllHexesFilledAfterMove(currentState, move)){
            return GameState.DRAW;
        }else{
            return GameState.STILLTOPLAYFOR;
        }
    }

    private boolean isWinningMove(Set<Hex> currentState, Hex move, HexValue hexValue){
        return isWinByCorner(currentState, move, hexValue) || isWinByLoop(currentState, move, hexValue)
                || isWinByEdge(currentState, move, hexValue);
    }

    private boolean isWinByCorner(Set<Hex> currentState, Hex move, HexValue hexValue){
        return false;
    }

    private boolean isWinByLoop(Set<Hex> currentState, Hex move, HexValue hexValue){

        return false;
    }
    private boolean isWinByEdge(Set<Hex> currentState, Hex move, HexValue hexValue){

        return false;
    }
    private boolean areAllHexesFilledAfterMove(Set<Hex> currentState, Hex move){
        Set<Hex> allEmpty = new HashSet<Hex>();
        for (Hex hex: currentState){
            if (hex.getHexValue() == HexValue.EMPTY){
                allEmpty.add(hex);
            }
        }
        allEmpty.remove(move);
        return allEmpty.size() == 0;
    }
}
