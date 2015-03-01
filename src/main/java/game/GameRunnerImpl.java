package game;

import game.elements.HexValue;
import game.player.Player;
import game.player.PlayerType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

import static game.player.PlayerType.PlayerOption;
import static java.util.stream.Collectors.toCollection;

/**
 * Runs a headless game for AIs to compete!
 * Created by kyle on 2/27/15.
 */
public class GameRunnerImpl implements GameRunner{
    private static final Logger LOG = Logger.getLogger(GameRunnerImpl.class.getName());
    private final int numberOfGames;
    private final int boardSize;
    private final ArrayList<PlayerOption> players;
    private HashSet<RunnerPlayer> runnerPlayers = new HashSet<>();
    private boolean gamesFinished = false;
    private Map<Player,RunnerPlayer> gamePlayerMap = new HashMap<>();
    private int draws = 0;

    public GameRunnerImpl(int numberOfGames, int boardSize, ArrayList<PlayerOption> players) {
        this.numberOfGames = numberOfGames;
        this.boardSize = boardSize;
        this.players = players;
        initialiseRunnerPlayers();
    }

    private void initialiseRunnerPlayers() {
        ArrayList<HexValue> availableHexes = new ArrayList<>();
        for (HexValue hexValue: HexValue.values()){
            if(hexValue.isValidForPlayer()){
                availableHexes.add(hexValue);
            }
        }
        for(PlayerOption playerOption: players){
            if(availableHexes.isEmpty()){
                LOG.info("No more hex values, the other players will be ignored");
                break;
            }
            PlayerType playerType = new PlayerType(playerOption);
            playerType.setHexValue(availableHexes.remove(0));
            runnerPlayers.add(new RunnerPlayer(playerType));
        }
    }

    /**
     * Main
     * @param args int NumberOfGames (>0), int Boardsize (>1), String[] Player Types
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Requires at Least 3 arguments");
        }
        int numberOfGames;
        try {
            numberOfGames = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("First Argument must be an Integer for numberOfGames");
        }
        int boardSize;
        try {
            boardSize = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Second Argument must be an Integer for boardSize");
        }
        if (numberOfGames < 1){
            throw new IllegalArgumentException("NumberOfGames must be at least 1");
        }
        if (boardSize < 2){
            throw new IllegalArgumentException("BoardSize must be at least 2");
        }

        ArrayList<PlayerOption> availablePlayerTypes = new ArrayList<>();
        for (PlayerOption playerOption : PlayerOption.values()) {
            if (!playerOption.equals(PlayerOption.HUMAN)) {
                availablePlayerTypes.add(playerOption);
            }
        }
        ArrayList<PlayerOption> players = new ArrayList<>();
        for (int arg = 2; arg < args.length; arg++) {
            players.add(PlayerOption.valueOf(args[arg]));
        }
        if(!players.stream().allMatch(availablePlayerTypes::contains)){
            throw new IllegalArgumentException("All Player Types not available");
        }
        GameRunnerImpl gameRunner = new GameRunnerImpl(numberOfGames, boardSize, players);
        gameRunner.run();
    }

    @Override
    public void run() {
        HashSet<Game> games = getGames();
        HashSet<Thread> gameThreads = games.stream().map(Game::getGameLoop).collect(toCollection(HashSet::new));
        gameThreads.stream().forEach(Thread::start);
        waitForGamesToFinish(gameThreads);
        games.stream().forEach(game -> {
            Player winner = game.getWinner();
            if (winner == null) {
                LOG.info("Game was a draw");
                draws++;
            } else {
                gamePlayerMap.get(winner).incrementWins();
            }
        });
        LOG.info(String.format("Game Results: %s, draws: %s",runnerPlayers,draws));
        gamesFinished = true;
    }

    private void waitForGamesToFinish(HashSet<Thread> games) {
        while(games.stream().anyMatch(Thread::isAlive)){
            LOG.info("Games are still running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private HashSet<Game> getGames() {
        HashSet<Game> games = new HashSet<>();
        for (int i = 0; i < numberOfGames; i++) {
            ArrayList<Player> gamePlayers = new ArrayList<>();
            runnerPlayers.stream().forEach(runnerPlayer ->{
                Player player = runnerPlayer.getPlayerType().getPlayer();
                gamePlayerMap.put(player,runnerPlayer);
                gamePlayers.add(player);
            });
            Game game = new Game(boardSize, gamePlayers);
            games.add(game);

        }
        return games;
    }

    @Override
    public HashSet<RunnerPlayer> getPlayerResults() {
        return runnerPlayers;
    }

    @Override
    public int getDraws() {
        return draws;
    }

    @Override
    public boolean areGamesFinished() {
        return gamesFinished;
    }
}
