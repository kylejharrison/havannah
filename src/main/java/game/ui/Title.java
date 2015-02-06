package game.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kyle on 1/18/15.
 * game.ui.Title panel for game.elements.MainMenu
 */
public class Title extends JLabel{

    public Title(String title){
        this.setText(title);
        this.setFont(new Font("Serif", Font.PLAIN, 24));
    }


}
