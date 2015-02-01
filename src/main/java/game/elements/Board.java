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
    public static Double hexWidth = 40.0;
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
        drawPlayingBoard();
        createCanvas();

//        final HexButton testButton2 = new HexButton(Color.WHITE);
//        final HexButton testButton3 = new HexButton(Color. BLUE);

//        this.add(testButton2);
//        this.add(testButton3);




//        testButton2.setPreferredSize(tileSize);
//        testButton3.setPreferredSize(tileSize);

//        testButton2.setBounds(insets.left, (int) (Math.sin(Math.toRadians(60.0)) * 40.0) + insets.top, tileSize.width, tileSize.height);
//        testButton3.setBounds((int) (insets.left + 20.0 + oneAcross), (int) (insets.top + hexHeight), tileSize.width, tileSize.height);

//        testButton2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                System.out.println("clicked2");
//                System.out.println(insets.top);
//                System.out.println(insets.bottom);
//            }
//        });
//        testButton3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                System.out.println("Clicked 3");
//            }
//        });
    }
    private void drawPlayingBoard(){
        for (int j = 0; j < 1; j++) {
            for (int i = 0; i < boardSize; i++) {
                final HexButton tile = new HexButton(Color.WHITE);
                this.add(tile);
                tile.setPreferredSize(tileSize);
                tile.setBounds((int) (insets.left + (boardWidth / 2) - hexWidth / 2),
                        (int) (insets.top + boardHeight / 2 - hexHeight / 2 - hexHeight * i),
                        tileSize.width, tileSize.height);
                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.out.println(insets.left);
                    }
                });
            }
            for (int i = 0; i < boardSize -1; i++) {
                final HexButton tile = new HexButton(Color.BLUE);
                this.add(tile);
                tile.setPreferredSize(tileSize);
                tile.setBounds((int) (insets.left + (boardWidth / 2) - hexWidth /2),
                        (int) (insets.top + boardHeight / 2 - hexHeight /2 + hexHeight + hexHeight * i),
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
