package blackjack.views;

import blackjack.controllers.GameController;
import blackjack.models.*;
import blackjack.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * GamePanel, the panel that showcase the game
 * @author Mark Villarosa
 */
public class GamePanel extends JPanel implements Observer<MainModel> {

    private JPanel panel;
    private JButton hitButton;
    private JButton standButton;
    private JButton doubleButton;
    private JButton splitButton;
    private JPanel handOne;
    private JPanel handTwo;
    private JPanel handThree;
    private JPanel handFour;
    private JPanel handFive;
    private JPanel dealer;
    private final List<JLayeredPane> handCards = new ArrayList<>();

    public GamePanel(GameController gameController) {
        hitButton.addActionListener(gameController);
        standButton.addActionListener(gameController);
        doubleButton.addActionListener(gameController);
        splitButton.addActionListener(gameController);
        hitButton.setActionCommand("HIT");
        standButton.setActionCommand("STAND");
        doubleButton.setActionCommand("DOUBLE");
        splitButton.setActionCommand("SPLIT");
        handCards.add(new JLayeredPane());
        handCards.add(new JLayeredPane());
        handCards.add(new JLayeredPane());
        handCards.add(new JLayeredPane());
        handCards.add(new JLayeredPane());
        for (JLayeredPane lp : handCards) {
            lp.setBounds(0, 0, 160, 160);
        }
        handOne.setLayout(null);
        handTwo.setLayout(null);
        handThree.setLayout(null);
        handFour.setLayout(null);
        handFive.setLayout(null);
        handOne.add(handCards.get(0));
        handTwo.add(handCards.get(1));
        handThree.add(handCards.get(2));
        handFour.add(handCards.get(3));
        handFive.add(handCards.get(4));
        setLayout(new GridLayout(1, 0));
        add(panel);
    }

    private ImageIcon cardImage() {
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(new File("src/icons/cards/backside.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Missing file: src/icons/cards/backside.png");
            System.exit(0);
        }
        Image image = bImage.getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
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

    @Override
    public void update(MainModel o) {
        if (o.getState() != State.GAME) return;
        clearCards();
        showDealer(o.getDealerCards(), o.getShowSecond());
        showHands(o.getHands());
    }
}
