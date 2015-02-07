package game.elements;

import game.ui.HexButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 * Created by kyle on 1/18/15.
 * Will draw and update the game board panel
 */
public class Board extends JPanel{
    public static Double hexWidth = 40.0; //TODO: make this optional size (or dynamic based on screen res and boardSize)
    public static Double hexHeight = (double) Math.round(Math.sin(Math.toRadians(60.0)) * hexWidth);
    private Insets insets = getInsets(); //TODO: does this even do anything?
    private int boardWidth;
    private int boardHeight;
    private Dimension tileSize = new Dimension((int) (1 * hexWidth) + 1, (int) (hexHeight + 1)); //TODO: make hexHeight round properly

    public Board (int boardSize, Set<HexImpl> allHexes){
        this.boardWidth = (int) ((boardSize * 2 * hexWidth) + insets.top + insets.bottom);
        this.boardHeight = (int) ((boardSize * 2 * hexHeight) + insets.top + insets.bottom);
        createCanvas();
        drawPlayingBoardWithHexes(allHexes);
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

    private void addActionListenerToTile(HexButton tile, final HexImpl hexForTile){
        hexForTile.setButton(tile);
        tile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO: if get current player is human + is valid move, set hex value
                System.out.println(hexForTile.hashCode());
                hexForTile.setHexValue(HexValue.BLUE);//Example of setting color
            }
        });
    }
    private void drawPlayingBoardWithHexes(Set<HexImpl> allHexes){
        final int centreOfBoardHorizontally = (int) (insets.left + (boardWidth / 2) - hexWidth / 2);
        final int horizontalShift = (int) (hexWidth * 0.75);
        final int centreOfBoardVertically = (int) (insets.top + boardHeight / 2 - hexHeight / 2);
        final int verticalShift = (int) (hexHeight / 2);
        for (HexImpl hex: allHexes){
            final HexButton tile = createTile();
            tile.setBounds(centreOfBoardHorizontally + horizontalShift * hex.getXAxis(),
                    (int) (centreOfBoardVertically + verticalShift * hex.getXAxis() + hexHeight * hex.getYAxis()),
                    tileSize.width, tileSize.height);
            addActionListenerToTile(tile, hex);
        }
    }

    private void createCanvas(){
        setLayout(null);
        setSize(boardWidth, boardHeight);
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

}
