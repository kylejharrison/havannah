package game.player;

import game.elements.HexValue;
import game.player.ai.CrowdingAiAIPlayer;
import game.player.ai.RandomAiAIPlayer;


/**
 * Types of player the game can be played as
 * Created by kyle on 2/23/15.
 */
public enum PlayerType {

    HUMAN,
    RANDOM,
    CROWDING;

    private HexValue hexValue;

    public HexValue getHexValue() {
        return hexValue;
    }

    public void setHexValue(HexValue hexValue) {
        this.hexValue = hexValue;
    }

    public Player getPlayer(){
        if (hexValue == null || !hexValue.isValidForPlayer()){
            throw new RuntimeException("HexValue is not valid to create a Player. Denied.");
        }
        Player player = null;
        switch (this){
            case HUMAN:
                player = new HumanPlayer(hexValue);
                break;
            case RANDOM:
                player = new RandomAiAIPlayer(hexValue);
                break;
            case CROWDING:
                player = new CrowdingAiAIPlayer(hexValue);
                break;
        }
        return player;
    }
}
