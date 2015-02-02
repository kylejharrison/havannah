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
    private ArrayList<Hex> hexList;
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
    private void drawPlayingBoard(){
        final HexValue initialValue = HexValue.EMPTY;
        final Color initialColor = initialValue.getColor();
        //Draw tiles rightwards
        for (int x = 0; x < boardSize; x++) {
            //draw tiles going upwards
            for (int y = 0; y < boardSize; y++) {
                //Set Button layout stuff
                final HexButton tile = new HexButton(initialColor);
                add(tile);
                tile.setPreferredSize(tileSize);
                tile.setBounds((int) (insets.left + (boardWidth / 2) - hexWidth / 2 + x * hexWidth * 0.75),
                        (int) (insets.top + boardHeight / 2 - hexHeight / 2 - hexHeight * y + x * hexHeight /2),
                        tileSize.width, tileSize.height);
                //create the hex and add to the list
                final Hex hexForTile = new Hex(x, -y);
                hexForTile.setButton(tile);
//                this.hexList.add(hexForTile); TODO: doesn't work :(
                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        //TODO: if get current player is human + is valid move, set hex value
                        System.out.println(hexForTile.getHashCode());
                        hexForTile.setHexValue(HexValue.BLUE);//Example of setting color
                    }
                });
            }
            //draw tiles going downwards
            for (int y = 0; y < boardSize -1 -x; y++) {
                //Set Button layout stuff
                final HexButton tile = new HexButton(initialColor);
                this.add(tile);
                tile.setPreferredSize(tileSize);
                tile.setBounds((int) (insets.left + (boardWidth / 2) - hexWidth /2 + x * hexWidth * 0.75),
                        (int) (insets.top + boardHeight / 2 - hexHeight /2 + hexHeight + hexHeight * y + x * hexHeight /2),
                        tileSize.width, tileSize.height);
                //create the hex and add to the list
                final Hex hexForTile = new Hex(x, y +1);
//                this.hexList.add(hexForTile); TODO: doesn't work :(

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.out.println(hexForTile.getHashCode());
                    }
                });
            }
        }
        // draw tiles leftwards
        for (int x = 0; x < boardSize -1; x++) {
            //draw tiles upwards
            for (int y = 0; y < boardSize - 1 - x; y++) {
                final HexButton tile = new HexButton(Color.GREEN);
                this.add(tile);
                tile.setPreferredSize(tileSize);
                tile.setBounds((int) (insets.left + (boardWidth / 2) - hexWidth / 2 - hexWidth * 0.75 - x * hexWidth * 0.75),
                        (int) (insets.top + boardHeight / 2 - hexHeight - hexHeight * y - x * hexHeight /2),
                        tileSize.width, tileSize.height);
                final int finalI = y;
                final int finalJ = x;
                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.out.println(finalI);
                        System.out.println(finalJ);
                    }
                });
            }
            //draw tiles downwards
            for (int y = 0; y < boardSize -1; y++) {
                final HexButton tile = new HexButton(Color.RED);
                this.add(tile);
                tile.setPreferredSize(tileSize);
                tile.setBounds((int) (insets.left + (boardWidth / 2) - hexWidth / 2 - hexWidth * 0.75 - x * hexWidth * 0.75),
                        (int) (insets.top + boardHeight / 2 - hexHeight / 2 + hexHeight /2 + hexHeight * y - x * hexHeight /2),
                        tileSize.width, tileSize.height);
                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.out.println(insets.left);
                    }
                });
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
