package blackjack.controllers;

import blackjack.models.MainModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SettingController, a controller for scoreboardPanel
 * @author  Lukas Wigren
 */
public class SettingController implements ActionListener {
    MainModel mainModel;
    public SettingController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
