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
    public ScoreboardController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("EXIT".equals(e.getActionCommand())) {
            mainModel.setState(State.MENU);
        }
    }
}
