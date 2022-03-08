package blackjack.views;

import java.awt.event.*;
import javax.swing.border.*;
import blackjack.controllers.GameController;
import blackjack.models.*;
import blackjack.Observer;
import blackjack.models.Card;
import blackjack.models.Hand;
import blackjack.models.MainModel;
import blackjack.models.PlayerHand;

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
 * @author Tor Falkenberg, Mark Villarosa, Lukas Wigren
 */
public class GamePanel extends JPanel implements Observer<MainModel> {

    private final List<PlayerPanel> playerPanelList = new ArrayList<>();
    private int cardWidth, cardHeight;
    private int nrOfPlayers = 5;

    /**
     * Constructor for GamePanel
     * Creates all the swing objects and listeners
     * @param gameController    ChatController for listeners
     * @param nrOfPlayers   Number of players
     */
    public GamePanel(GameController gameController, int nrOfPlayers) {
        //Local variables
        this.nrOfPlayers = nrOfPlayers;

        //Set Layout
        setLayout(new GridLayout(1, 0));
        initComponents();
        for (int i = 0; i < nrOfPlayers; i++) {
            PlayerPanel playerPanel = new PlayerPanel(gameController, i);
            playerPanelList.add(playerPanel);
            ppContainer.add(playerPanel);
        }

        add(panel);
        updateBackground(1280,720);
    }
    
    /**
     * Update functions, called by observer
     * updates the panel with newly updated mainModel
     * @param o MainModel
     */
    //Public methods
    @Override
    public void update(MainModel o) {
        if (o.getState() != State.GAME) return;
        updateBackground(o.getWidth(),o.getHeight());
        updateStatusText(o);
        updatePlayerPanels(o.getHands(), o);
        showDealer(o.getDealerCards(), o.getShowSecond());
        showHands(o.getHands());
    }

    //Private update methods
    private void updateBackground(int width, int height) {
        updateCardSize(height);
        panel.setSize(new Dimension(width,height));
        panel1.setSize(new Dimension(width,height));
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(getClass().getClassLoader().getResource("icons/blackjackbord.jpg"));
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
            case 720 -> { cardWidth = 60; cardHeight = 90; }
            case 900 -> { cardWidth = 80; cardHeight = 120; }
            case 1080 -> { cardWidth = 100; cardHeight = 150; }
            default -> System.out.println("Wait what how we get here?");
        }
    }

    private void updateStatusText (MainModel o) {
        int timerCounter = o.getTimerCounter();
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
        dealer.removeAll();
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
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, 0, 160, 160);
            layeredPane.setAlignmentX(Component.CENTER_ALIGNMENT);
            int j = 0;
            for (Card c : pHand.getCards()) {
                JLabel l = new JLabel(cardImage(getFilename(c)));
                l.setBounds(15 * j, 5 * j, cardWidth, cardHeight);
                layeredPane.add(l, Integer.valueOf(j));
                j++;
            }
            playerPanelList.get(i).printCards(layeredPane);
            i++;
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
            bImage = ImageIO.read(getClass().getClassLoader().getResource("icons/cards/" + filename));
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
                statusText.setText("New round starts in 30s! Place your bets!");
                statusText.setOpaque(false);
                statusText.setHorizontalAlignment(SwingConstants.CENTER);
                statusText.setFont(new Font("Docktrin", Font.PLAIN, 55));
                statusText.setBorder(null);
                statusText.setForeground(new Color(39, 4, 4));
                statusText.setEditable(false);
                statusText.setFocusable(false);
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
