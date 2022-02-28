package blackjack.views;

import blackjack.controllers.ScoreboardController;
import blackjack.models.MainModel;
import blackjack.Observer;
import blackjack.models.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * ScoreboardPanel, the panel that showcase the scoreboard
 * @author Lukas Wigren
 */
public class ScoreboardPanel extends JPanel implements Observer<MainModel> {

    private JPanel panel;
    private JPanel scorePanel;

    public ScoreboardPanel(ScoreboardController scoreboardController) {

        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.add(Box.createRigidArea(new Dimension(0,20)));
        add(panel);
    }

    private void showScores (List<Player> players) {
        scorePanel.removeAll();
        for (Player player : players) {
            JLabel l = new JLabel(player.getName() + ": " + (int)player.getBalance());
            l.setAlignmentX(Component.CENTER_ALIGNMENT);
            scorePanel.add(l);
        }
    }

    @Override
    public void update(MainModel o) {
        showScores(o.getPlayerList());

    }
}
