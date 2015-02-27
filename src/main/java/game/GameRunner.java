package game;

import game.player.PlayerType;

import java.util.ArrayList;
import java.util.Arrays;

import static game.player.PlayerType.*;

/**
 * Runs a headless game for AIs to compete!
 * Created by kyle on 2/27/15.
 */
public class GameRunner {
    /**
     * Main
     *
     * @param args int Boardsize (>1), String[] Player Types
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Requires at Least 2 arguments");
        }
        int boardSize;
        try {
            boardSize = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("First Argument must be an Integer for boardSize");
        }
        if (boardSize < 2){
            throw new IllegalArgumentException("BoardSize must be at least 2");
        }

        ArrayList<String> availablePlayerTypes = new ArrayList<>();
        for (PlayerOption playerOption : PlayerOption.values()) {
            if (!playerOption.equals(PlayerOption.HUMAN)) {
                availablePlayerTypes.add(playerOption.toString());
            }
        }
        ArrayList<String> players = new ArrayList<>();
        players.addAll(Arrays.asList(args).subList(1, args.length));
        if(!players.stream().allMatch(availablePlayerTypes::contains)){
            throw new IllegalArgumentException("All Player Types not available");
        }
    }
}
