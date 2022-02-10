package blackjack.views;

import blackjack.controllers.MenuController;
import blackjack.models.MainModel;
import blackjack.Observer;

import javax.swing.*;
import java.awt.*;
/**
 * MenuPanel, the panel that showcase the menu
 * @author Lukas Wigren
 */
public class MenuPanel extends JPanel implements Observer<MainModel> {
    private JPanel panel;
    private JButton startButton;
    private JButton settingsButton;
    private JButton rulesButton;
    private JButton scoreboardButton;
    private JButton exitButton;

    public MenuPanel(MenuController menuController) {
        System.out.println(State.GAME.toString());
        startButton.setActionCommand(State.GAME.toString());
        settingsButton.setActionCommand(State.SETTINGS.toString());
        rulesButton.setActionCommand(State.RULES.toString());
        scoreboardButton.setActionCommand(State.SCOREBOARD.toString());
        exitButton.setActionCommand(State.EXIT.toString());
        startButton.addActionListener(menuController);
        settingsButton.addActionListener(menuController);
        rulesButton.addActionListener(menuController);
        scoreboardButton.addActionListener(menuController);
        exitButton.addActionListener(menuController);
        setLayout(new GridLayout(1,0));
        add(panel);
    }
    @Override
    public void update(MainModel o) {
    }
}
