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

    private final Set<HexImpl> allHexes = new HashSet<>();
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
            Corner corner = getGoingUpRightCorner(x, y);
            Edge edge = getGoingUpRightEdge(x, y);
            final HexImpl hex = new HexImpl(x, -y, edge, corner);
            allHexes.add(hex);
        }
    }

    private Corner getGoingUpRightCorner(int x, int y){
        Corner corner = Corner.NOTACORNER;
        if (x == 0 && y == boardSize -1 ){
            corner = Corner.TOP;
        }else if (x == boardSize -1 && y == boardSize - 1){
            corner = Corner.TOPRIGHT;
        }else if (x == boardSize -1 && y == 0){
            corner = Corner.BOTTOMRIGHT;
        }
        return corner;
    }
    private Edge getGoingUpRightEdge(int x, int y){
        Edge edge = Edge.NOTANEDGE;
        if (y == boardSize - 1 && x > 0 && x < boardSize -1){
            edge = Edge.TOPRIGHT;
        } else if (x == boardSize -1 && y > 0 && y < boardSize -1){
            edge = Edge.RIGHT;
        }
        return edge;
    }
    private void generateHexesGoingDownRight(int x){
        for (int y = 0; y < boardSize -1 -x; y++) {
            Corner corner = getGoingDownRightCorner(x, y);
            Edge edge = getGoingDownRightEdge(x, y);
            final HexImpl hex = new HexImpl(x, y + 1, edge, corner);
            allHexes.add(hex);
        }
    }

    private Corner getGoingDownRightCorner(int x, int y){
        Corner corner = Corner.NOTACORNER;
        if (x == 0 && y == boardSize - 2 ) {
            corner = Corner.BOTTOM;
        }
        return corner;
    }

    private Edge getGoingDownRightEdge(int x, int y){
        Edge edge = Edge.NOTANEDGE;
        if (y == boardSize -2 -x && x > 0 && x < boardSize -1){
            edge = Edge.BOTTOMRIGHT;
        }
        return edge;
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
            Corner corner = getGoingUpLeftCorner(x, y);
            Edge edge = getGoingUpLeftEdge(x, y);
            final HexImpl hex = new HexImpl(-x - 1, -y, edge, corner);
            allHexes.add(hex);
        }
    }

    private Corner getGoingUpLeftCorner(int x, int y){
        Corner corner = Corner.NOTACORNER;
        if (x == boardSize - 2 && y == 0 ){
            corner = Corner.TOPLEFT;
        }
        return corner;
    }

    private Edge getGoingUpLeftEdge(int x, int y){
        Edge edge = Edge.NOTANEDGE;
        if (y == boardSize -2 - x && x < boardSize -2 ){
            edge = Edge.TOPLEFT;
        }
        return edge;
    }
    private void generateHexesGoingDownLeft(int x){
        for (int y = 0; y < boardSize - 1; y++) {
            Corner corner = getGoingDownLeftCorner(x, y);
            Edge edge = getGoingDownLeftEdge(x, y);
            final HexImpl hex = new HexImpl(-x - 1, y + 1, edge, corner);
            allHexes.add(hex);
        }
    }

    private Corner getGoingDownLeftCorner(int x, int y){
        Corner corner = Corner.NOTACORNER;
        if (x == boardSize - 2 && y == boardSize - 2 ){
            corner = Corner.BOTTOMLEFT;
        }
        return corner;
    }

    private Edge getGoingDownLeftEdge(int x, int y){
        Edge edge = Edge.NOTANEDGE;
        if (y == boardSize - 2 && x < boardSize -2){
            edge = Edge.BOTTOMLEFT;
        } else if (x == boardSize - 2 && y < boardSize - 2){
            edge = Edge.LEFT;
        }
        return edge;
    }

}

