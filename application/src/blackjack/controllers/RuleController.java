package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.views.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * RuleController, a controller for rulePanel
 *
 * @author Lukas Wigren
 */
public class RuleController implements ActionListener {
    MainModel mainModel;

    public RuleController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("MENU".equals(e.getActionCommand())) {
            mainModel.setState(State.MENU);
        } else {
            throw new IllegalStateException("Error:" + e.getActionCommand());
        }
    }
}

