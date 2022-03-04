package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.views.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * RuleController, a controller for rulePanel
 * @author  Lukas Wigren, Mark
 */
public class ChatController implements ActionListener {
    MainModel mainModel;
    public ChatController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "MENU" -> mainModel.setState(State.MENU);
            default -> {
            }
        }
    }

}
