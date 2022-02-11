package blackjack.views;

import blackjack.controllers.GameController;
import blackjack.models.MainModel;
import blackjack.Observer;

import javax.swing.*;

/**
 * GamePanel, the panel that showcase the game
 * @author Mark Villarosa
 */
public class GamePanel extends JPanel implements Observer<MainModel> {

    public GamePanel(GameController gameController) {

    }

    @Override
    public void update(MainModel observable) {

    }
}
