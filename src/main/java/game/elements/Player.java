package game.elements;

/**
 * Created by kyle on 1/19/15.
 * Holds details about the player
 */
public class Player {
    private String name;
    private String colour;
    private boolean human;

    public Player(String name, String colour, boolean human) {
        this.name = name;
        this.colour = colour;
        this.human = human;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public boolean isHuman(){
        return human;
    }

}