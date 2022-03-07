package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.views.MainView;
import blackjack.views.State;

import java.awt.event.*;
/**
 * MainController, a controller for the program, MainView...
 * @author  Lukas Wigren
 */
public class MainController {
    private MainModel mainModel;
    private MainView mainView;

    public MainController(MainModel mainModel, MainView mainView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
    }
}
