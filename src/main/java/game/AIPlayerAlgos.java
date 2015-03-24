package game;

import game.elements.Hex;
import game.elements.HexValue;
import game.player.Player;
import game.player.ai.WeightedMovesAiPlayer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Common Algos AI players can use to WIN.
 * Created by kyle on 3/10/15.
 */
public class AIPlayerAlgos {
    public static Optional<Hex> getWinningMove(Player player, Set<Hex> state, Set<Hex> possibleMoves) {
        Optional<Hex> myWinningMove = winningMoveForPlayers(state, possibleMoves, Collections.singleton(player.getPlayerHexValue()));
        if (myWinningMove.isPresent()) {
            return myWinningMove;
        }
        Optional<Hex> oppWinningMove = winningMoveForPlayers(state, possibleMoves, AIPlayerAlgos.opponents(player));
        if (oppWinningMove.isPresent()) {
            return oppWinningMove;
        }
        return Optional.empty();
    }

    public static Optional<Hex> winningMoveForPlayers(Set<Hex> state, Set<Hex> possibleMoves, Set<HexValue> players) {
        Set<Hex> myMoves = state.stream().filter(h -> players.contains(h.getHexValue())).collect(Collectors.toSet());
        CheckGameState gameState = new CheckGameStateImpl();
        for (Hex move : possibleMoves) {
            for (HexValue value : players) {
                if (myMoves.stream().filter(h -> h.isNextTo(move)).count() > 0 && gameState.getGameState(state, move, value).isGameOver()) {
                    return Optional.of(move);
                }
            }
        }
        return Optional.empty();
    }

    private static Set<HexValue> opponents(Player player) {
        //TODO need to get this from the game loop
        Set<HexValue> opponents = new HashSet<>(Arrays.asList(HexValue.values()));
        opponents.remove(player.getPlayerHexValue());
        opponents.remove(HexValue.EMPTY);
        return opponents;
    }
}
