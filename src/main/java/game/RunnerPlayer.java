package game;

import game.player.PlayerType;

/**
* Created by kyle on 3/1/15.
*/
public class RunnerPlayer {
    private final PlayerType playerType;
    private int wins = 0;

    public RunnerPlayer(PlayerType playerType) {
        this.playerType = playerType;
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        this.wins++;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    @Override
    public String toString() {
        return String.format("Player: %s, Wins: %s",playerType.getPlayerOption().toString(),wins);
    }
}
