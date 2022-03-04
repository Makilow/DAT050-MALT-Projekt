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
            case "ADD0" -> addPlayer(0);
            case "ADD1" -> addPlayer(1);
            case "ADD2" -> addPlayer(2);
            case "ADD3" -> addPlayer(3);
            case "ADD4" -> addPlayer(4);
            case "BET0"-> placeBet(0);
            case "BET1"-> placeBet(1);
            case "BET2"-> placeBet(2);
            case "BET3"-> placeBet(3);
            case "BET4"-> placeBet(4);
            case "HIT"-> mainModel.playerHit();
            case "STAND"-> mainModel.playerStand();
            case "DOUBLE"-> mainModel.playerDouble();
            case "SPLIT"-> mainModel.playerSplit();

            case "REMOVE" -> {
                name = JOptionPane.showInputDialog(null, "Enter player name:");
                if (name != null) {
                    mainModel.removePlayer(name);
                }
            }
            default-> System.out.println("HOW DID WE GET HERE?");
        }
    }

    private void addPlayer (int seat) {
        String name = JOptionPane.showInputDialog(null, "Enter player name:");
        if (name != null) {
            mainModel.addPlayer(name, seat);
        }
    }

    private void placeBet (int seat) {
        int bet = Integer.parseInt(JOptionPane.showInputDialog(null, "How much dineros?"));
        mainModel.playerBet(seat,bet);
    }

}
