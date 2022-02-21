package blackjack.views;

import blackjack.controllers.GameController;
import blackjack.models.Dealer;
import blackjack.models.MainModel;
import blackjack.Observer;
import blackjack.models.Player;

import javax.swing.*;

/**
 * GamePanel, the panel that showcase the game
 * @author Mark Villarosa
 */
public class GamePanel extends JPanel implements Observer<MainModel> {

    public GamePanel(GameController gameController) {
        dealer = new Dealer();

        //Test Code
        for (int i = 0; i < 3; i++) {
            dealer.addSeat(new Player());
        }

        dealer.newRound();

    }

    @Override
    public void update(MainModel observable) {

    }
}
