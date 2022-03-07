package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.views.State;
import com.sun.tools.javac.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ScoreboardController, a controller for scoreboardPanel
 * @author  Lukas Wigren
 */
public class ScoreboardController implements ActionListener {
    private MainModel mainModel;
    /**
     * Constructor for ScoreboardController
     * @param mainModel The MainModel
     */
    public ScoreboardController(MainModel mainModel) {
        this.mainModel = mainModel;
    }
    /**
     * ActionListener
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("EXIT".equals(e.getActionCommand())) {
            mainModel.setState(State.MENU);
        }
    }
}
