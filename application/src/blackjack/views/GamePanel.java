package blackjack.views;

import java.awt.event.*;
import javax.swing.border.*;
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
    private int cardWidth, cardHeight;
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
        updateBackground(1280,720);
    }

    private String getFilename(Card c) {
        String rank = c.getRank().toString();
        String suit = c.getSuit().toString();
        return rank + "_of_" + suit + ".png";
    }

    private ImageIcon cardImage(String filename) {
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
                JLabel l = new JLabel(cardImage(getFilename(c)));
                l.setBounds(15 * j, 5 * j, cardWidth, cardHeight);
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
                l = new JLabel(cardImage("backside.png"));
            } else {
                l = new JLabel(cardImage(getFilename(c)));
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
        updateBackground(o.getWidth(),o.getHeight());
        clearCards();
        showDealer(o.getDealerCards(), o.getShowSecond());
        showHands(o.getHands());
    }
    private void updateBackground(int width, int height) {
        updateCardSize(height);
        panel.setSize(new Dimension(width,height));
        panel1.setSize(new Dimension(width,height));
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(new File("src/icons/blackjackbord.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Missing file: \"src/icons/blackjackbord.jpg\"");
            System.exit(0);
        }
        Image image = bImage.getScaledInstance(width,height,Image.SCALE_SMOOTH);
        bordet.setIcon(new ImageIcon(image));
    }
    private void updateCardSize(int h) {
        switch (h) {
            case 720 -> {
                cardWidth = 40; cardHeight = 60;
            }
            case 900 -> {
                cardWidth = 60; cardHeight = 90;
            }
            case 1080 -> {
                cardWidth = 80; cardHeight = 120;
            }
            default -> System.out.println("Wait what how we get here?");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel = new JPanel();
        panel1 = new JPanel();
        dealer = new JPanel();
        handOne = new JPanel();
        handTwo = new JPanel();
        handThree = new JPanel();
        handFour = new JPanel();
        handFive = new JPanel();
        var hSpacer1 = new Spacer();
        panel2 = new JPanel();
        hitButton = new JButton();
        standButton = new JButton();
        doubleButton = new JButton();
        splitButton = new JButton();
        var hSpacer2 = new Spacer();
        bordet = new JLabel();

        //======== panel ========
        {
            panel.setAlignmentX(0.0F);
            panel.setAlignmentY(0.0F);
            panel.setBackground(new Color(7, 65, 0));
            panel.setBorder(new TitledBorder("text"));
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panel.setMaximumSize(new Dimension(1280, 720));
            panel.setMinimumSize(null);
            panel.setPreferredSize(new Dimension(1280, 720));
            panel.setLayout(null);

            //======== panel1 ========
            {
                panel1.setOpaque(false);
                panel1.setLayout(new GridLayoutManager(7, 5, new Insets(0, 0, 0, 0), 2, 2));

                //======== dealer ========
                {
                    dealer.setOpaque(false);
                    dealer.setLayout(new FlowLayout());
                }
                panel1.add(dealer, new GridConstraints(2, 1, 1, 3,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //======== handOne ========
                {
                    handOne.setBackground(null);
                    handOne.setForeground(null);
                    handOne.setOpaque(false);
                    handOne.setLayout(new FlowLayout());
                }
                panel1.add(handOne, new GridConstraints(3, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //======== handTwo ========
                {
                    handTwo.setOpaque(false);
                    handTwo.setLayout(new FlowLayout());
                }
                panel1.add(handTwo, new GridConstraints(3, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //======== handThree ========
                {
                    handThree.setOpaque(false);
                    handThree.setLayout(new FlowLayout());
                }
                panel1.add(handThree, new GridConstraints(3, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //======== handFour ========
                {
                    handFour.setOpaque(false);
                    handFour.setLayout(new FlowLayout());
                }
                panel1.add(handFour, new GridConstraints(3, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //======== handFive ========
                {
                    handFive.setOpaque(false);
                    handFive.setLayout(new FlowLayout());
                }
                panel1.add(handFive, new GridConstraints(3, 4, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(hSpacer1, new GridConstraints(5, 0, 1, 2,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));

                //======== panel2 ========
                {
                    panel2.setOpaque(false);
                    panel2.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));

                    //---- hitButton ----
                    hitButton.setText("Hit");
                    panel2.add(hitButton, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- standButton ----
                    standButton.setText("Stand");
                    panel2.add(standButton, new GridConstraints(0, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- doubleButton ----
                    doubleButton.setText("Double");
                    panel2.add(doubleButton, new GridConstraints(1, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- splitButton ----
                    splitButton.setText("Split");
                    panel2.add(splitButton, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                }
                panel1.add(panel2, new GridConstraints(5, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(hSpacer2, new GridConstraints(5, 3, 1, 2,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));
            }
            panel.add(panel1);
            panel1.setBounds(new Rectangle(new Point(0, 0), panel1.getPreferredSize()));

            //---- bordet ----
            bordet.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbord.jpg")));
            bordet.setHorizontalAlignment(SwingConstants.LEFT);
            bordet.setMaximumSize(new Dimension(1900, 1080));
            bordet.setPreferredSize(null);
            bordet.setMinimumSize(new Dimension(1900, 1080));
            bordet.setBackground(null);
            bordet.setVerticalAlignment(SwingConstants.TOP);
            panel.add(bordet);
            bordet.setBounds(new Rectangle(new Point(0, 0), bordet.getPreferredSize()));

            panel.setPreferredSize(new Dimension(1885, 1050));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel;
    private JPanel panel1;
    private JPanel dealer;
    private JPanel handOne;
    private JPanel handTwo;
    private JPanel handThree;
    private JPanel handFour;
    private JPanel handFive;
    private JPanel panel2;
    private JButton hitButton;
    private JButton standButton;
    private JButton doubleButton;
    private JButton splitButton;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
