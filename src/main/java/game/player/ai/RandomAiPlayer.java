package game.player.ai;

import game.elements.Hex;
import game.elements.HexValue;

import java.util.Random;
import java.util.Set;

/**
 * Randomly choose a move that is valid.
 * Created by steve on 29/01/15.
 */
public class RandomAiPlayer extends AbstractPlayer {

    public RandomAiPlayer(HexValue playerColour) {
        super(playerColour);
    }

    @Override
    public Hex move(Set<Hex> currentState) {
        Hex move = getRandomHex(currentState);
        while (!isValidMove(move)){
            move = getRandomHex(currentState);
        }
        return move;
    }

    private static Hex getRandomHex(Set<Hex> currentState) {
        int random = new Random().nextInt(currentState.size());
        int i = 0;
        for (Hex hex: currentState){
            if (i == random){
                return hex;
            }
            i++;
        }
        //return null if doesn't return a hex... although it always will. unless empty maybe.
        return null;
    }
}
