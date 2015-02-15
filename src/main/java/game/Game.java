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

    private final int boardSize;
    private int invalidMoves = 0;
    private Set<HexImpl> gameHexes;

    private final List<Player> allPlayers = new ArrayList<>();
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
        Set<Hex> clonedGameHexes = new HashSet<>();
        for (Hex hex: gameHexes){
            HexImpl newHex = new HexImpl(hex.getXAxis(),hex.getYAxis(),hex.getEdge(),hex.getCorner());
            newHex.setHexValue(hex.getHexValue());
            clonedGameHexes.add(newHex);
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
        Thread gameLoop = new Thread(new GameLoop());
        gameLoop.start();
    }

    private class GameLoop implements Runnable {

        @Override
        public void run() {
            currentPlayer = chooseFirstPlayer((ArrayList<Player>) allPlayers);
            LOG.info(String.format("Starting Player is: %s", currentPlayer.getPlayerHexValue()));
            LOG.info("Starting Game! It's all happening.");
            while (!gameState.isGameOver()) {
                if (currentPlayer.isHuman()) {
                    doHuman();
                } else {
                    doAI();
                }
            }
            LOG.info("Game Over");
        }


        private void doHuman() {
            LOG.info(String.format("Human turn. Player: %s", currentPlayer.getPlayerHexValue()));
            HexValue thisPlayerHexValue = currentPlayer.getPlayerHexValue();
            while (currentPlayer.getPlayerHexValue() == thisPlayerHexValue) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void doAI() {
            LOG.info(String.format("AI turn. Player: %s", currentPlayer.getPlayerHexValue()));
            AIPlayer thisPlayer = (AIPlayer) currentPlayer;
            Hex move = thisPlayer.move(getClonedGameHexes());
            HexImpl gameHex = null;
            for (HexImpl hex: gameHexes){
                if (hex.equals(move)){
                    gameHex = hex;
                    break;
                }
            }
            if (gameHex == null){
                throw new RuntimeException("Move is not a hex in the game!");
            }
            if (AbstractPlayer.isValidMove(gameHex)) {
                invalidMoves = 0;
                setGameState(new CheckGameStateImpl().getGameState(getClonedGameHexes(), move,
                thisPlayer.getPlayerHexValue()));
                gameHex.setHexValue(currentPlayer.getPlayerHexValue());
                switchCurrentPlayer();
            } else if (invalidMoves > 5) {
                throw new RuntimeException("AI player is being a dick and returned 5 invalid moves");
            } else {
                LOG.info(String.format("Player gave this move: %s, which has this value: %s", move.toString(), move.getHexValue()));
                invalidMoves++;
            }
        }

        private Player chooseFirstPlayer(ArrayList<Player> allPlayers) {
            LOG.info("Choosing First Player");
            int random = new Random().nextInt(allPlayers.size());
            return allPlayers.get(random);
        }
    }
    private void createGameWindow(){
        GameWindow gameWindow = new GameWindow();
        gameWindow.addGameElement(new Board(boardSize, gameHexes, this));
        gameWindow.showGameFrame();
    }

}