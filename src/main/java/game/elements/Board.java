package game.elements;

import ui.HexButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by kyle on 1/18/15.
 * Will draw and update the game board panel
 */
public class Board extends JPanel{
    private int boardSize;
    public static Double hexWidth = 40.0; //TODO: make this optional size (or dynamic based on screen res and boardsize)
    public static Double hexHeight = (double) Math.round(Math.sin(Math.toRadians(60.0)) * hexWidth);
    private Insets insets = getInsets(); //TODO: does this even do anything?
    private ArrayList<Hex> hexList = new ArrayList<Hex>();
    private int boardWidth;
    private int boardHeight;
    private Dimension tileSize = new Dimension((int) (1 * hexWidth) + 1, (int) (hexHeight + 1)); //TODO: make hexHeight round properly

    public Board (int boardSize){
        this.boardSize = boardSize;
        this.boardWidth = (int) ((boardSize * 2 * hexWidth) + insets.top + insets.bottom);
        this.boardHeight = (int) ((boardSize * 2 * hexHeight) + insets.top + insets.bottom);
        createCanvas();
        drawPlayingBoard();
    }
    private HexButton createTile(){
        //Set Button colour and size
        final HexValue initialValue = HexValue.EMPTY;
        final Color initialColor = initialValue.getColor();
        final HexButton tile = new HexButton(initialColor);
        add(tile);
        tile.setPreferredSize(tileSize);
        return tile;
    }
    private Hex createHex(int x, int y, HexButton tile){
        //create the Hex for game and assign to a tile
        final Hex hexForTile = new Hex(x, y, tile);
        hexList.add(hexForTile);
        return hexForTile;
    }
    private void addActionListenerToTile(HexButton tile, final Hex hexForTile){
        tile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO: if get current player is human + is valid move, set hex value
                System.out.println(hexForTile.getHashCode());
                hexForTile.setHexValue(HexValue.BLUE);//Example of setting color
            }
        });
    }
    private void drawPlayingBoard(){
        final int centreOfBoardHorizontally = (int) (insets.left + (boardWidth / 2) - hexWidth / 2);
        final int horizontalShift = (int) (hexWidth * 0.75);
        final int centreOfBoardVertically = (int) (insets.top + boardHeight / 2 - hexHeight / 2);
        final int verticalShift = (int) (hexHeight / 2);
        //Draw tiles rightwards
        for (int x = 0; x < boardSize; x++) {
            //draw tiles going upwards
            for (int y = 0; y < boardSize; y++) {
                // create tile and set bounds
                final HexButton tile = createTile();
                tile.setBounds(centreOfBoardHorizontally + x * horizontalShift,
                        (int) (centreOfBoardVertically - hexHeight * y + x * verticalShift),
                        tileSize.width, tileSize.height);
                //create the hex and add to the list
                final Hex hexForTile = createHex(x, -y, tile);
                // Finally join the hex to the tile and all is swell
                addActionListenerToTile(tile, hexForTile);
            }
            //draw tiles going downwards
            for (int y = 0; y < boardSize -1 -x; y++) {
                // create tile and set bounds
                final HexButton tile = createTile();
                tile.setBounds(centreOfBoardHorizontally + x * horizontalShift,
                        (int) (centreOfBoardVertically + hexHeight + hexHeight * y + x * verticalShift),
                        tileSize.width, tileSize.height);
                //create the hex and add to the list
                final Hex hexForTile = createHex(x, y + 1, tile);
                // Finally join the hex to the tile and all is swell
                addActionListenerToTile(tile, hexForTile);
            }
        }
        // draw tiles leftwards
        for (int x = 0; x < boardSize -1; x++) {
            //draw tiles upwards
            for (int y = 0; y < boardSize - 1 - x; y++) {
                // create tile and set bounds
                final HexButton tile = createTile();
                tile.setBounds(centreOfBoardHorizontally - horizontalShift - x * horizontalShift,
                        (int) (centreOfBoardVertically - verticalShift  - hexHeight * y - x * verticalShift),
                        tileSize.width, tileSize.height);
                //create the hex and add to the list
                final Hex hexForTile = createHex(-x - 1, -y, tile);
                // Finally join the hex to the tile and all is swell
                addActionListenerToTile(tile, hexForTile);
            }
            //draw tiles downwards
            for (int y = 0; y < boardSize -1; y++) {
                // create tile and set bounds
                final HexButton tile = createTile();
                tile.setBounds(centreOfBoardHorizontally - horizontalShift - x * horizontalShift,
                        (int) (centreOfBoardVertically + verticalShift + hexHeight * y - x * verticalShift),
                        tileSize.width, tileSize.height);
                //create the hex and add to the list
                final Hex hexForTile = createHex(-x -1, y + 1, tile);
                // Finally join the hex to the tile and all is swell
                addActionListenerToTile(tile, hexForTile);
            }
        }
    }
    private void createCanvas(){
        setLayout(null);
        setSize(boardWidth, boardHeight);
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public ArrayList<Hex> getHexList() {
        return hexList;
    }
}
