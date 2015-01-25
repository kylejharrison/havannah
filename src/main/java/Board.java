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
//        SpringLayout layout = new SpringLayout();
//        this.setLayout(layout);
        setLayout(null);
        Title test = new Title("test");
        final HexButton testButton = new HexButton(Color.WHITE);
        final HexButton testButton2 = new HexButton(Color.WHITE);
        this.add(testButton);
        this.add(testButton2);

        Insets insets = getInsets();
        Dimension size = new Dimension((int) (40 + (Math.cos(Math.toRadians(60.0)) * 40.0)), (int) (Math.sin(Math.toRadians(60.0)) * 40.0));
        testButton.setPreferredSize(size);
        testButton2.setPreferredSize(size);
        testButton.setBounds(insets.left, insets.top, size.width, size.height);
        testButton2.setBounds(insets.left, (int) (Math.sin(Math.toRadians(60.0)) * 40.0) + insets.top, size.width, size.height);

        setSize((int) ((boardSize * 40) - ((boardSize -1) * (Math.cos(Math.toRadians(60.0)) * 40.0)) + insets.left + insets.right),
                ((boardSize + 1) * (int) (Math.sin(Math.toRadians(60.0)) * 40.0)) + insets.top + insets.bottom);


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
//        this.add(simpleTest);
    }

}
