package game.player;

import game.elements.HexValue;
import game.player.ai.CrowdingAiAIPlayer;
import game.player.ai.RandomAiAIPlayer;
import game.player.ai.WeightedMovesAiPlayer;


/**
 * Types of player the game can be played as
 * Created by kyle on 2/23/15.
 */

public class PlayerType {

    public enum PlayerOption {
        HUMAN,
        RANDOM,
        CROWDING,
        WEIGHTED
    }

    private HexValue hexValue;
    private PlayerOption playerOption;

    public PlayerType(PlayerOption playerOption){
        this.playerOption = playerOption;
    }

    public PlayerOption getPlayerOption() {
        return playerOption;
    }
    public void setPlayerOption(PlayerOption playerOption) {
        this.playerOption = playerOption;
    }

    public HexValue getHexValue() {
        return hexValue;
    }

    public void setHexValue(HexValue hexValue) {
        this.hexValue = hexValue;
    }

    public Player getPlayer() {
        if (hexValue == null || !hexValue.isValidForPlayer()) {
            throw new RuntimeException("HexValue is not valid to create a Player. Denied.");
        }
        Player player = null;
        switch (playerOption) {
            case HUMAN:
                player = new HumanPlayer(hexValue);
                break;
            case RANDOM:
                player = new RandomAiAIPlayer(hexValue);
                break;
            case CROWDING:
                player = new CrowdingAiAIPlayer(hexValue);
                break;
            case WEIGHTED:
                player = new WeightedMovesAiPlayer(hexValue);
                break;
        }
        return player;
    }
}