/*
 * Created by JFormDesigner on Fri Mar 04 04:17:54 CET 2022
 */

package blackjack.views;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import blackjack.controllers.GameController;
import blackjack.models.MainModel;
import blackjack.models.PlayerHand;

/**
 * @author Tor Falkenberg Gunberg & Lukas Wigren
 */
public class PlayerPanel extends JPanel {

    //Local variables
    private final GameController gameController;
    private PlayerHand hand = null;
    private JPanel cardPanel, buttonPanel;
    private JTextArea infoText;
    private List<JButton> buttonList;
    private final int seatNr;

    //Constructor
    public PlayerPanel(GameController gameController, int seatNr) {
        this.gameController = gameController;
        this.seatNr = seatNr;

        //Set Layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(0, 30, 0, 30));
        setOpaque(false);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        initialiseElements();

        add(cardPanel);
        add(infoText);
        add(buttonPanel);
    }

    //Public methods
    public void printCards(JLayeredPane cardsLP) {
        cardPanel.removeAll();
        cardPanel.add(cardsLP);
    }
    
    public void removeHand() { hand = null; }

    public void update(PlayerHand hand, MainModel o) {
        setHand(hand);
        whatButtons(o);
        updateInfoText();
    }

    //Private update methods
    private void setHand(PlayerHand hand) { this.hand = hand; }
    private void whatButtons (MainModel o) {
        hideAllButtons();
        if (o.activeGame() && o.playerActionsNeeded()) {
            if (!hand.isActionDone()) {
                showButton("Hit", true);
                showButton("Stand", true);
                showButton("Double", true);
                showButton("Split", true);
            }
        } else {
            if (hand.getPlayer() == null) {
                showButton("Add Player", true);
            } else if (hand.getBet() == 0) {
                showButton("Remove Player", true);
                showButton("Place Bet", true);
            }
        }
    }

    private void updateInfoText() {
        if (hand.getPlayer() == null) {
            infoText.setText("");
        } else {
            String name = hand.getPlayer().getName();
            int bet = (int) hand.getBet();
            double balance = hand.getPlayer().getBalance();
            infoText.setText("Name: " + name + "\nBet: " + bet + "\nBalance: " + balance);
        }
    }

    //Private methods
    private void hideAllButtons() {
        for (JButton button : buttonList) { button.setVisible(false); }
    }

    private void showButton(String text, boolean visible) {
        for (JButton button : buttonList) {
            if (Objects.equals(button.getText(), text)) {
                button.setVisible(visible);
            }
        }
    }

    private void initialiseElements() {
        cardPanel = new JPanel();
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(170, 130));
        cardPanel.setOpaque(false);

        //buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setOpaque(false);

        infoText = new JTextArea();
        infoText.setOpaque(false);
        infoText.setForeground(new Color(255, 0, 51));
        infoText.setFont(new Font("Times New Roman", Font.BOLD, 18));

        createButtons();
    }

    private void createButtons() {
        buttonList = new ArrayList<>();

        JButton betButton = new JButton("Place Bet");
        betButton.setActionCommand("BET" + Integer.toString(seatNr));
        buttonList.add(betButton);


        JButton hitButton = new JButton("Hit");
        hitButton.setActionCommand("HIT" + Integer.toString(seatNr));
        buttonList.add(hitButton);

        JButton standButton = new JButton("Stand");
        standButton.setActionCommand("STAND" + Integer.toString(seatNr));
        buttonList.add(standButton);

        JButton doubleButton = new JButton("Double");
        doubleButton.setActionCommand("DOUBLE" + Integer.toString(seatNr));
        buttonList.add(doubleButton);

        /*
        JButton splitButton = new JButton("Split");
        splitButton.setActionCommand("SPLIT" + Integer.toString(seatNr));
        buttonList.add(splitButton);
         */

        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.setActionCommand("ADD" + Integer.toString(seatNr));
        buttonList.add(addPlayerButton);

        JButton removePlayerButton = new JButton("Remove Player");
        removePlayerButton.setActionCommand("REMOVE" + Integer.toString(seatNr));
        buttonList.add(removePlayerButton);

        for (JButton button : buttonList) {
            button.addActionListener(gameController);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setSize(new Dimension(30, 20));
            buttonPanel.add(button);
        }
    }
}
