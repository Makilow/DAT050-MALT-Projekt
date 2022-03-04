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

    private final List<PlayerPanel> playerPanelList = new ArrayList<>();
    private final List<JLayeredPane> handCards = new ArrayList<>();
    private final List<JTextArea> infoTexts = new ArrayList<>();
    private int cardWidth, cardHeight;

    private JButton betOne, betTwo, betThree, betFour, betFive;

    public GamePanel(GameController gameController) {
        for (int i = 0; i < 5; i++) {
            playerPanelList.add(new PlayerPanel(i, gameController));
        }
        initComponents();
        hitButton.addActionListener(gameController);
        standButton.addActionListener(gameController);
        doubleButton.addActionListener(gameController);
        splitButton.addActionListener(gameController);
        addButton.addActionListener(gameController);
        removeButton.addActionListener(gameController);
        hitButton.setActionCommand("HIT");
        standButton.setActionCommand("STAND");
        doubleButton.setActionCommand("DOUBLE");
        splitButton.setActionCommand("SPLIT");
        addButton.setActionCommand("ADD");
        removeButton.setActionCommand("REMOVE");

        betOne = new JButton("Bet");
        betTwo = new JButton("Bet");
        betThree = new JButton("Bet");
        betFour = new JButton("Bet");
        betFive = new JButton("Bet");

        betOne.addActionListener(gameController);
        betTwo.addActionListener(gameController);
        betThree.addActionListener(gameController);
        betFour.addActionListener(gameController);
        betFive.addActionListener(gameController);

        betOne.setActionCommand("BETONE");
        betTwo.setActionCommand("BETTWO");
        betThree.setActionCommand("BETTHREE");
        betFour.setActionCommand("BETFOUR");
        betFive.setActionCommand("BETFIVE");

        handCards.add(new JLayeredPane());
        handCards.add(new JLayeredPane());
        handCards.add(new JLayeredPane());
        handCards.add(new JLayeredPane());
        handCards.add(new JLayeredPane());
        for (JLayeredPane lp : handCards) {
            lp.setBounds(0, 0, 160, 160);
        }

        handTwo.add(new JPanel().add(betTwo));



        /*
        handOne.add(handCards.get(0));
        handTwo.add(handCards.get(1));
        handThree.add(handCards.get(2));
        handFour.add(handCards.get(3));
        handFive.add(handCards.get(4));

        handOne.setLayout(null);
        handTwo.setLayout(null);
        handThree.setLayout(null);
        handFour.setLayout(null);
        handFive.setLayout(null);
        */

        infoTexts.add(infoOne);
        infoTexts.add(infoTwo);
        infoTexts.add(infoThree);
        infoTexts.add(infoFour);
        infoTexts.add(infoFive);
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
        Image image = bImage.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    private void showHands(List<PlayerHand> hands) {
        int i = 0;
        for (PlayerHand h : hands) {
            int j = 0;
            if (h.getBet() > 0) {
                for (Card c : h.getCards()) {
                    JLabel l = new JLabel(cardImage(getFilename(c)));
                    l.setBounds(15 * j, 5 * j, cardWidth, cardHeight);
                    handCards.get(i).add(l, Integer.valueOf(j));
                    j++;
                }
                playerPanelList.get(i).showCards(handCards.get(i));
            }
        }
        i++;
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
        updateInfo(o.getHands());
        updateBackground(o.getWidth(),o.getHeight());
        highlightCurrentHand(o.getCurrentHand());
        clearCards();
        showDealer(o.getDealerCards(), o.getShowSecond());
        showHands(o.getHands());
        showBettingButton(o.activeGame(), o.getHands());

        updatePlayerPanels(o.getHands());
    }

    private void updatePlayerPanels(List<PlayerHand> hands) {
        int i = 0;
        for (PlayerHand hand : hands) {
            if (hand.getPlayer() != null) {
                playerPanelList.get(i).showButton("Add Player", false);
                playerPanelList.get(i).showButton("Remove Player", true);
                playerPanelList.get(i).showButton("Place Bet", true);
            }
            i++;
        }
    }

    private void showBettingButton(Boolean activeGame, List<PlayerHand> hands) {
        if (activeGame) {
            // dont show button
            // setvisable(false)
        } else {
            // show button
            for (PlayerHand h : hands) {
                if (h.getPlayer() != null) {
                    // show button
                }
            }
        }
    }
    private void highlightCurrentHand(int index) {
        Border currentPlayerBorder = BorderFactory.createLineBorder(Color.RED);
        for(JLayeredPane lp : handCards) {lp.setBorder(null);}
        handCards.get(index).setBorder(currentPlayerBorder);
    }
    private void updateInfo(List<PlayerHand> hands) {
        String name;
        Double bet, balance;
        for (int i = 0; i < hands.size(); i++) {
            /*
            name = hands.get(i).getPlayer().getName();
            bet = hands.get(i).getBet();
            balance = hands.get(i).getPlayer().getBalance();
            infoTexts.get(i).setText("Name: "+name+"\nBet: "+bet+"\nBalance: "+balance);

             */
            if (hands.get(i).getPlayer() != null) {
                playerPanelList.get(i).setInfoText(hands.get(i));
            }

        }
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
                cardWidth = 60; cardHeight = 90;
            }
            case 900 -> {
                cardWidth = 80; cardHeight = 120;
            }
            case 1080 -> {
                cardWidth = 100; cardHeight = 150;
            }
            default -> System.out.println("Wait what how we get here?");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel = new JPanel();
        panel1 = new JPanel();
        var vSpacer2 = new Spacer();
        dealer = new JPanel();
        var vSpacer1 = new Spacer();
        var hSpacer3 = new Spacer();
        handOne = new JPanel();
        handTwo = new JPanel();
        handThree = new JPanel();
        handFour = new JPanel();
        handFive = new JPanel();
        var hSpacer4 = new Spacer();
        infoOne = new JTextArea();
        infoTwo = new JTextArea();
        infoThree = new JTextArea();
        infoFour = new JTextArea();
        infoFive = new JTextArea();
        var vSpacer4 = new Spacer();
        panel2 = new JPanel();
        hitButton = new JButton();
        standButton = new JButton();
        doubleButton = new JButton();
        splitButton = new JButton();
        addButton = new JButton();
        removeButton = new JButton();
        var vSpacer3 = new Spacer();
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
                panel1.setLayout(new GridLayoutManager(9, 7, new Insets(0, 0, 0, 0), 2, 2));
                panel1.add(vSpacer2, new GridConstraints(0, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));

                //======== dealer ========
                {
                    dealer.setOpaque(false);
                    dealer.setLayout(new FlowLayout());
                }
                panel1.add(dealer, new GridConstraints(1, 1, 1, 5,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(vSpacer1, new GridConstraints(2, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));
                panel1.add(hSpacer3, new GridConstraints(3, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));

                //======== handOne ========
                {
                    handOne.setPreferredSize(null);
                    handOne.setOpaque(false);
                    handOne.setLayout(new FlowLayout());
                }
                panel1.add(playerPanelList.get(0), new GridConstraints(3, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));

                //======== handTwo ========
                {
                    handTwo.setPreferredSize(null);
                    handTwo.setOpaque(false);
                    handTwo.setLayout(new FlowLayout());
                }
                panel1.add(playerPanelList.get(1), new GridConstraints(3, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));

                //======== handThree ========
                {
                    handThree.setPreferredSize(null);
                    handThree.setOpaque(false);
                    handThree.setLayout(new FlowLayout());
                }
                panel1.add(playerPanelList.get(2), new GridConstraints(3, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));

                //======== handFour ========
                {
                    handFour.setPreferredSize(null);
                    handFour.setOpaque(false);
                    handFour.setLayout(new FlowLayout());
                }
                panel1.add(playerPanelList.get(3), new GridConstraints(3, 4, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));

                //======== handFive ========
                {
                    handFive.setPreferredSize(null);
                    handFive.setOpaque(false);
                    handFive.setLayout(new FlowLayout());
                }
                panel1.add(playerPanelList.get(4), new GridConstraints(3, 5, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));
                panel1.add(hSpacer4, new GridConstraints(3, 6, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));

                //---- infoOne ----
                infoOne.setOpaque(false);
                infoOne.setForeground(new Color(255, 0, 51));
                infoOne.setFont(new Font("Times New Roman", Font.BOLD, 18));
                infoOne.setEditable(false);
                panel1.add(infoOne, new GridConstraints(4, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- infoTwo ----
                infoTwo.setOpaque(false);
                infoTwo.setForeground(new Color(255, 0, 51));
                infoTwo.setFont(new Font("Times New Roman", Font.BOLD, 18));
                infoTwo.setEditable(false);
                panel1.add(infoTwo, new GridConstraints(4, 2, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- infoThree ----
                infoThree.setOpaque(false);
                infoThree.setForeground(new Color(255, 0, 51));
                infoThree.setFont(new Font("Times New Roman", Font.BOLD, 18));
                infoThree.setEditable(false);
                panel1.add(infoThree, new GridConstraints(4, 3, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- infoFour ----
                infoFour.setOpaque(false);
                infoFour.setForeground(new Color(255, 0, 51));
                infoFour.setFont(new Font("Times New Roman", Font.BOLD, 18));
                infoFour.setEditable(false);
                panel1.add(infoFour, new GridConstraints(4, 4, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- infoFive ----
                infoFive.setOpaque(false);
                infoFive.setForeground(new Color(255, 0, 51));
                infoFive.setFont(new Font("Times New Roman", Font.BOLD, 18));
                infoFive.setEditable(false);
                panel1.add(infoFive, new GridConstraints(4, 5, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(vSpacer4, new GridConstraints(5, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, new Dimension(0, 40), null));

                //======== panel2 ========
                {
                    panel2.setOpaque(false);
                    panel2.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));

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

                    //---- addButton ----
                    addButton.setText("Add Player");
                    panel2.add(addButton, new GridConstraints(2, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- removeButton ----
                    removeButton.setText("Remove Player");
                    panel2.add(removeButton, new GridConstraints(2, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                }
                panel1.add(panel2, new GridConstraints(6, 1, 1, 5,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(vSpacer3, new GridConstraints(8, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));
            }
            panel.add(panel1);
            panel1.setBounds(0, 0, 960, 565);

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
    private JTextArea infoOne;
    private JTextArea infoTwo;
    private JTextArea infoThree;
    private JTextArea infoFour;
    private JTextArea infoFive;
    private JPanel panel2;
    private JButton hitButton;
    private JButton standButton;
    private JButton doubleButton;
    private JButton splitButton;
    private JButton addButton;
    private JButton removeButton;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
