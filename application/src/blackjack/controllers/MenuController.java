package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.views.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * MenuController, a controller for menuPanel
 * @author  Lukas Wigren
 */
public class MenuController implements ActionListener {
    MainModel mainModel;

    public MenuController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "GAME":
                mainModel.setState(State.GAME);
                break;
            case "SETTINGS":
                mainModel.setState(State.SETTINGS);
                break;
            case "RULES":
                mainModel.setState(State.RULES);
                break;
            case "SCOREBOARD":
                mainModel.setState(State.SCOREBOARD);
                break;
            case "CHAT":
                mainModel.setState(State.CHAT);
                break;
            case "EXIT":
                mainModel.setState(State.EXIT);
                break;
            default:
        }
    }
}
