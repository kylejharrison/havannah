import UiElements.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kyle on 1/18/15.
 * Will draw and update the game board panel
 */
public class Board extends JPanel{
    private ArrayList<Hex> allHexes;

    public Board (ArrayList<Hex> allHexes){
        this.allHexes = allHexes;
    }
    public void displayBoard(){

    }
}
