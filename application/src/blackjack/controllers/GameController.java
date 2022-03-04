package blackjack.controllers;

import blackjack.models.MainModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GameController, a controller for gamePanel
 * @author  Lukas Wigren & Tor Falkenberg
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
            case "HIT0"-> playerHit(0);
            case "HIT1"-> playerHit(1);
            case "HIT2"-> playerHit(2);
            case "HIT3"-> playerHit(3);
            case "HIT4"-> playerHit(4);
            case "STAND0"-> mainModel.playerStand(0);
            case "STAND1"-> mainModel.playerStand(1);
            case "STAND2"-> mainModel.playerStand(2);
            case "STAND3"-> mainModel.playerStand(3);
            case "STAND4"-> mainModel.playerStand(4);
            case "DOUBLE0"-> mainModel.playerDouble(0);
            case "DOUBLE1"-> mainModel.playerDouble(1);
            case "DOUBLE2"-> mainModel.playerDouble(2);
            case "DOUBLE3"-> mainModel.playerDouble(3);
            case "DOUBLE4"-> mainModel.playerDouble(4);
            //case "SPLIT"-> mainModel.playerSplit();

            case "REMOVE" -> removePlayer();
            default-> System.out.println("HOW DID WE GET HERE?");
        }
    }

    private void addPlayer (int seat) {
        String name = JOptionPane.showInputDialog(null, "Enter player name:");
        if (name != null) {
            mainModel.addPlayer(name, seat);
        }
    }

    private void removePlayer () {
        String name = JOptionPane.showInputDialog(null, "Enter player name:");
        if (name != null) {
            mainModel.removePlayer(name);
        }
    }

    private void placeBet (int seat) {
        int bet = Integer.parseInt(JOptionPane.showInputDialog(null, "How much dineros?"));
        mainModel.playerBet(seat,bet);
    }

    private void playerHit (int seat) {
        mainModel.playerHit(seat);
    }


}
