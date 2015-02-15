package game;

import game.elements.Corner;
import game.elements.Hex;
import game.elements.HexImpl;
import game.elements.HexValue;

import java.util.HashSet;
import java.util.Set;

import static game.GameHelpers.hasPathTo;

/**
 * Implementation of GameOver
 * Created by kyle on 2/8/15.
 */
public class CheckGameStateImpl implements CheckGameState {

    private Hex topCorner;
    private Hex topRightCorner;
    private Hex bottomRightCorner;
    private Hex bottomCorner;
    private Hex bottomLeftCorner;
    private Hex topLeftCorner;

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
                switch (hex.getCorner()) {
                    case TOP:
                        topCorner = hex;
                        break;
                    case TOPRIGHT:
                        topRightCorner = hex;
                        break;
                    case BOTTOMRIGHT:
                        bottomRightCorner = hex;
                        break;
                    case BOTTOM:
                        bottomCorner = hex;
                        break;
                    case BOTTOMLEFT:
                        bottomLeftCorner = hex;
                        break;
                    case TOPLEFT:
                        topLeftCorner = hex;
                        break;
                    case NOTACORNER:
                        break;
                }
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
        for (Corner corner: Corner.values()){
            switch(corner){
                case TOP:
                    if(topCorner != null && topCorner.getHexValue() == hexValue
                            && hasPathTo(currentState, move, topCorner)
                            || move.equals(topCorner)){
                        cornersConnected++;
                    }
                    break;
                case TOPRIGHT:
                    if(topRightCorner != null && topRightCorner.getHexValue() == hexValue
                            && hasPathTo(currentState,move,topRightCorner)
                            || move.equals(topRightCorner)){
                        cornersConnected++;
                    }
                    break;
                case BOTTOMRIGHT:
                    if(bottomRightCorner != null && bottomRightCorner.getHexValue() == hexValue
                            && hasPathTo(currentState,move,bottomRightCorner)
                            || move.equals(bottomRightCorner)){
                        cornersConnected++;
                    }
                    break;
                case BOTTOM:
                    if(bottomCorner != null && bottomCorner.getHexValue() == hexValue &&
                            hasPathTo(currentState,move,bottomCorner)
                            || move.equals(bottomCorner)){
                        cornersConnected++;
                    }
                    break;
                case BOTTOMLEFT:
                    if(bottomLeftCorner != null && bottomLeftCorner.getHexValue() == hexValue &&
                            hasPathTo(currentState,move,bottomCorner)
                            || move.equals(bottomLeftCorner)){
                        cornersConnected++;
                    }
                    break;
                case TOPLEFT:
                    if(topLeftCorner != null && topLeftCorner.getHexValue() == hexValue &&
                            hasPathTo(currentState,move,topLeftCorner)
                            || move.equals(topLeftCorner)){
                        cornersConnected++;
                    }
                    break;
                case NOTACORNER:
                    break;
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
