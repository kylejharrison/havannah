package game;

import game.elements.Corner;
import game.elements.Hex;
import game.elements.HexImpl;
import game.elements.HexValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static game.GameHelpers.hasPathTo;

/**
 * Implementation of GameOver
 * Created by kyle on 2/8/15.
 */
public class CheckGameStateImpl implements CheckGameState {

    private Map<Corner,Hex> cornerMap = new HashMap<Corner, Hex>();

    @Override
    public GameState getGameState(Set<Hex> currentState, Hex move, HexValue hexValue) {
        setCorners(currentState);
        Set<Hex> newCurrentState = switchMoveToPlayerValue(currentState,move,hexValue);
        HexImpl newMove = new HexImpl(move.getXAxis(),move.getYAxis());
        newMove.setHexValue(hexValue);
        if(isWinningMove(newCurrentState, newMove, hexValue)){
            return GameState.WINNER;
        }else if(areAllHexesFilledAfterMove(newCurrentState)){
            return GameState.DRAW;
        }else{
            return GameState.STILLTOPLAYFOR;
        }
    }

    private void setCorners(Set<Hex> currentState){
        for (Hex hex: currentState) {
            if (hex.getCorner().isACorner()) {
                cornerMap.put(hex.getCorner(),hex);
            }
        }
    }
    private boolean isWinningMove(Set<Hex> currentState, Hex move, HexValue hexValue){
        return isWinByCorner(currentState, move, hexValue) || isWinByLoop(currentState, move, hexValue)
                || isWinByEdge(currentState, move, hexValue);
    }

    private Set<Hex> switchMoveToPlayerValue(Set<Hex> currentState, Hex move, HexValue hexValue){
        Set<Hex> newCurrentState = new HashSet<Hex>();
        for (Hex hex: currentState){
            HexImpl newHex = new HexImpl(hex.getXAxis(),hex.getYAxis(),hex.getEdge(),hex.getCorner());
            if(newHex.equals(move)){
                newHex.setHexValue(hexValue);
            } else{
                newHex.setHexValue(hex.getHexValue());
            }
            newCurrentState.add(newHex);
        }
        return newCurrentState;
    }

    private boolean isWinByCorner(Set<Hex> currentState, Hex move, HexValue hexValue){
        int cornersConnected = 0;
        for (Corner corner: Corner.values()) {
            Hex cornerHex;
            if (cornerMap.containsKey(corner)) {
                cornerHex = cornerMap.get(corner);
            } else {
                continue;
            }
            if (cornerMap.containsKey(corner) && cornerHex.getHexValue() == hexValue
                    && hasPathTo(currentState, move, cornerHex)
                    || move.equals(cornerHex)) {
                cornersConnected++;
            }
        }
        return cornersConnected >= 2;
    }
    private boolean isWinByLoop(Set<Hex> currentState, Hex move, HexValue hexValue){

        return false;
    }
    private boolean isWinByEdge(Set<Hex> currentState, Hex move, HexValue hexValue){

        return false;
    }
    private boolean areAllHexesFilledAfterMove(Set<Hex> currentState){
        Set<Hex> allEmpty = new HashSet<Hex>();
        for (Hex hex: currentState){
            if (hex.getHexValue() == HexValue.EMPTY){
                allEmpty.add(hex);
            }
        }
        return allEmpty.size() == 0;
    }
}
