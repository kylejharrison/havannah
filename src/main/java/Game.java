import java.util.ArrayList;

/**
 * Created by kyle on 1/18/15.
 * Controls all aspects of the game
 */
public class Game {
    private int boardSize;
    private int difficulty;
    private boolean againstCPU;
    private ArrayList<Hex> allHexes;

    public Game(int boardSize, int difficulty, boolean againstCPU){
        this.boardSize = boardSize;
        this.difficulty = difficulty;
        this.againstCPU = againstCPU;
        generateAllHexes();
    }

    public ArrayList<Hex> getAllHexes(){
        return this.allHexes;
    }
    public void startGame(){

    }

    private ArrayList<Hex> generateAllHexes(){

    }
    private int calculateMaxNumberOfHexes(){


    }
}
