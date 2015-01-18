/**
 * Created by kyle on 1/18/15.
 * Runs the game n shit.
 */
public class GameRunner {
    private int boardSize;
    private int difficulty;
    private boolean againstCPU;

    public GameRunner(int boardSize, int difficulty, boolean againstCPU){
        this.boardSize = boardSize;
        this.difficulty = difficulty;
        this.againstCPU = againstCPU;
    }
    public void run(){
        Game game = new Game(boardSize, difficulty, againstCPU);
        Board board = new Board(allHexes);
        board.displayBoard();
    }
}
