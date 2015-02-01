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
        //Draw tiles rightwards
        for (int j = 0; j < boardSize; j++) {
            //draw tiles going upwards
            for (int i = 0; i < boardSize; i++) {
                final HexButton tile = new HexButton(Color.WHITE);
                this.add(tile);
                tile.setPreferredSize(tileSize);
                tile.setBounds((int) (insets.left + (boardWidth / 2) - hexWidth / 2 + j * hexWidth * 0.75),
                        (int) (insets.top + boardHeight / 2 - hexHeight / 2 - hexHeight * i + j * hexHeight /2),
                        tileSize.width, tileSize.height);
                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.out.println(insets.left);
                    }
                });
            }
            //draw tiles going downwards
            for (int i = 0; i < boardSize -1 -j; i++) {
                final HexButton tile = new HexButton(Color.BLUE);
                this.add(tile);
                tile.setPreferredSize(tileSize);
                tile.setBounds((int) (insets.left + (boardWidth / 2) - hexWidth /2 + j * hexWidth * 0.75),
                        (int) (insets.top + boardHeight / 2 - hexHeight /2 + hexHeight + hexHeight * i + j * hexHeight /2),
                        tileSize.width, tileSize.height);
                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.out.println(insets.left);
                    }
                });
            }
        }
        // draw tiles leftwards
        for (int j = 0; j < boardSize -1; j++) {
            //draw tiles upwards
            for (int i = 0; i < boardSize - 1 - j; i++) {
                final HexButton tile = new HexButton(Color.GREEN);
                this.add(tile);
                tile.setPreferredSize(tileSize);
                tile.setBounds((int) (insets.left + (boardWidth / 2) - hexWidth / 2 - hexWidth * 0.75 - j * hexWidth * 0.75),
                        (int) (insets.top + boardHeight / 2 - hexHeight - hexHeight * i - j * hexHeight /2),
                        tileSize.width, tileSize.height);
                final int finalI = i;
                final int finalJ = j;
                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.out.println(finalI);
                        System.out.println(finalJ);
                    }
                });
            }
            //draw tiles downwards
            for (int i = 0; i < boardSize -1; i++) {
                final HexButton tile = new HexButton(Color.RED);
                this.add(tile);
                tile.setPreferredSize(tileSize);
                tile.setBounds((int) (insets.left + (boardWidth / 2) - hexWidth / 2 - hexWidth * 0.75 - j * hexWidth * 0.75),
                        (int) (insets.top + boardHeight / 2 - hexHeight / 2 + hexHeight /2 + hexHeight * i - j * hexHeight /2),
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
