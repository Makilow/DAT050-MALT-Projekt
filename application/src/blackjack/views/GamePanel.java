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
 * @author Tor Falkenberg & Mark Villarosa & Lukas Wigren
 */
public class GamePanel extends JPanel implements Observer<MainModel> {

    //Local variables
    private GameController gamecontroller;
    private final List<PlayerPanel> playerPanelList = new ArrayList<>();
    private final List<JLayeredPane> cardLPList = new ArrayList<>();
    private int cardWidth, cardHeight;
    private int nrOfPlayers = 5;
    //private boolean activeGame,playerActionsNeeded = false;
    private int timerCounter, timeBetweenRounds;

    //Constructor
    public GamePanel(GameController gameController, int nrOfPlayers) {
        this.gamecontroller = gameController;
        this.nrOfPlayers = nrOfPlayers;

        //Set Layout
        setLayout(new GridLayout(1, 0));
        initComponents();

        for (int i = 0; i < nrOfPlayers; i++) {

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, 0, 160, 160);
            cardLPList.add(layeredPane);

            PlayerPanel playerPanel = new PlayerPanel(gameController, i);
            playerPanel.printCards(layeredPane);
            playerPanelList.add(playerPanel);

            ppContainer.add(playerPanel);
        }

        add(panel);
        updateBackground(1280,720);
    }


    //Public methods
    @Override
    public void update(MainModel o) {
        if (o.getState() != State.GAME) return;
        updateBackground(o.getWidth(),o.getHeight());
        doGameLogic(o);
        updatePlayerPanels(o.getHands(), o);
        showDealer(o.getDealerCards(), o.getShowSecond());
        showHands(o.getHands());

        //highlightCurrentHand(o.getCurrentHand());
        //clearCards();
        //showBettingButton(o.activeGame(), o.getHands());
    }

    //Private update methods
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

    private void doGameLogic (MainModel o) {

        //Get timer variables
        this.timerCounter = o.getTimerCounter();
        this.timeBetweenRounds = o.getTimeBetweenRounds();

        if (!o.activeGame()) {
            this.statusText.setText("Place a bet for the game to start");
        } else if (o.activeGame() && timerCounter > 0) {
            this.statusText.setText("The round starts in " + Integer.toString(timerCounter) + "s. Place your bets!");
        } else if (o.activeGame() && timerCounter == 0) {
            this.statusText.setText("");

        }
    }

    private void updatePlayerPanels(List<PlayerHand> playerHandList, MainModel o) {
        for (int i = 0; i < nrOfPlayers; i++) {
            playerPanelList.get(i).update(playerHandList.get(i), o);
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

    private void showHands(List<PlayerHand> playerHandList) {
        int i = 0;
        for (PlayerHand pHand : playerHandList) {
            int j = 0;
            if (!pHand.getCards().isEmpty()) {
                for (Card c : pHand.getCards()) {
                    JLabel l = new JLabel(cardImage(getFilename(c)));
                    l.setBounds(15 * j, 5 * j, cardWidth, cardHeight);
                    cardLPList.get(i).add(l, Integer.valueOf(j));
                    j++;
                }
                playerPanelList.get(i).printCards(cardLPList.get(i));
            }
            i++;
        }
    }

    /*
    private void clearCards() {
        dealer.removeAll();
        for (JLayeredPane lp : handCards) {
            lp.removeAll();
        }
        panel.revalidate();
        panel.repaint();
    }



    private void highlightCurrentHand(int index) {
        Border currentPlayerBorder = BorderFactory.createLineBorder(Color.RED);
        for(JLayeredPane lp : handCards) {lp.setBorder(null);}
        handCards.get(index).setBorder(currentPlayerBorder);
    }

     */


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




    //Private methods
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










    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel = new JPanel();
        panel1 = new JPanel();
        var vSpacer2 = new Spacer();
        dealer = new JPanel();
        statusText = new JTextField();
        var vSpacer1 = new Spacer();
        var hSpacer3 = new Spacer();
        ppContainer = new JPanel();
        var hSpacer4 = new Spacer();
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
                panel1.setLayout(new GridLayoutManager(10, 7, new Insets(0, 0, 0, 0), 2, 2));
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

                //---- statusText ----
                statusText.setOpaque(false);
                statusText.setHorizontalAlignment(SwingConstants.CENTER);
                statusText.setFont(statusText.getFont().deriveFont(statusText.getFont().getStyle() | Font.BOLD, 48f));
                statusText.setBorder(null);
                statusText.setForeground(Color.black);
                panel1.add(statusText, new GridConstraints(2, 0, 1, 7,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));
                panel1.add(vSpacer1, new GridConstraints(3, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));
                panel1.add(hSpacer3, new GridConstraints(4, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));

                //======== ppContainer ========
                {
                    ppContainer.setOpaque(false);
                    ppContainer.setLayout(new FlowLayout());
                }
                panel1.add(ppContainer, new GridConstraints(4, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(hSpacer4, new GridConstraints(4, 6, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));
                panel1.add(vSpacer3, new GridConstraints(5, 3, 5, 1,
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
    private JTextField statusText;
    private JPanel ppContainer;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

/*
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
    */







/*
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
 */