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

    public static boolean hasPathTo(Set<Hex> currentState, Hex from, Hex to){
        Set<Hex> frontier = new HashSet<Hex>();
        frontier.add(from);
        Set<Hex> visited = new HashSet<Hex>();
        Map<Hex, Hex> currentHexMap = getHashMapFromSet(currentState);
        while (!frontier.isEmpty()){
            Hex currentHex = frontier.iterator().next();
            for(Hex neighbour: getNeighboursOfSameValue(currentHexMap,currentHex)){
                if(!visited.contains(neighbour)){
                    visited.add(neighbour);
                    frontier.add(neighbour);
                }
            }
            frontier.remove(currentHex);
            if(visited.contains(to)){
                return true;
            }
        }
        return false;
    }
    public static Map<Hex, Hex> getHashMapFromSet(Set<Hex> hexSet){
        Map<Hex, Hex> hashMap = new HashMap<Hex, Hex>();
        for(Hex hex: hexSet){
            hashMap.put(hex,hex);
        }
        return hashMap;
    }
    public static Set<Hex> getNeighboursOfSameValue(Map<Hex,Hex> hexMap, Hex hex){
        Set<Hex> neighboursOfSameValue = new HashSet<Hex>();
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
        Set<Hex> allPossibleNeighbours = new HashSet<Hex>();
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
