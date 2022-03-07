package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.views.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * SettingController, a controller for scoreboardPanel
 * @author  Lukas Wigren. Mark Villarosa
 */
public class SettingController implements ActionListener, ItemListener {
    private MainModel mainModel;
    private int  width = 1280, height = 720;
    private boolean isFullscreen = false;
    private boolean soundON = false;
    private int song;

    public SettingController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "OK" -> {
                mainModel.setSize(width, height);
                if (mainModel.getIsFullscreen() != isFullscreen) {
                    mainModel.toggleFullscreen();
                }
                if (mainModel.getSoundON() != soundON) {
                    mainModel.toggleSound();
                }
                if (mainModel.currentSong() != song) {
                    mainModel.switchSong(song);
                }
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
            case "Music 1" -> {
                song = 0;
            }
            case "Music 2" -> {
                song = 1; 
            }
            default -> System.out.println("HOW DID WE GET HERE");
        }
    }
}
