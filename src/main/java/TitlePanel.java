import javax.swing.*;
import java.awt.*;

/**
 * Created by kyle on 1/18/15.
 * TitlePanel panel for MainMenu
 */
public class TitlePanel extends JLabel{

    public TitlePanel(String title){
        this.setText(title);
        this.setFont(new Font("Serif", Font.PLAIN, 24));
    }


}
