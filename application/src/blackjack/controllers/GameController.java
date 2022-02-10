package blackjack.controllers;

import blackjack.models.MainModel;

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

    }
}
