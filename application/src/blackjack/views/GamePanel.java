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

/**
 * GamePanel, the panel that showcase the game
 * @author Mark Villarosa
 */
public class GamePanel extends JPanel implements Observer<MainModel> {

    private JPanel panel;
    private JLabel card1;
    private JLabel card2;
    private JLabel card3;
    private JLabel card4;
    private JLabel dealer2;
    private JLabel dealer1;
    private JButton deal;

    private CardDeck deck = new CardDeck();


    public ImageIcon cardImage (Card card) {
        BufferedImage bimage = null;
        String filename = "src/icons/cards/" + card.getRank().toString() + "_of_" + card.getSuit().toString() + ".png";
        try {
            bimage = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = bimage.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public void deal() {
        card1.setIcon(cardImage(deck.next()));
        card2.setIcon(cardImage(deck.next()));
        card3.setIcon(cardImage(deck.next()));
        card4.setIcon(cardImage(deck.next()));

        dealer1.setIcon(cardImage(deck.next()));
        dealer2.setIcon(cardImage(deck.next()));
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public class Hitlistener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand() == "hit") {
                deal();
            }
        }
    }

    public GamePanel(GameController gameController) {

        Hitlistener listener = new Hitlistener();

        setLayout(new GridLayout(1,0));
        add(panel);

        deal.addActionListener(listener);
        deal.setActionCommand("hit");

        deal();



    }

    @Override
    public void update(MainModel observable) {

    }
}
