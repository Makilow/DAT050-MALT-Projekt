package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.views.MainView;
import blackjack.views.State;

import java.awt.event.*;
/**
 * MainController, a controller for the programs MainView
 * @author  Lukas Wigren
 */
public class MainController implements KeyListener {
    private final MainModel mainModel;
    private MainView mainView;
    /**
     * The MainController constructor
     * @param mainModel The mainModel
     * @param mainView  The MainView
     */
    public MainController(MainModel mainModel, MainView mainView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
    }
    /**
     * KeyTyped, unused
     * @param e KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }
    /**
     * KeyPressed, ESC -> State.MENU
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27-> mainModel.setState(State.MENU); // escape
            default -> {}
        }
    }
    /**
     *  KeyReleased, unused
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
