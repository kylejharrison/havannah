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
            Corner corner = Corner.NOTACORNER;
            if (x == 0 && y == boardSize -1 ){
                corner = Corner.TOP;
            }else if (x == boardSize -1 && y == boardSize - 1){
                corner = Corner.TOPRIGHT;
            }else if (x == boardSize -1 && y == 0){
                corner = Corner.BOTTOMRIGHT;
            }
            final HexImpl hex = new HexImpl(x, -y, Edge.NOTANEDGE, corner);
            allHexes.add(hex);
        }
    }

    private void generateHexesGoingDownRight(int x){
        for (int y = 0; y < boardSize -1 -x; y++) {
            Corner corner = Corner.NOTACORNER;
            if (x == 0 && y == boardSize - 2 ) {
                corner = Corner.BOTTOM;
            }
            final HexImpl hex = new HexImpl(x, y + 1, Edge.NOTANEDGE, corner);
            allHexes.add(hex);
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
            Corner corner = Corner.NOTACORNER;
            if (x == boardSize - 2 && y == 0 ){
                corner = Corner.TOPLEFT;
            }
            final HexImpl hex = new HexImpl(-x - 1, -y, Edge.NOTANEDGE, corner);
            allHexes.add(hex);
        }
    }

    private void generateHexesGoingDownLeft(int x){
        for (int y = 0; y < boardSize - 1; y++) {
            Corner corner = Corner.NOTACORNER;
            if (x == boardSize - 2 && y == boardSize - 2 ){
                corner = Corner.BOTTOMLEFT;
            }
            final HexImpl hex = new HexImpl(-x - 1, y + 1, Edge.NOTANEDGE, corner);
            allHexes.add(hex);
        }
    }

}

