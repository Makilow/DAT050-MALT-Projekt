package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.views.State;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * MenuController, a controller for menuPanel
 * @author  Lukas Wigren
 */
public class MenuController implements ActionListener {
    MainModel mainModel;
    /**
     * MenuController constructor
     * @param mainModel The MainModel
     */
    public MenuController(MainModel mainModel) {
        this.mainModel = mainModel;
    }
    /**
     * ActionListener
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "GAME" -> mainModel.setState(State.GAME);
            case "SETTINGS" -> mainModel.setState(State.SETTINGS);
            case "RULES" -> mainModel.setState(State.RULES);
            case "SCOREBOARD" -> mainModel.setState(State.SCOREBOARD);
            case "CHAT" ->  {
                String uname = JOptionPane.showInputDialog("Enter username:");
                if (uname != null) {
                    mainModel.setChatUsername(uname);
                    mainModel.setState(State.CHAT);
                }
            }
            case "EXIT" -> mainModel.setState(State.EXIT);
            default -> {
            }
        }
    }
}
