package blackjack.views;

import java.awt.event.*;
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
import com.intellij.uiDesigner.core.*;

/**
 * GamePanel, the panel that showcase the game
 * @author Mark Villarosa
 */
public class GamePanel extends JPanel implements Observer<MainModel> {

    private final List<JLayeredPane> handCards = new ArrayList<>();

    public GamePanel(GameController gameController) {
        initComponents();
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

    private void bordetMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel = new JPanel();
        dealer = new JPanel();
        handOne = new JPanel();
        handTwo = new JPanel();
        handThree = new JPanel();
        handFour = new JPanel();
        handFive = new JPanel();
        hitButton = new JButton();
        standButton = new JButton();
        doubleButton = new JButton();
        splitButton = new JButton();
        bordet = new JLabel();

        //======== panel ========
        {
            panel.setLayout(null);

            //======== dealer ========
            {
                dealer.setOpaque(false);
                dealer.setLayout(new FlowLayout());
            }
            panel.add(dealer);
            dealer.setBounds(545, 120, 500, 145);

            //======== handOne ========
            {
                handOne.setBackground(null);
                handOne.setForeground(null);
                handOne.setOpaque(false);
                handOne.setLayout(new FlowLayout());
            }
            panel.add(handOne);
            handOne.setBounds(185, 330, 160, 160);

            //======== handTwo ========
            {
                handTwo.setOpaque(false);
                handTwo.setLayout(new FlowLayout());
            }
            panel.add(handTwo);
            handTwo.setBounds(390, 480, 160, 160);

            //======== handThree ========
            {
                handThree.setOpaque(false);
                handThree.setLayout(new FlowLayout());
            }
            panel.add(handThree);
            handThree.setBounds(730, 550, 160, 160);

            //======== handFour ========
            {
                handFour.setOpaque(false);
                handFour.setLayout(new FlowLayout());
            }
            panel.add(handFour);
            handFour.setBounds(1065, 480, 160, 160);

            //======== handFive ========
            {
                handFive.setOpaque(false);
                handFive.setLayout(new FlowLayout());
            }
            panel.add(handFive);
            handFive.setBounds(1265, 330, 160, 160);

            //---- hitButton ----
            hitButton.setText("Hit");
            panel.add(hitButton);
            hitButton.setBounds(new Rectangle(new Point(720, 735), hitButton.getPreferredSize()));

            //---- standButton ----
            standButton.setText("Stand");
            panel.add(standButton);
            standButton.setBounds(new Rectangle(new Point(720, 775), standButton.getPreferredSize()));

            //---- doubleButton ----
            doubleButton.setText("Double");
            panel.add(doubleButton);
            doubleButton.setBounds(new Rectangle(new Point(810, 775), doubleButton.getPreferredSize()));

            //---- splitButton ----
            splitButton.setText("Split");
            panel.add(splitButton);
            splitButton.setBounds(new Rectangle(new Point(810, 735), splitButton.getPreferredSize()));

            //---- bordet ----
            bordet.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbord.jpg")));
            bordet.setHorizontalAlignment(SwingConstants.CENTER);
            bordet.setMaximumSize(new Dimension(1500, 864));
            bordet.setPreferredSize(new Dimension(1500, 864));
            bordet.setMinimumSize(new Dimension(1500, 860));
            bordet.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    bordetMouseClicked(e);
                    bordetMouseClicked(e);
                }
            });
            panel.add(bordet);
            bordet.setBounds(0, 0, 1600, 900);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel.getComponentCount(); i++) {
                    Rectangle bounds = panel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel.setMinimumSize(preferredSize);
                panel.setPreferredSize(preferredSize);
            }
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel;
    private JPanel dealer;
    private JPanel handOne;
    private JPanel handTwo;
    private JPanel handThree;
    private JPanel handFour;
    private JPanel handFive;
    private JButton hitButton;
    private JButton standButton;
    private JButton doubleButton;
    private JButton splitButton;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
