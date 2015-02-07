package game;

import game.elements.Corner;
import game.elements.Edge;
import game.elements.HexImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Generates the Set of Hexes for a game
 * Created by kyle on 2/7/15.
 */
public class HexGenerator {

    private final Set<HexImpl> allHexes = new HashSet<HexImpl>();
    private final int boardSize;

    public HexGenerator(int boardSize){
        this.boardSize = boardSize;
    }
    public Set<HexImpl> generateHexes() {
        generateHexesToTheRight();
        generateHexesToTheLeft();
        return allHexes;
    }

    private void generateHexesToTheRight(){
        for (int x = 0; x < boardSize; x++) {
            generateHexesGoingUpRight(x);
            generateHexesGoingDownRight(x);
        }
    }

    private void generateHexesGoingUpRight(int x){
        for (int y = 0; y < boardSize; y++) {
            createHex(x, -y);
        }
    }

    private void generateHexesGoingDownRight(int x){
        for (int y = 0; y < boardSize -1 -x; y++) {
            createHex(x, y + 1);
        }
    }
    private void generateHexesToTheLeft() {
        for (int x = 0; x < boardSize - 1; x++) {
            //draw tiles upwards
            generateHexesGoingUpLeft(x);
            generateHexesGoingDownLeft(x);
        }
    }

    private void generateHexesGoingUpLeft(int x){
        for (int y = 0; y < boardSize - 1 - x; y++) {
            createHex(-x - 1, -y);
        }
    }

    private void generateHexesGoingDownLeft(int x){
        for (int y = 0; y < boardSize - 1; y++) {
            createHex(-x - 1, y + 1);
        }
    }

    private void createHex(int x, int y){
        final HexImpl hex = new HexImpl(x, y, Edge.NOTANEDGE, Corner.NOTACORNER); //TODO: make edge and corner work properly
        allHexes.add(hex);
    }
}

