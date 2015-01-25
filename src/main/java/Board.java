import ui.HexBorder;
import ui.HexButton;
import ui.Title;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kyle on 1/18/15.
 * Will draw and update the game board panel
 */
public class Board extends JPanel{
    private int boardSize;
    private Polygon simpleTest;

    public Board (int boardSize){
        this.boardSize = boardSize;

        Title test = new Title("test");
        final HexButton testButton = new HexButton(Color.BLACK);
        final Border hex, hexBlue;
        Border empty;
        empty = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        hex = BorderFactory.createCompoundBorder(empty, new ui.HexBorder(Color.BLACK));
        hexBlue = BorderFactory.createCompoundBorder(empty, new HexBorder(Color.BLUE));
        testButton.setBorderPainted(true);
        testButton.setFocusPainted(false);
        testButton.setBorder(hex);
        testButton.setOpaque(true);
        this.add(testButton);
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                testButton.setBorder(hexBlue);
            }
        });
//        this.add(simpleTest);
    }

}
