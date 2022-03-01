package blackjack.views;

import java.awt.event.*;
import javax.swing.border.*;
import blackjack.Observer;
import blackjack.controllers.RuleController;
import blackjack.models.MainModel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * RulePanel, the panel that showcase the rules
 *
 * @author Lukas Wigren , Mark Villarosa
 */
public class RulePanel extends JPanel implements Observer<MainModel> {

    public RulePanel(RuleController ruleController) {
        initComponents();
        returnButton.setActionCommand(State.MENU.toString());
        returnButton.addActionListener(ruleController);
        setLayout(new GridLayout(1, 0));
        add(panel1);
    }

    @Override
    public void update(MainModel observable) {
    }


    private void returnButtonKeyPressed(KeyEvent e) {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        panel = new JTextArea();
        returnButton = new JButton();
        bordet = new JLabel();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(7, 65, 0));
            panel1.setPreferredSize(new Dimension(1280, 720));
            panel1.setMaximumSize(new Dimension(1280, 720));
            panel1.setMinimumSize(null);
            panel1.setAlignmentX(0.0F);
            panel1.setAlignmentY(0.0F);
            panel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panel1.setBorder(new TitledBorder("text"));
            panel1.setLayout(null);

            //---- panel ----
            panel.setBackground(new Color(65, 4, 0));
            panel.setEditable(false);
            panel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
            panel.setForeground(Color.white);
            panel.setOpaque(false);
            panel.setRequestFocusEnabled(false);
            panel.setSelectionEnd(928);
            panel.setTabSize(2);
            panel.setText("-The goal of blackjack is to beat the dealer's hand without going over 21.\n\n-Face cards are worth 10. Aces are worth 1 or 11, whichever makes a better hand.\n\n-Each player starts with two cards, one of the dealer's cards is hidden until the end.\n\n-To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn.\n\n-If you go over 21 you bust, and the dealer wins regardless  of the dealer's hand.\n\n-If you are dealt 21 from the start (Ace & 10), you get blackjack.\n\n-Blackjack usually means you win 1.5 times the amount of your bet.\n\n-Dealer will hit until his/her cards total 17 or higher.\n\n-Doubling is like a hit, only the bet is doubled and you only get one more card.\n\n-Split can be done when you have two of the same card and the pair is split into two hands.\n\n-Splitting also doubles the bet, because each new hand is worth the original bet.\n\n-You cannot play on two aces after they are split.");
            panel.setWrapStyleWord(false);
            panel1.add(panel);
            panel.setBounds(330, 65, 605, panel.getPreferredSize().height);

            //---- returnButton ----
            returnButton.setBackground(new Color(65, 4, 0));
            returnButton.setForeground(new Color(65, 4, 0));
            returnButton.setIcon(new ImageIcon(getClass().getResource("/icons/button_return.png")));
            returnButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/button_returnH.png")));
            returnButton.setText("");
            returnButton.setHorizontalTextPosition(SwingConstants.CENTER);
            returnButton.setMaximumSize(new Dimension(174, 46));
            returnButton.setBorderPainted(false);
            returnButton.setSelected(true);
            returnButton.setContentAreaFilled(false);
            returnButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    returnButtonKeyPressed(e);
                }
            });
            panel1.add(returnButton);
            returnButton.setBounds(570, 610, 145, returnButton.getPreferredSize().height);

            //---- bordet ----
            bordet.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbordsuddigt.png")));
            bordet.setHorizontalAlignment(SwingConstants.CENTER);
            bordet.setMaximumSize(new Dimension(1500, 864));
            bordet.setPreferredSize(new Dimension(1500, 864));
            bordet.setMinimumSize(new Dimension(1500, 860));
            panel1.add(bordet);
            bordet.setBounds(-160, -65, 1600, 900);

            panel1.setPreferredSize(new Dimension(1260, 835));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JTextArea panel;
    private JButton returnButton;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
