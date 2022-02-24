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
    private final JLayeredPane dealerCards = new JLayeredPane();
    private List<JLayeredPane> handCards = new ArrayList<>();

    public GamePanel(GameController gameController) {
        handCards.add(new JLayeredPane());
        handCards.add(new JLayeredPane());
        handCards.add(new JLayeredPane());
        handCards.add(new JLayeredPane());
        handCards.add(new JLayeredPane());
        handOne.add(handCards.get(0));
        handTwo.add(handCards.get(1));
        handThree.add(handCards.get(2));
        handFour.add(handCards.get(3));
        handFive.add(handCards.get(4));
        setLayout(new GridLayout(1,0));
        add(panel);
    }
    private ImageIcon cardImage(Card c) {
        String rank = c.getRank().toString();
        String suit = c.getSuit().toString();
        String filename = rank + "_of_" + suit + ".png";
        BufferedImage bImage = null;
        try {
            System.out.println("src/icons/cards/"+filename);
            bImage = ImageIO.read(new File("src/icons/cards/"+filename));
        } catch (IOException e) {e.printStackTrace(); System.out.println("Missing file: src/icons/cards/"+filename); System.exit(0);}


        assert bImage != null;      // REMOVE THIS


        Image image = bImage.getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
    private void showHands(List<PlayerHand> hands) {
        int i = 0;
        for (Hand h : hands) {
            int j = 0;
            for (Card c : h.getCards()) {
                JLabel l = new JLabel(cardImage(c));
                l.setBounds(10*j,5*j, 80, 120);
                handCards.get(i).add(l, Integer.valueOf(j));
                j++;
            }
            i++;
        }
    }
    private void showDealer(List<Card> cards) {
        int i = 0;
        for (Card c : cards) {
            JLabel l = new JLabel(cardImage(c));
            l.setBounds(10*i,5*i, 80, 120);
            dealerCards.add(new JLabel(cardImage(c)));
            i++;
        }
    }
    @Override
    public void update(MainModel o) {
        showDealer(o.getDealerCards());
        //showHands(o.getHands());
    }

}
