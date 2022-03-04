/*
 * Created by JFormDesigner on Fri Mar 04 04:17:54 CET 2022
 */

package blackjack.views;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

import blackjack.controllers.GameController;
import blackjack.models.PlayerHand;

/**
 * @author unknown
 */
public class PlayerPanel extends JPanel {

    private GameController gameController;
    private final int seat;
    private JPanel cardPanel, buttonPanel;
    private JTextArea infoText;
    private JLayeredPane cards;
    private List<JButton> buttonList = new ArrayList<>();
    //private JButton addPlayerButton, removePlayerButton, betButton, hitButton, standButton, doubleButton, splitButton;

    public PlayerPanel(int seat, GameController gameController) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        this.gameController = gameController;
        this.seat = seat;

        cardPanel = new JPanel();
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(200,130));
        cardPanel.setOpaque(false);

        buttonPanel = new JPanel(new GridLayout(0,2));
        buttonPanel.setOpaque(false);

        infoText = new JTextArea();
        infoText.setOpaque(false);
        infoText.setForeground(new Color(255, 0, 51));
        infoText.setFont(new Font("Times New Roman", Font.BOLD, 18));

        createButtons();
        showButton("Add Player", true);

        add(cardPanel);
        add(buttonPanel);
        add(infoText);
    }


    public void setInfoText (PlayerHand hand) {
        String name = hand.getPlayer().getName();
        int bet = (int)hand.getBet();
        double balance = hand.getPlayer().getBalance();
        infoText.setText("Name: "+name+"\nBet: "+bet+"\nBalance: "+balance);
    }



    public void createButtons () {

        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.setActionCommand("ADD"+Integer.toString(seat));
        buttonList.add(addPlayerButton);

        JButton removePlayerButton = new JButton("Remove Player");
        removePlayerButton.setActionCommand("REMOVE"+Integer.toString(seat));
        buttonList.add(removePlayerButton);

        JButton betButton = new JButton("Place Bet");
        betButton.setActionCommand("BET"+Integer.toString(seat));
        buttonList.add(betButton);

        JButton hitButton = new JButton("Hit");
        hitButton.setActionCommand("HIT"+Integer.toString(seat));
        buttonList.add(hitButton);

        JButton standButton = new JButton("Stand");
        standButton.setActionCommand("STAND"+Integer.toString(seat));
        buttonList.add(standButton);

        JButton doubleButton = new JButton("Double");
        doubleButton.setActionCommand("DOUBLE"+Integer.toString(seat));
        buttonList.add(doubleButton);

        JButton splitButton = new JButton("Split");
        splitButton.setActionCommand("SPLIT"+Integer.toString(seat));
        buttonList.add(splitButton);

        for (JButton button : buttonList) {
            button.addActionListener(gameController);
            button.setSize(new Dimension(30, 20));
            buttonPanel.add(button);
        }
        hideAllButtons();
    }

    public void hideAllButtons() {
        for (JButton button : buttonList) {
            button.setVisible(false);
        }
    }
    public void showButton (String text, boolean visible)  {
        for (JButton button : buttonList) {
            if (Objects.equals(button.getText(), text)) {
                button.setVisible(visible);
            }
        }
    }

    public void showAddPlayerButton () {
        //cards.setVisible(false);
        //betButton.setVisible(false);
        //addPlayerButton.setVisible(true);
    }

    public void showBetButton () {
        //addPlayerButton.setVisible(false);
        //betButton.setVisible(true);
    }

    public void showCards(JLayeredPane cards) {
        cardPanel.removeAll();
        cardPanel.add(cards);
    }
}
