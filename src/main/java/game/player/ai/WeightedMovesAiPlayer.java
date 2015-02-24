package game.player.ai;

import game.CheckGameState;
import game.CheckGameStateImpl;
import game.elements.Hex;
import game.elements.HexValue;
import game.player.AIPlayer;
import game.player.AbstractPlayer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * AI that uses weights for each move position.
 * Also does a 1 ply depth search to see if any wins are available and if so, takes it.
 * Created by steve on 29/01/15.
 */
public class WeightedMovesAiPlayer extends AbstractPlayer implements AIPlayer {

    private static final double CORNER_WEIGHT = 1.1d;
    private static final double EDGE_WEIGHT = 1.05d;
    private static final double OTHER_PLAYER_WEIGHT = 0.9d;
    private static final double CONNECTION_WEIGHT = 1.5d;
    private static final double MY_WIN_WEIGHT = 1000d;

    public WeightedMovesAiPlayer(HexValue playerColour) {
        super(playerColour);
    }

    @Override
    public Hex move(Set<Hex> currentState) {
        Set<Hex> possibleMoves = currentState.stream().filter(h -> h.getHexValue().equals(HexValue.EMPTY)).collect(Collectors.toSet());
        Optional<Hex> winningMove = getWinningMove(currentState, possibleMoves);
        return winningMove.isPresent() ? winningMove.get() : getBestRankedMove(currentState, possibleMoves);
    }

    private Optional<Hex> getWinningMove(Set<Hex> currentState, Set<Hex> possibleMoves) {
        Optional<Hex> myWinningMove = getMyWinningMove(currentState, possibleMoves);
        if (myWinningMove.isPresent()) {
            return myWinningMove;
        }
        Optional<Hex> oppWinningMove = getOppWinningMove(currentState, possibleMoves);
        if (oppWinningMove.isPresent()) {
            return oppWinningMove;
        }
        return Optional.empty();
    }

    private Optional<Hex> getMyWinningMove(Set<Hex> currentState, Set<Hex> possibleMoves) {
        CheckGameState gameState = new CheckGameStateImpl();
        for (Hex move : possibleMoves) {
            if (gameState.getGameState(currentState, move, getPlayerHexValue()).isGameOver()) {
                return Optional.of(move);
            }
        }
        return Optional.empty();
    }

    private Optional<Hex> getOppWinningMove(Set<Hex> currentState, Set<Hex> possibleMoves) {
        CheckGameState gameState = new CheckGameStateImpl();
        for (Hex move : possibleMoves) {
            for (HexValue value : HexValue.values()) {
                if (!value.equals(HexValue.EMPTY) && gameState.getGameState(currentState, move, value).isGameOver()) {
                    return Optional.of(move);
                }
            }
        }
        return Optional.empty();
    }

    private Hex getBestRankedMove(Set<Hex> currentState, Set<Hex> possibleMoves) {
        TreeMap<Double, Hex> rankedMoves = new TreeMap<>();
        for (Hex move : possibleMoves) {
            rankedMoves.put(score(move, currentState), move);
        }
        return rankedMoves.lastEntry().getValue();
    }

    /**
     * Score the move based on weights.
     *
     * @param move
     * @param state
     * @return
     */
    private double score(Hex move, Set<Hex> state) {
        double score = 0d;
        score += weightCheck(move.getCorner().isACorner(), CORNER_WEIGHT);
        score += weightCheck(move.getEdge().isAnEdge(), EDGE_WEIGHT);
        score += nextToWeights(move, state);
        score += proceedsWin(move, state);
        return score;
    }

    private double proceedsWin(Hex move, Set<Hex> state) {
        Set<Hex> set = new HashSet<>(state);
        set.add(move);
        Set<Hex> possible = set.stream().filter(h -> h.getHexValue().equals(HexValue.EMPTY) && h.isNextTo(move)).collect(Collectors.toSet());
        Optional<Hex> myWinningMove = getMyWinningMove(state,possible);
        return myWinningMove.isPresent() ? MY_WIN_WEIGHT : 0d;
    }

    private double nextToWeights(Hex move, Set<Hex> state) {
        Map<HexValue, List<Hex>> nextTo = state.stream().filter(h -> !h.getHexValue().equals(HexValue.EMPTY) && h.isNextTo(move))
                .collect(Collectors.groupingBy(Hex::getHexValue));
        double value = 0d;
        for (Map.Entry<HexValue, List<Hex>> entry : nextTo.entrySet()) {
            for (Hex hex : entry.getValue()) {
                //this should reduce clumping into a big group
                value += CONNECTION_WEIGHT / (entry.getValue().stream().filter(h -> h.isNextTo(hex)).count() + 1);
            }
            value = getPlayerHexValue().equals(entry.getKey()) ? value : value * OTHER_PLAYER_WEIGHT;
        }
        return value;
    }

    private double weightCheck(boolean check, double value) {
        return check ? value : 0d;
    }
}
