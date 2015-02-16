package game;

import game.elements.Hex;
import game.elements.HexImpl;
import game.elements.HexValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Helper methods for game algorithms
 * Created by kyle on 2/14/15.
 */
public class GameHelpers {

    public static Set<Set<Hex>> getAllUnconnectedHexes(Set<Hex> currentState, Hex hex){
        return getAllUnconnectedHexes(currentState, allHexesConnectedToHex(currentState,hex));
    }

    public static Set<Hex> cloneHexSetWithOutValues(Set<Hex> hexSet){
        Set<Hex> newSet = new HashSet<>();
        for(Hex hex: hexSet){
            HexImpl newHex = new HexImpl(hex.getXAxis(),hex.getYAxis(),hex.getEdge(),hex.getCorner());
            newSet.add(newHex);
        }
        return newSet;
    }

    public static Set<Set<Hex>> getAllUnconnectedHexes(Set<Hex> currentState, Set<Hex> allHexesConnectedToHex){
        Set<Set<Hex>> unconnected = new HashSet<>();
        Set<Hex> cloneCurrentState = cloneHexSetWithOutValues(currentState);
        cloneCurrentState.removeAll(allHexesConnectedToHex);
        while(!cloneCurrentState.isEmpty()){
            Set<Hex> hexSet = new HashSet<>();
            Hex aHex = cloneCurrentState.iterator().next();
            hexSet.addAll(allHexesConnectedToHex(cloneCurrentState,aHex));
            unconnected.add(hexSet);
            cloneCurrentState.removeAll(hexSet);
        }
        return unconnected;
    }

    /**
     * returns a set of all hexes connected to the given hex, including the hex itself
     * Where connected is next to a hex of the same value or via hexes of the same value
     * @param currentState the currentstate of the board
     * @param hex the hex on the board you want to test (not a future move)
     * @return all hexes currently connected to the hex
     */
    public static Set<Hex> allHexesConnectedToHex(Set<Hex> currentState, Hex hex){
        Set<Hex> frontier = new HashSet<>();
        frontier.add(hex);
        Set<Hex> visited = new HashSet<>();
        visited.add(hex);
        Map<Hex, Hex> currentHexMap = getHashMapFromSet(currentState);
        while (!frontier.isEmpty()){
            Hex currentHex = frontier.iterator().next();
            getNeighboursOfSameValue(currentHexMap, currentHex).stream().filter(neighbour -> !visited.contains(neighbour)).forEach(neighbour -> {
                visited.add(neighbour);
                frontier.add(neighbour);
            });
            frontier.remove(currentHex);
        }
        return visited;
    }

    public static boolean hasPathTo(Set<Hex> currentState, Hex from, Hex to){
        Set<Hex> allConnectedTo = allHexesConnectedToHex(currentState, from);
        return allConnectedTo.contains(to);
    }
    public static Map<Hex, Hex> getHashMapFromSet(Set<Hex> hexSet){
        Map<Hex, Hex> hashMap = new HashMap<>();
        for(Hex hex: hexSet){
            hashMap.put(hex,hex);
        }
        return hashMap;
    }
    public static Set<Hex> getNeighboursOfSameValue(Map<Hex,Hex> hexMap, Hex hex){
        Set<Hex> neighboursOfSameValue = new HashSet<>();
        Set<Hex> allPossibleNeighbours = getAllPossibleNeighbours(hex);
        for(Hex possibleHex: allPossibleNeighbours){
            Hex hexFromMap = hexMap.get(possibleHex);
            if (hexFromMap == null) continue;
            if(hexFromMap.getHexValue() == hex.getHexValue() ){
                neighboursOfSameValue.add(hexFromMap);
            }
        }
        return neighboursOfSameValue;
    }
    private static Set<Hex> getAllPossibleNeighbours(Hex hex){
        Set<Hex> allPossibleNeighbours = new HashSet<>();
        int xAxis = hex.getXAxis();
        int yAxis = hex.getYAxis();
        addHexToSet(allPossibleNeighbours, xAxis, yAxis - 1, HexValue.EMPTY);
        addHexToSet(allPossibleNeighbours, xAxis + 1, yAxis -1, HexValue.EMPTY);
        addHexToSet(allPossibleNeighbours, xAxis + 1, yAxis,HexValue.EMPTY);
        addHexToSet(allPossibleNeighbours, xAxis, yAxis + 1,HexValue.EMPTY);
        addHexToSet(allPossibleNeighbours, xAxis -1, yAxis + 1, HexValue.EMPTY);
        addHexToSet(allPossibleNeighbours, xAxis -1, yAxis, HexValue.EMPTY);
        return allPossibleNeighbours;
    }

    public static void addHexToSet(Set<Hex> set, int xAxis, int yAxis, HexValue hexValue){
        HexImpl hex = new HexImpl(xAxis, yAxis);
        hex.setHexValue(hexValue);
        set.add(hex);
    }
}
