package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.views.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * SettingController, a controller for scoreboardPanel
 * @author  Lukas Wigren
 */
public class SettingController implements ActionListener, ItemListener {
    MainModel mainModel;
    int width = 1280, height = 720;
    boolean isFullscreen = false;
    boolean soundON = true;
    public SettingController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "OK" -> {
                mainModel.setSize(width,height);
                if (mainModel.getIsFullscreen() != isFullscreen) {mainModel.toggleFullscreen();}
                if (mainModel.getSoundON() != soundON) {mainModel.toggleSound();}
                mainModel.setState(State.MENU);
            }
            case "CANCEL" -> mainModel.setState(State.MENU);
            case "FULLSCREEN" -> isFullscreen ^= true;
            case "SOUND" -> soundON ^= true;
            default -> System.out.println(e.getActionCommand());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        switch (e.getItem().toString()) {
            case "1920x1080" -> {
                width = 1920;
                height = 1080;
            }
            case "1600x900" -> {
                width = 1600;
                height = 900;
            }
            case "1280x720" -> {
                width = 1280;
                height = 720;
            }
            default -> System.out.println("HOW DID WE GET HERE");
        }
    }
}
