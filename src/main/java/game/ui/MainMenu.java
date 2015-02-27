package game.ui; /**
 * Created by kyle on 1/18/15.
 * Main Menu - main class for game
 */

import game.Game;
import game.elements.HexValue;
import game.player.Player;
import game.player.PlayerType;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.logging.Logger;

import static game.player.PlayerType.PlayerOption;

public class MainMenu extends JPanel{
    private static final Logger LOG = Logger.getLogger(MainMenu.class.getName());
    private Map<String,PlayerType> optionPlayers = new HashMap<>();
    private String currentOptionPlayer;
    private int boardSize = 8;

    public MainMenu(){
        setDefaultPlayers();
    }

    private void setDefaultPlayers() {
        PlayerType humanPlayer1 = new PlayerType(PlayerOption.HUMAN);
        humanPlayer1.setHexValue(HexValue.BLUE);
        PlayerType humanPlayer2 = new PlayerType(PlayerOption.HUMAN);
        humanPlayer2.setHexValue(HexValue.RED);
        optionPlayers.put("Player 1", humanPlayer1);
        optionPlayers.put("Player 2", humanPlayer2);
        currentOptionPlayer = "Player 1";
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        // Stolen from http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FrameDemoProject/src/components/FrameDemo.java
        MainMenu mainMenu = new MainMenu();
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame window = createUI();
            addElements(window, mainMenu);
            showUI(window);
        });
    }

    private static JFrame createUI() {
        //Create the main game window
        JFrame frame = new JFrame("Havannah Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        frame.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        //Set size and make the window visible
        frame.setSize(450, 350);

        return frame;
    }

    private static void showUI(JFrame frame) {
        frame.setVisible(true);
    }

    private static void addElements(JFrame frame, MainMenu mainMenu) {
        //add elements to the window in order
        addTitle(frame);
        addOptionsButton(frame, mainMenu);
        addNewGameButton(frame, mainMenu);
    }

    private static void addTitle(JFrame frame) {
        //Add the title text at the top
        Title title = new Title("Welcome to Havannah");
        frame.add(title);
    }

    private static void addOptionsButton(JFrame frame, MainMenu mainMenu){
        game.ui.Button options = new game.ui.Button("Options");
        options.addActionListener(actionEvent -> createOptionsDialog(frame, mainMenu));
        frame.add(options);
    }

    private static void createOptionsDialog(JFrame frame, MainMenu mainMenu) {
        JDialog dialog = new JDialog(new Dialog(frame), "Options");
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        addPlayerChoices(dialog, mainMenu);
        addEditPlayerButton(frame,dialog,mainMenu);
        addCloseDialogButton(dialog);
        dialog.pack();
        dialog.setVisible(true);
    }

    private static void addPlayerChoices(JDialog dialog, MainMenu mainMenu){
        Vector<String> playerChoices = new Vector<>();
        for (Map.Entry<String, PlayerType> optionPlayer: mainMenu.optionPlayers.entrySet()){
            playerChoices.add(optionPlayer.getKey());
        }
        addPlayerChoiceBox(dialog, playerChoices, mainMenu);
    }


    private static void addPlayerChoiceBox(JDialog dialog, Vector<String> playerChoices, MainMenu mainMenu) {
        JComboBox<String> valueChoiceBox = new JComboBox<>(playerChoices);
        valueChoiceBox.setSelectedItem(mainMenu.currentOptionPlayer);
        valueChoiceBox.addItemListener(itemEvent -> {
            LOG.info(String.format("Selected item: %s", itemEvent.getItem()));
            mainMenu.currentOptionPlayer = (String) itemEvent.getItem();
        });
        dialog.add(valueChoiceBox);
    }

    private static void addEditPlayerButton(JFrame frame, JDialog dialog, MainMenu mainMenu){
        JButton button = new JButton("Edit");
        button.addActionListener(action -> createPlayerOptionsDialog(frame, mainMenu));
        dialog.add(button);
    }
    private static void createPlayerOptionsDialog(JFrame frame, MainMenu mainMenu){
        JDialog dialog = new JDialog(new Dialog(frame), "Options");
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        addOptionElements(dialog, mainMenu);
        dialog.pack();
        dialog.setVisible(true);
    }

    private static void addOptionElements(JDialog dialog, MainMenu mainMenu) {
        addSetPlayerValueChoices(dialog, mainMenu);
        addSetPlayerTypeChoices(dialog, mainMenu);
        addCloseDialogButton(dialog);
    }

    private static void addSetPlayerValueChoices(JDialog dialog, MainMenu mainMenu){
        Vector<HexValue> valueChoices = new Vector<>();
        for(HexValue value: HexValue.values()){
            if(value.isValidForPlayer()){
                valueChoices.add(value);
            }
        }
        addPlayerValueChoiceBox(dialog, valueChoices, mainMenu);
    }

    private static void addPlayerValueChoiceBox(JDialog dialog, Vector<HexValue> valueChoices, MainMenu mainMenu) {
        JComboBox<HexValue> valueChoiceBox = new JComboBox<>(valueChoices);
        valueChoiceBox.setSelectedItem(mainMenu.optionPlayers.get(mainMenu.currentOptionPlayer).getHexValue());
        valueChoiceBox.addItemListener(itemEvent -> {
            LOG.info(String.format("Selected item: %s",itemEvent.getItem()));
            mainMenu.optionPlayers.get(mainMenu.currentOptionPlayer).setHexValue((HexValue) itemEvent.getItem());
        });
        dialog.add(valueChoiceBox);
    }

    private static void addSetPlayerTypeChoices(JDialog dialog, MainMenu mainMenu){
        Vector<PlayerOption> playerOptions = new Vector<>();
        Collections.addAll(playerOptions, PlayerOption.values());
        addPlayerOptionsChoiceBox(dialog, playerOptions, mainMenu);
    }

    private static void addPlayerOptionsChoiceBox(JDialog dialog, Vector<PlayerOption> playerOptions, MainMenu mainMenu) {
        JComboBox<PlayerOption> valueChoiceBox = new JComboBox<>(playerOptions);
        valueChoiceBox.setSelectedItem(mainMenu.optionPlayers.get(mainMenu.currentOptionPlayer).getPlayerOption());
        valueChoiceBox.addItemListener(itemEvent -> {
            LOG.info(String.format("Selected item: %s",itemEvent.getItem()));
            mainMenu.optionPlayers.get(mainMenu.currentOptionPlayer).setPlayerOption((PlayerOption) itemEvent.getItem());
        });
        dialog.add(valueChoiceBox);
    }

    private static void addCloseDialogButton(JDialog dialog) {
        JButton button = new JButton("DONE");
        button.addActionListener(actionEvent2 -> dialog.dispose());
        dialog.add(button);
    }

    private static void addNewGameButton(JFrame frame, MainMenu mainMenu){
        //Add a button to start new game.Game
        game.ui.Button newGame = new game.ui.Button("Start New Game");
        newGame.addActionListener(actionEvent -> {
            ArrayList<Player> allPlayers = generateAllPlayersFromOptionPlayers(mainMenu.optionPlayers);
            //Hands off the running of the game to the game.controls.GameRunner
            Game game = new Game(mainMenu.boardSize, allPlayers);
            game.launchGameWindow();
            try {
                game.startGameLoop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        frame.add(newGame);
    }

    private static ArrayList<Player> generateAllPlayersFromOptionPlayers(Map<String,PlayerType> optionPlayers){
        ArrayList<Player> allPlayers = new ArrayList<>();
        for (Map.Entry<String,PlayerType> playerType: optionPlayers.entrySet()){
            allPlayers.add(playerType.getValue().getPlayer());
        }
        return allPlayers;
    }
}
