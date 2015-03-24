package game;

import game.elements.Hex;
import game.elements.HexImpl;
import game.elements.HexValue;

import java.util.*;

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

    /**
     * Faster implementation of allHexesConnectedToHex which breaks fast on finding a a valid path
     * 'from' one hex 'to' another
     * @param currentState current state of the board
     * @param from the hex you want to test from
     * @param to the hex you want to test if has a path to
     * @return true if 'from' is connected to 'to'
     */
    public static boolean hasPathTo(Set<Hex> currentState, Hex from, Hex to){
        Set<Hex> frontier = new HashSet<>();
        frontier.add(from);
        Set<Hex> visited = new HashSet<>();
        visited.add(from);
        Map<Hex, Hex> currentHexMap = getHashMapFromSet(currentState);
        while (!frontier.isEmpty()){
            Hex currentHex = frontier.iterator().next();
            for (Hex neighbour: getNeighboursOfSameValue(currentHexMap, currentHex)){
                if(!visited.contains(neighbour)){
                    if (neighbour.equals(to)){
                        return true;
                    } else {
                        visited.add(neighbour);
                        frontier.add(neighbour);
                    }
                }
            }
            frontier.remove(currentHex);
        }
        return false;
    }

    /**
     * Finds the shortest path between two hexes, where "shortest" is the lowest numbers of moves a player needs to make
     * to connect the two hexes.
     * @param currentState of the game
     * @param starting the starting hex - assumes is a Hex the Player has played.
     * @param target the hex to connect to
     * @param hexValue value of the player
     * @return list of hexes from the starting hex to the target hex
     */
    public static Optional<ArrayList<Hex>> findShortestPath(Set<Hex> currentState, Hex starting, Hex target, HexValue hexValue){
        if(target.getHexValue() != HexValue.EMPTY && target.getHexValue() != hexValue ||
                starting.getHexValue() != HexValue.EMPTY && starting.getHexValue() != hexValue){
            return Optional.empty();
        }
        Set<Hex> frontier = new HashSet<>();
        frontier.add(starting);
        HashMap<Hex,Hex> cameFrom = new HashMap<>();
        HashMap<Hex,Integer> costSoFar = new HashMap<>();
        cameFrom.put(starting, null);
        costSoFar.put(starting, 0);
        Map<Hex, Hex> currentHexMap = getHashMapFromSet(currentState);
        boolean found = false;
        while (!frontier.isEmpty()){
            Hex current = frontier.iterator().next();
            if(current.equals(target)){
                found = true;
            }
            Set<Hex> availableNeighbours = getNeighboursOfValue(currentHexMap, current, HexValue.EMPTY);
            availableNeighbours.addAll(getNeighboursOfValue(currentHexMap, current, starting.getHexValue()));
            for (Hex neighbour: availableNeighbours){
                Integer cost = costSoFar.get(current);
                if (neighbour.getHexValue() == HexValue.EMPTY){
                    cost++;
                }
                if (!costSoFar.containsKey(neighbour)){
                    costSoFar.put(neighbour,cost);
                    cameFrom.put(neighbour,current);
                    frontier.add(neighbour);
                } else if( costSoFar.get(neighbour) > cost){
                    costSoFar.replace(neighbour,cost);
                    cameFrom.replace(neighbour,current);
                    frontier.add(neighbour);
                }
            }
            frontier.remove(current);
        }
        if (found){
            ArrayList<Hex> path = new ArrayList<>();
            path.add(target);
            while (!target.equals(starting)){
                target = cameFrom.get(target);
                path.add(target);
            }
            Collections.reverse(path);
            return Optional.of(path);
        }else {
            return Optional.empty();
        }
    }
    /**
     * Finds the shortest path between two hexes, where "shortest" is the lowest numbers of moves a player needs to make
     * to connect the two hexes. Uses the A* Algorithm
     * @param currentState of the game
     * @param starting the starting hex - assumes is a Hex the Player has played.
     * @param target the hex to connect to
     * @param hexValue value of the player
     * @return list of hexes from the starting hex to the target hex
     */
    //TODO: implement this properly if dijkstra's is too slow. Will need comparator for the priority queue
    public static Optional<ArrayList<Hex>> findShortestPathAStar(Set<Hex> currentState, Hex starting, Hex target, HexValue hexValue){
        if(target.getHexValue() != HexValue.EMPTY && target.getHexValue() != hexValue ||
                starting.getHexValue() != HexValue.EMPTY && starting.getHexValue() != hexValue){
            return Optional.empty();
        }
//        PriorityQueue<Hex> frontier = new PriorityQueue<>();
        TreeMap<Integer, Hex> frontier = new TreeMap<>();
        frontier.put(0,starting);
        HashMap<Hex,Hex> cameFrom = new HashMap<>();
        cameFrom.put(starting, null);
        HashMap<Hex,Integer> costSoFar = new HashMap<>();
        costSoFar.put(starting, 0);
        Map<Hex, Hex> currentHexMap = getHashMapFromSet(currentState);
        boolean found = false;
        while (!frontier.isEmpty()){
            Hex current = frontier.firstEntry().getValue();
            if(current.equals(target)){
                found = true;
                break;
            }
            Set<Hex> availableNeighbours = getNeighboursOfValue(currentHexMap, current, HexValue.EMPTY);
            availableNeighbours.addAll(getNeighboursOfValue(currentHexMap, current, starting.getHexValue()));
            for (Hex neighbour: availableNeighbours){
                Integer cost = costSoFar.get(current);
                if (neighbour.getHexValue() == HexValue.EMPTY){
                    cost++;
                }
                if (!costSoFar.containsKey(neighbour)){
                    costSoFar.put(neighbour,cost);
                    cameFrom.put(neighbour,current);
                    frontier.put((int) (cost + hexDistance(neighbour,target)),neighbour);
                } else if( costSoFar.get(neighbour) > cost){
                    costSoFar.replace(neighbour,cost);
                    cameFrom.replace(neighbour,current);
                    frontier.replace((int) (cost + hexDistance(neighbour,target)),neighbour);
                }
            }
            frontier.remove(frontier.get(current));
        }
        if (found){
            ArrayList<Hex> path = new ArrayList<>();
            path.add(target);
            while (!target.equals(starting)){
                target = cameFrom.get(target);
                path.add(target);
            }
            Collections.reverse(path);
            return Optional.of(path);
        }else {
            return Optional.empty();
        }
    }
    public static double hexDistance(Hex hex1, Hex hex2){
        int x1 = hex1.getXAxis();
        int x2 = hex2.getXAxis();
        int y1 = hex1.getYAxis();
        int y2 = hex2.getYAxis();
        return (Math.abs(x1 - x2) + Math.abs(y1 - y2) + Math.abs(x1 + y1 - x2 - y2))/2;
    }
    public static Map<Hex, Hex> getHashMapFromSet(Set<Hex> hexSet){
        Map<Hex, Hex> hashMap = new HashMap<>();
        for(Hex hex: hexSet){
            hashMap.put(hex,hex);
        }
        return hashMap;
    }
    private static Set<Hex> getNeighboursOfValue(Map<Hex,Hex> hexMap, Hex hex, HexValue hexValue){
        Set<Hex> neighbours = new HashSet<>();
        Set<Hex> allPossibleNeighbours = getAllPossibleNeighbours(hex);
        for(Hex possibleHex: allPossibleNeighbours){
            Hex hexFromMap = hexMap.get(possibleHex);
            if (hexFromMap == null) continue;
            if(hexFromMap.getHexValue() == hexValue ){
                neighbours.add(hexFromMap);
            }
        }
        return neighbours;
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
