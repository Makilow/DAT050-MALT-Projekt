package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.models.Player;
import blackjack.views.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GameController, a controller for gamePanel
 * @author  Lukas Wigren
 */
public class GameController implements ActionListener {
    MainModel mainModel;
    public GameController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "HIT": mainModel.playerHit();          break;
            case "STAND": mainModel.playerStand();      break;
            case "DOUBLE": mainModel.playerDouble();    break;
            case "SPLIT": mainModel.playerSplit();      break;
            case "New Player": mainModel.newPlayer();      break;
            case "Start Round": mainModel.setState(State.STARTGAME);      break;
            case "UPDATE": mainModel.update();      break;

            default: System.out.println("WTF HOW DID WE GET HERE?");
        }
    }
}
