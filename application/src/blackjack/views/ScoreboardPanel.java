package blackjack.views;

import blackjack.controllers.ScoreboardController;
import blackjack.models.MainModel;
import blackjack.Observer;

import javax.swing.*;
/**
 * ScoreboardPanel, the panel that showcase the scoreboard
 * @author Lukas Wigren
 */
public class ScoreboardPanel extends JPanel implements Observer<MainModel> {
    public ScoreboardPanel(ScoreboardController scoreboardController) {

    }

    @Override
    public void update(MainModel observable) {

    }
}
