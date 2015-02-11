package game;

import game.elements.Board;
import game.elements.Hex;
import game.elements.HexImpl;
import game.elements.HexValue;
import game.player.AIPlayer;
import game.player.AbstractPlayer;
import game.player.Player;
import game.ui.GameWindow;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by kyle on 1/18/15.
 * Controls and runs all aspects of the game
 */
public class Game {
    private static final Logger LOG = Logger.getLogger(Game.class.getName());

    private int boardSize;
    private int invalidMoves = 0;
    private Set<HexImpl> gameHexes;

    private List<Player> allPlayers = new ArrayList<Player>();
    private GameState gameState = GameState.STILLTOPLAYFOR;
    private Player currentPlayer;

    public Game(int boardSize, Player player1, Player player2) {
        this.boardSize = boardSize;
        this.allPlayers.add(player1);
        this.allPlayers.add(player2);
        validatePlayers((ArrayList<Player>) allPlayers);
        LOG.info("Setting Game Hexes...");
        setGameHexes();
        LOG.info("Game Hexes set");
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Set<Hex> getClonedGameHexes(){
        Set<Hex> clonedGameHexes = new HashSet<Hex>();
        for (Hex hex: gameHexes){
            clonedGameHexes.add(hex);
        }
        return clonedGameHexes;
    }

    public void switchCurrentPlayer() {
        if (allPlayers.get(0) == currentPlayer){
            currentPlayer = allPlayers.get(1);
        }else{
            currentPlayer = allPlayers.get(0);
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void validatePlayers(ArrayList<Player> allPlayers){
        if(allPlayers.get(0).getPlayerHexValue() == allPlayers.get(1).getPlayerHexValue()){
            throw new RuntimeException("You obviously can't play a game with two players using the same colour, you stupid cunt");
        }
    }
    private void setGameHexes(){
        this.gameHexes = new HexGenerator(boardSize).generateHexes();
    }

    public void launchGameWindow() {
        LOG.info("Creating Game Window");
        createGameWindow();
     }

    public void startGameLoop() throws InterruptedException {
        currentPlayer = chooseFirstPlayer((ArrayList<Player>) allPlayers);
        LOG.info(String.format("Starting Player is: %s",currentPlayer.getPlayerHexValue()));
        LOG.info("Starting Game! It's all happening.");
        while(!gameState.isGameOver()){
            if (currentPlayer.isHuman()){
                doHuman();
            }else{
                doAI();
            }
        }
        LOG.info("Game Over");
    }

    private void doHuman() throws InterruptedException {
        LOG.info(String.format("Human turn. Player: %s",currentPlayer.getPlayerHexValue()));
        HexValue thisPlayerHexValue = currentPlayer.getPlayerHexValue();
        while (currentPlayer.getPlayerHexValue() == thisPlayerHexValue){
            Thread.sleep(1000);
        }
    }

    private void doAI(){
        LOG.info(String.format("AI turn. Player: %s",currentPlayer.getPlayerHexValue()));
        AIPlayer thisPlayer = (AIPlayer) currentPlayer;
        Hex move = thisPlayer.move(getClonedGameHexes());
        if(AbstractPlayer.isValidMove(move)){
            invalidMoves = 0;
            setGameState(new CheckGameStateImpl().getGameState(getClonedGameHexes(),move,
                    thisPlayer.getPlayerHexValue()));
            for (HexImpl hex: gameHexes){
                if (hex == move){
                    hex.setHexValue(currentPlayer.getPlayerHexValue());
                    break;
                }
            }
            switchCurrentPlayer();
        }else if(invalidMoves > 5){
            throw new RuntimeException("AI player is being a dick and returned 5 invalid moves");
        }else{
            LOG.info(String.format("Player gave this move: %s, which has this value: %s",move.toString(),move.getHexValue()));
            invalidMoves++;
        }
    }
    private Player chooseFirstPlayer(ArrayList<Player> allPlayers){
        LOG.info("Choosing First Player");
        int random = new Random().nextInt(allPlayers.size());
        return allPlayers.get(random);
    }

    private void createGameWindow(){
        GameWindow gameWindow = new GameWindow();
        addBoard(gameWindow);
        gameWindow.showGameFrame();
    }

    private void addBoard(GameWindow gameWindow){
        Board board = new Board(boardSize, gameHexes, this);
        gameWindow.addGameElement(board);
    }

}