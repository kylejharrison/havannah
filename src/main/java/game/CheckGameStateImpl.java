package game;

import game.elements.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import static game.GameHelpers.allHexesConnectedToHex;
import static game.GameHelpers.getAllUnconnectedHexes;

/**
 * Implementation of GameOver
 * Created by kyle on 2/8/15.
 */
public class CheckGameStateImpl implements CheckGameState {

    private static final Logger LOG = Logger.getLogger(Game.class.getName());

    private Map<Corner,Hex> cornerMap = new HashMap<>();
    private Map<Edge,Set<Hex>> edgeMap = new HashMap<>();
    private Set<Hex> allMoveWillBeConnectedTo = new HashSet<>();
    private boolean givenCornerMap = false;
    private boolean givenEdgeMap = false;
    private boolean givenAllMoveWillBeConnectedTo = false;


    public CheckGameStateImpl(){
        new CheckGameStateImpl(null,null,null);
    }

    public CheckGameStateImpl(Map<Corner,Hex> cornerMap,Map<Edge,Set<Hex>> edgeMap, Set<Hex> allMoveWillBeConnectedTo){
        if(cornerMap != null) {
            this.cornerMap = cornerMap;
            this.givenCornerMap = true;
        }
        if(edgeMap != null){
            this.edgeMap = edgeMap;
            this.givenEdgeMap = true;
        }
        if(allMoveWillBeConnectedTo != null) {
            this.allMoveWillBeConnectedTo = allMoveWillBeConnectedTo;
            this.givenAllMoveWillBeConnectedTo = true;
        }
    }

    public void setAllMoveWillBeConnectedTo(Set<Hex> newCurrentState, Hex newMove) {
        this.allMoveWillBeConnectedTo = allHexesConnectedToHex(newCurrentState, newMove);
    }

    @Override
    public GameState getGameState(Set<Hex> currentState, Hex move, HexValue hexValue) {
        Set<Hex> newCurrentState = switchMoveToPlayerValue(currentState,move,hexValue);
        HexImpl newMove = new HexImpl(move.getXAxis(),move.getYAxis());
        newMove.setHexValue(hexValue);
        if(!givenCornerMap){
            setCornerMap(newCurrentState);
        }
        if(!givenEdgeMap){
            setEdgeMap(newCurrentState);
        }
        if(!givenAllMoveWillBeConnectedTo){
            setAllMoveWillBeConnectedTo(newCurrentState, newMove);
        }
        if(isWinningMove(newCurrentState, hexValue)){
            LOG.info("It was a win!");
            return GameState.WINNER;
        }else if(areAllHexesFilledAfterMove(newCurrentState)){
            LOG.info("It was a draw");
            return GameState.DRAW;
        }else{
            return GameState.STILLTOPLAYFOR;
        }
    }

    private void setCornerMap(Set<Hex> newCurrentState){
        newCurrentState.stream().filter(hex -> hex.getCorner().isACorner()).forEach(hex ->
                cornerMap.put(hex.getCorner(), hex));
    }

    private void setEdgeMap(Set<Hex> newCurrentState){
        for(Edge edge: Edge.values()){
            if(edge.isAnEdge()){
                edgeMap.put(edge,new HashSet<>());
            }
        }
        newCurrentState.stream().filter(hex -> hex.getEdge().isAnEdge()).forEach(hex ->
        edgeMap.get(hex.getEdge()).add(hex));
    }
    private boolean isWinningMove(Set<Hex> newCurrentState, HexValue hexValue){
        return isWinByCorner(hexValue) || isWinByLoop(newCurrentState)
                || isWinByEdge(hexValue);
    }

    private Set<Hex> switchMoveToPlayerValue(Set<Hex> currentState, Hex move, HexValue hexValue){
        Set<Hex> newCurrentState = new HashSet<>();
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

    private boolean isWinByCorner(HexValue hexValue){
        int cornersConnected = 0;
        for (Corner corner: Corner.values()) {
            Hex cornerHex;
            if (cornerMap.containsKey(corner)) {
                cornerHex = cornerMap.get(corner);
            } else {
                continue;
            }
            if (cornerHex.getHexValue() == hexValue
                    && allMoveWillBeConnectedTo.stream().anyMatch(cornerHex::equals)) {
                cornersConnected++;
            }
        }
        if (cornersConnected >= 2){
            LOG.info("Corner win");
            return true;
        }
        else return false;
    }
    private boolean isWinByLoop(Set<Hex> newCurrentState){
        Set<Set<Hex>> unconnected = getAllUnconnectedHexes(newCurrentState,allMoveWillBeConnectedTo);
        if (unconnected.isEmpty()) return false;
        for(Set<Hex> hexSet: unconnected){
            if(hexSet.stream().noneMatch(hex -> hex.getCorner().isACorner() || hex.getEdge().isAnEdge())){
                LOG.info("Loop Win");
                return true;
            }
        }
        return false;
    }
    private boolean isWinByEdge(HexValue hexValue){
        int edgesConnected = 0;
        for (Edge edge: Edge.values()) {
            Set<Hex> edgeHexes;
            if (edgeMap.containsKey(edge)) {
                edgeHexes = edgeMap.get(edge);
            } else {
                continue;
            }
            if (edgeHexes.stream().anyMatch(hex -> hex.getHexValue() == hexValue)
                    && edgeHexes.stream().anyMatch(allMoveWillBeConnectedTo::contains)) {
                edgesConnected++;
            }
        }
        if (edgesConnected >= 3){
            LOG.info("Edge Win");
            return true;
        }
        else return false;
    }
    private boolean areAllHexesFilledAfterMove(Set<Hex> newCurrentState){
        return  newCurrentState.stream().allMatch(hex -> hex.getHexValue().isValidForPlayer());
    }
}
