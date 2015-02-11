package game.player;

import game.elements.HexValue;
import game.player.ai.RandomAiAIPlayer;

/**
 * Human player- will be controlled by the Game
 * Created by kyle on 2/8/15.
 */
public class HumanPlayer implements Player{

    private final HexValue playerColour;

    public HumanPlayer(HexValue playerColour){
        RandomAiAIPlayer.validatePlayerColour(playerColour);
        this.playerColour = playerColour;
    }

    @Override
    public boolean isHuman() {
        return true;
    }

    @Override
    public HexValue getPlayerHexValue() {
        return playerColour;
    }
}
