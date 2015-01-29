package game.player.ai;

import game.elements.Hex;
import game.elements.HexValue;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Randomly choose a move that is valid.
 * Created by steve on 29/01/15.
 */
public class RandomAiPlayer extends AbstractPlayer {

    public RandomAiPlayer(HexValue playerColour) {
        super(playerColour);
    }

    @Override
    public Hex move(List<Hex> currentState) {
        Hex move = currentState.get(getRandom(currentState));
        while (!isValidMove(move)){
            move = currentState.get(getRandom(currentState));
        }
        return move;
    }

    private static int getRandom(List<Hex> currentState) {
        return new Random().nextInt(currentState.size());
    }
}
