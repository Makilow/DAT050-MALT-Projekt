package blackjack.views;

import blackjack.Observer;
import blackjack.controllers.GameController;
import blackjack.models.MainModel;

import javax.swing.*;

/**
 * GamePanel, the panel that showcase the game
 *
 * @author Mark Villarosa
 */
public class GamePanel extends JPanel implements Observer<MainModel> {

    public GamePanel(GameController gameController) {

    }

    @Override
    public void update(MainModel observable) {

    }
}
