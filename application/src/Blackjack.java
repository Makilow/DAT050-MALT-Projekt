import blackjack.controllers.*;
import blackjack.models.MainModel;
import blackjack.views.*;
import blackjack.views.State;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * The Blackjack class.. starts the program... something something .. can be run in thread
 * @author Lukas Wigren
 */
public class Blackjack implements Runnable {
    private MainModel mainModel;
    private MainView mainView;
    private MainController mainController;

    public Blackjack() {
        new Thread(this).start();
    }

    public static void main(String[] args) {
        new Blackjack();
    }

    @Override
    public void run() {
        mainModel = new MainModel();
        mainController = new MainController(mainModel, mainView);
        JPanel container = new JPanel(new CardLayout());
        // Create Panels
        GamePanel gamePanel = new GamePanel(new GameController(mainModel), mainModel.getNrOfPlayers());
        MenuPanel menuPanel = new MenuPanel(new MenuController(mainModel));
        SettingPanel settingPanel = new SettingPanel(new SettingController(mainModel));
        RulePanel rulePanel = new RulePanel(new RuleController(mainModel));
        ScoreboardPanel scoreboardPanel = new ScoreboardPanel(new ScoreboardController(mainModel));
        // Add to panels to mainPanel
        container.add(menuPanel, State.MENU.toString());
        container.add(gamePanel, State.GAME.toString());
        container.add(settingPanel, State.SETTINGS.toString());
        container.add(rulePanel, State.RULES.toString());
        container.add(scoreboardPanel, State.SCOREBOARD.toString());
        //
        mainView = new MainView(mainModel.getWidth(), mainModel.getHeight(), container);
        // Listeners for mainView
        mainView.addKeyListener(mainController);
        mainView.addMouseListener(mainController);
        mainView.addComponentListener(mainController);
        // Add observers
        mainModel.addObserver(mainView);
        mainModel.addObserver(menuPanel);
        mainModel.addObserver(gamePanel);
        mainModel.addObserver(settingPanel);
        mainModel.addObserver(rulePanel);
        mainModel.addObserver(scoreboardPanel);
    }
}
