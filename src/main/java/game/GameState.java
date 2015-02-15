package game;

/**
 * Describes the possible values of a Game Over condition
 * Created by kyle on 2/8/15.
 */
public enum GameState {
    WINNER(true),
    DRAW(true),
    STILLTOPLAYFOR(false);

    private final boolean gameOver;

    GameState(boolean gameOver){
        this.gameOver = gameOver;
    }

    public boolean isGameOver(){
        return gameOver;
    }
}
