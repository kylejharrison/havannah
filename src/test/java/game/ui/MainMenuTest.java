package game.ui;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.uispec4j.UISpec4J;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.MainClassAdapter;

public class MainMenuTest extends UISpecTestCase {

    static {
        UISpec4J.init();
    }

    @BeforeClass
    public void setUp() throws Exception{
        setAdapter(new MainClassAdapter(MainMenu.class, new String[0]));
    }

    @Test(enabled = false)
    public void testButtonCanBeClicked() throws Exception{
        Window window = getMainWindow();
        org.uispec4j.Button button = window.getButton("Start New Game");
        assertTrue(button.isVisible());

    }
}