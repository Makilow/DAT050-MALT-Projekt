package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.views.MainView;
import blackjack.views.State;

import java.awt.event.*;
/**
 * MainController, a controller for the program, MainView...
 * @author  Lukas Wigren
 */
public class MainController implements KeyListener {
    private MainModel mainModel;
    private MainView mainView;

    public MainController(MainModel mainModel, MainView mainView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
    }
        @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27-> mainModel.setState(State.MENU); // escape
            default -> {}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
