package game.elements;

import ui.HexButton;
import ui.Title;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static game.elements.Hex.*;

/**
 * Created by kyle on 1/18/15.
 * Will draw and update the game board panel
 */
public class Board extends JPanel{
    private int boardSize;
    public static Double hexSize = 20.0;
    private Insets insets = getInsets();

    public Board (int boardSize){
        this.boardSize = boardSize;
        createCanvas();
        final HexButton testButton = new HexButton(Color.WHITE);
        final HexButton testButton2 = new HexButton(Color.WHITE);
        final HexButton testButton3 = new HexButton(Color. BLUE);
        this.add(testButton);
        this.add(testButton2);
        this.add(testButton3);



        Dimension size = new Dimension((int) Math.round(40 + (Math.cos(Math.toRadians(60.0)) * 40.0)), (int) ( Math.round(Math.sin(Math.toRadians(60.0))) * 40.0));
        testButton.setPreferredSize(size);
        testButton2.setPreferredSize(size);
        testButton3.setPreferredSize(size);
        testButton.setBounds(insets.left, insets.top, size.width, size.height);
        testButton2.setBounds(insets.left, (int) (Math.sin(Math.toRadians(60.0)) * 40.0) + insets.top, size.width, size.height);
        testButton3.setBounds((int) (insets.left + 20.0 + oneAcross), (int) (insets.top + oneUp), size.width, size.height);
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(Math.sin(Math.toRadians(60.0)) * 20.0 );
            }
        });
        testButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("clicked2");
            }
        });
        testButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Clicked 3");
            }
        });
    }
    private void createCanvas(){
        setLayout(null);
        setSize((int) ((boardSize * hexSize) - ((boardSize - 1) * hexSize) + insets.left + insets.right + 10),
                (int) (((boardSize + 1) * oneUp * 2) + insets.top + insets.bottom + 10));

    }

}
