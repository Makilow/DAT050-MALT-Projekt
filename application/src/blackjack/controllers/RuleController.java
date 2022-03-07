package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.views.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * RuleController, a controller for rulePanel
 * @author  Lukas Wigren, Mark Villarosa
 */
public class RuleController implements ActionListener {
    MainModel mainModel;
    /**
     * Constructor for RuleController
     * @param mainModel The MainModel
     */
    public RuleController(MainModel mainModel) {
        this.mainModel = mainModel;
    }
    /**
     * ActionListener
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("MENU".equals(e.getActionCommand())) {
            mainModel.setState(State.MENU);
        }
    }
}
