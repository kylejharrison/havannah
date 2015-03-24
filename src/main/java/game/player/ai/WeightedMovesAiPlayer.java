package game.player.ai;

import game.AIPlayerAlgos;
import game.Game;
import game.elements.Hex;
import game.elements.HexImpl;
import game.elements.HexValue;
import game.player.AIPlayer;
import game.player.AbstractPlayer;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * AI that uses weights for each move position.
 * Also does a 1 ply depth search to see if any wins are available and if so, takes it.
 * Created by steve on 29/01/15.
 */
public class WeightedMovesAiPlayer extends AbstractPlayer implements AIPlayer {

    private static final Logger LOG = Logger.getLogger(WeightedMovesAiPlayer.class.getName());

    private static final double CORNER_WEIGHT = 1.1d;
    private static final double EDGE_WEIGHT = 1.05d;
    private static final double OTHER_PLAYER_WEIGHT = 0.9d;
    private static final double CONNECTION_WEIGHT = 1.5d;
    private static final double WIN_WEIGHT = 1000d;

    public WeightedMovesAiPlayer(HexValue playerColour) {
        super(playerColour);
    }

    @Override
    public Hex move(Set<Hex> state) {
        Set<Hex> possibleMoves = state.stream().filter(h -> h.getHexValue().equals(HexValue.EMPTY)).collect(Collectors.toSet());
        Optional<Hex> winningMove = AIPlayerAlgos.getWinningMove(this, state, possibleMoves);
        return winningMove.isPresent() ? winningMove.get() : bestRankedMove(state, possibleMoves);
    }

    private Hex bestRankedMove(Set<Hex> state, Set<Hex> possibleMoves) {
        TreeMap<Double, Hex> rankedMoves = getRankedMove(state, possibleMoves, getPlayerHexValue());
        //TODO: get get the top 5-10% of moves  (by total weight) and see then evaluate the move after
        //Double totalWeights = rankedMoves.keySet().stream().reduce(0d, Double::sum);
        return rankedMoves.lastEntry().getValue();
    }

    private static TreeMap<Double, Hex> getRankedMove(Set<Hex> state, Set<Hex> possibleMoves, HexValue player) {
        TreeMap<Double, Hex> rankedMoves = new TreeMap<>();
        for (Hex move : possibleMoves) {
            rankedMoves.put(score(move, state, player), move);
        }
        return rankedMoves;
    }

    /**
     * Score the move based on weights.
     *
     * @param move
     * @param player
     * @return
     */
    private static double score(Hex move, Set<Hex> state, HexValue player) {
        double score = 0d;
        score += weightCheck(move.getCorner().isACorner(), CORNER_WEIGHT);
        score += weightCheck(move.getEdge().isAnEdge(), EDGE_WEIGHT);
        score += nextToWeights(move, state, player);
        score += proceedsWin(move, state, player);
        return score;
    }

    private static double proceedsWin(Hex move, Set<Hex> state, HexValue player) {
        Set<Hex> fakeState = new HashSet<>(state);
        HexImpl fakeMove = new HexImpl(move.getXAxis(), move.getYAxis(), move.getEdge(), move.getCorner());
        fakeMove.setHexValue(player);
        fakeState.remove(move);
        fakeState.add(fakeMove);
        Set<Hex> possible = fakeState.stream().filter(h -> h.getHexValue().equals(HexValue.EMPTY) && h.isNextTo(move)).collect(Collectors.toSet());
        Optional<Hex> winningMove = AIPlayerAlgos.winningMoveForPlayers(fakeState, possible, Collections.singleton(player));
        return winningMove.isPresent() ? WIN_WEIGHT : 0d;
    }

    private static double nextToWeights(Hex move, Set<Hex> state, HexValue player) {
        Map<HexValue, List<Hex>> nextTo = state.stream().filter(h -> !h.getHexValue().equals(HexValue.EMPTY) && h.isNextTo(move))
                .collect(Collectors.groupingBy(Hex::getHexValue));
        double value = 0d;
        for (Map.Entry<HexValue, List<Hex>> entry : nextTo.entrySet()) {
            for (Hex hex : entry.getValue()) {
                //this should reduce clumping into a big group
                value += CONNECTION_WEIGHT / (entry.getValue().stream().filter(h -> h.isNextTo(hex)).count() + 1);
            }
            value = player.equals(entry.getKey()) ? value : value * OTHER_PLAYER_WEIGHT;
        }
        return value;
    }

    private static double weightCheck(boolean check, double value) {
        return check ? value : 0d;
    }
}
