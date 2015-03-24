package game;

import game.elements.HexValue;
import game.player.Player;
import game.player.PlayerType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract Game Runner to share shit
 * Created by kyle on 2/28/15.
 */
abstract class AbstractGameRunner implements GameRunner{
    private final int numberOfGames;
    private final int boardSize;
    private final ArrayList<PlayerType.PlayerOption> players;
    private HashMap<Player,Integer> resultMap = new HashMap<>();
    private Map<Player, Integer> playerResults;
    private boolean gamesFinished = false;

    public AbstractGameRunner(int numberOfGames, int boardSize, ArrayList<PlayerType.PlayerOption> players) {
        this.numberOfGames = numberOfGames;
        this.boardSize = boardSize;
        this.players = players;
        initialiseResultMap();
    }

    private void initialiseResultMap() {
        ArrayList<HexValue> availableHexes = new ArrayList<>();
        for (HexValue hexValue: HexValue.values()){
            if(hexValue.isValidForPlayer()){
                availableHexes.add(hexValue);
            }
        }
        for(PlayerType.PlayerOption playerOption: players){
            PlayerType playerType = new PlayerType(playerOption);
            playerType.setHexValue(availableHexes.remove(0));
            resultMap.put(playerType.getPlayer(),0);
        }
    }


}
