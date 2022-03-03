package blackjack.controllers;

import blackjack.models.MainModel;

import javax.swing.*;
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
        String name;
        switch (e.getActionCommand()) {
            case "HIT"-> mainModel.playerHit();
            case "STAND"-> mainModel.playerStand();
            case "DOUBLE"-> mainModel.playerDouble();
            case "SPLIT"-> mainModel.playerSplit();
            case "ADD" -> {
                name = JOptionPane.showInputDialog(null, "Enter player name:");
                if (name != null) {
                    mainModel.addPlayer(name);
                }
            }
            case "REMOVE" -> {
                name = JOptionPane.showInputDialog(null, "Enter player name:");
                if (name != null) {
                    mainModel.removePlayer(name);
                }
            }
            default-> System.out.println("HOW DID WE GET HERE?");
        }
    }
}
