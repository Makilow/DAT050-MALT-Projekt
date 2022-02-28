package blackjack.views;

import blackjack.controllers.GameController;
import blackjack.models.*;
import blackjack.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * GamePanel, the panel that showcase the game
 * @author Tor Falkenberg & Lukas Wigren & Mark Villarosa
 */
public class GamePanel extends JPanel implements Observer<MainModel> {

    private JPanel dealerPanel;
    private JPanel playersPanel;
    private GameController gameController;
    private State state = State.NEWGAME;


    public GamePanel(GameController gameController) {
        this.gameController = gameController;
        setLayout(new BorderLayout());

        playersPanel = new JPanel(new FlowLayout());
        dealerPanel = new JPanel(new FlowLayout());
        add(dealerPanel);
        add(playersPanel,BorderLayout.SOUTH);
    }

    private void choosePlayers() {
        dealerPanel.removeAll();

        JButton newPlayer = new JButton("New Player");
        newPlayer.addActionListener(gameController);
        newPlayer.setActionCommand("New Player");
        dealerPanel.add(newPlayer);

        JButton startRound = new JButton("Start Round");
        startRound.addActionListener(gameController);
        startRound.setActionCommand("Start Round");
        dealerPanel.add(startRound);

        dealerPanel.revalidate();
    }

    private void playGame() {
        dealerPanel.removeAll();

        dealerPanel.add(new JLabel("Dealer"));

        dealerPanel.revalidate();
    }

    private JPanel createPlayerPanel(PlayerHand hand) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(new JLabel(hand.getPlayer().getName()));

        if (state == State.STARTGAME) {
            JPanel userInput = new JPanel();
            if (hand.getBet() == 0) {
                JTextField betValue = new JTextField(3);
                userInput.add(betValue);

                JButton bet = new JButton("BET");
                bet.addActionListener(gameController);
                bet.setActionCommand("UPDATE");
                bet.addActionListener(e -> hand.bet(Double.parseDouble(betValue.getText())));
                bet.addActionListener(gameController);
                bet.setActionCommand("UPDATE");
                userInput.add(bet);
                p.add(userInput);
            } else {
                p.add(createCardPane(hand.getCards()));
                p.add(new JLabel("Current Bet: " + hand.getBet()));
            }
        }
        p.add(new JLabel("Credits: " + hand.getPlayer().getBalance()));
        return p;
    }

    private JLayeredPane createCardPane(List<Card> cards) {
        JLayeredPane lp = new JLayeredPane();
        lp.setPreferredSize(new Dimension(200, 200));
        int j = 0;
        for (Card c : cards) {
            JLabel l = new JLabel(cardImage(c));
            l.setBounds(15 * j, 5 * j, 80, 120);
            lp.add(l, Integer.valueOf(j));
            j++;
        }
        return lp;
    }

    private void setPlayerPanels(List<PlayerHand> playerHands) {
        playersPanel.removeAll();
        if (playerHands != null) {
            for (PlayerHand hand : playerHands) {
                playersPanel.add(createPlayerPanel(hand));
            }
        }
        playersPanel.revalidate();
        playersPanel.repaint();
    }

    private ImageIcon cardImage(Card c) {
        String rank = c.getRank().toString();
        String suit = c.getSuit().toString();
        String filename = rank + "_of_" + suit + ".png";
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(new File("src/icons/cards/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Missing file: src/icons/cards/" + filename);
            System.exit(0);
        }
        Image image = bImage.getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    /*
    private void showHands(List<PlayerHand> hands) {
        int i = 0;
        for (Hand h : hands) {
            int j = 0;
            for (Card c : h.getCards()) {
                JLabel l = new JLabel(cardImage(c));
                l.setBounds(15 * j, 5 * j, 80, 120);
                handCards.get(i).add(l, Integer.valueOf(j));
                j++;
            }
            i++;
        }
    }

    private void showDealer(List<Card> cards, boolean showSecond) {
        int i = 0;
        for (Card c : cards) {
            JLabel l;
            if (!showSecond && i==1) {
                l = new JLabel(cardImage());
            } else {
                l = new JLabel(cardImage(c));
            }
            dealer.add(l);
            i++;
        }
    }

    private void clearCards() {
        dealer.removeAll();
        for (JLayeredPane lp : handCards) {
            lp.removeAll();
        }
        panel.revalidate();
        panel.repaint();
    }

     */

    @Override
    public void update(MainModel o) {
        state = o.getState();
        if (state == State.NEWGAME) {choosePlayers();}
        if (state == State.STARTGAME) {playGame();}
        setPlayerPanels(o.getHands());
    }
}
