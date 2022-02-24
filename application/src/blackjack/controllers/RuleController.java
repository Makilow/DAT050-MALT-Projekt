package blackjack.controllers;

import blackjack.models.MainModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * RuleController, a controller for rulePanel
 * @author  Lukas Wigren
 */
public class RuleController implements ActionListener {
    MainModel mainModel;
    public RuleController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
