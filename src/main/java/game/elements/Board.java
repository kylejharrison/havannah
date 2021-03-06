package game.elements;

import game.CheckGameStateImpl;
import game.Game;
import game.player.AbstractPlayer;
import game.ui.HexButton;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by kyle on 1/18/15.
 * Will draw and update the game board panel
 */
public class Board extends JPanel{
    private static final Logger LOG = Logger.getLogger(Board.class.getName());
    public static final Double hexWidth = 40.0; //TODO: make this optional size (or dynamic based on screen res and boardSize)
    public static final Double hexHeight = (double) Math.round(Math.sin(Math.toRadians(60.0)) * hexWidth);
    private final Insets insets = getInsets(); //TODO: does this even do anything?
    private final int boardWidth;
    private final int boardHeight;
    private final Dimension tileSize = new Dimension((int) (1 * hexWidth) + 1, (int) (hexHeight + 1)); //TODO: make hexHeight round properly
    private final Game game;

    public Board(int boardSize, Set<HexImpl> allHexes, Game game){
        this.boardWidth = (int) ((boardSize * 2 * hexWidth) + insets.top + insets.bottom);
        this.boardHeight = (int) ((boardSize * 2 * hexHeight) + insets.top + insets.bottom);
        this.game = game;
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
        tile.addActionListener(actionEvent -> {
            LOG.info(String.format("Ooh, you touched me! Hex:%s", hexForTile.toString()));
            hexIsTouched(hexForTile);
        });
    }
    private void hexIsTouched(HexImpl hex){
        if(AbstractPlayer.isValidMove(hex) && game.getCurrentPlayer().isHuman() && !game.getGameState().isGameOver()){
            HexValue currentPlayerHexValue = game.getCurrentPlayer().getPlayerHexValue();
            Set<Hex> currentGameHexes = game.getClonedGameHexes();
            hex.setHexValue(currentPlayerHexValue);
            game.setGameState(new CheckGameStateImpl().getGameState(currentGameHexes, hex, currentPlayerHexValue));
            game.switchCurrentPlayer();
        }else{
            LOG.info("Not a valid move, stop touching me!");
        }
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
