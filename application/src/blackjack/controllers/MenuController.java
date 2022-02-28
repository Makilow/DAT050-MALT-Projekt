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
            case "NEWGAME":
                mainModel.setState(State.NEWGAME);
                break;
            case "STARTGAME":
                mainModel.setState(State.STARTGAME);
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
            case "EXIT":
                mainModel.setState(State.EXIT);
                break;
            default:
        }
    }

}
