package blackjack.views;

import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.border.*;
import blackjack.Observer;
import blackjack.controllers.RuleController;
import blackjack.models.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import com.intellij.uiDesigner.core.*;

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
        add(panel);
    }

    @Override
    public void update(MainModel o) {
        if (o.getState() != State.RULES) {return;}
        updateBackground(o.getWidth(), o.getHeight());
    }
    private void updateBackground(int width, int height) {
        panel.setSize(new Dimension(width,height));
        panel1.setSize(new Dimension(width,height));
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(getClass().getClassLoader().getResource("icons/blackjackbordsuddigt.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Missing file: \"src/icons/blackjackbordsuddigt.png\"");
            System.exit(0);
        }
        Image image = bImage.getScaledInstance(width,height,Image.SCALE_SMOOTH);
        bordet.setIcon(new ImageIcon(image));
    }

    private void returnButtonKeyPressed(KeyEvent e) {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel = new JPanel();
        panel1 = new JPanel();
        var vSpacer1 = new Spacer();
        var hSpacer2 = new Spacer();
        rules = new JTextArea();
        var hSpacer1 = new Spacer();
        var vSpacer2 = new Spacer();
        returnButton = new JButton();
        var vSpacer3 = new Spacer();
        bordet = new JLabel();

        //======== panel ========
        {
            panel.setBackground(new Color(7, 65, 0));
            panel.setPreferredSize(new Dimension(1280, 720));
            panel.setMaximumSize(new Dimension(1280, 720));
            panel.setMinimumSize(null);
            panel.setAlignmentX(0.0F);
            panel.setAlignmentY(0.0F);
            panel.setBorder(new TitledBorder("text"));
            panel.setLayout(null);

            //======== panel1 ========
            {
                panel1.setOpaque(false);
                panel1.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), 5, -1));
                panel1.add(vSpacer1, new GridConstraints(0, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));
                panel1.add(hSpacer2, new GridConstraints(1, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));

                //---- rules ----
                rules.setBackground(new Color(65, 4, 0));
                rules.setEditable(false);
                rules.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
                rules.setForeground(Color.white);
                rules.setRequestFocusEnabled(false);
                rules.setSelectionEnd(928);
                rules.setTabSize(2);
                rules.setText("-The goal of blackjack is to beat the dealer's hand without going over 21.\n\n-Face cards are worth 10. Aces are worth 1 or 11, whichever makes a better hand.\n\n-Each player starts with two cards, one of the dealer's cards is hidden until the end.\n\n-To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn.\n\n-If you go over 21 you bust, and the dealer wins regardless  of the dealer's hand.\n\n-If you are dealt 21 from the start (Ace & 10), you get blackjack.\n\n-Blackjack usually means you win 1.5 times the amount of your bet.\n\n-Dealer will hit until his/her cards total 17 or higher.\n\n-Doubling is like a hit, only the bet is doubled and you only get one more card.");
                rules.setWrapStyleWord(false);
                rules.setOpaque(false);
                panel1.add(rules, new GridConstraints(1, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(hSpacer1, new GridConstraints(1, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));
                panel1.add(vSpacer2, new GridConstraints(2, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));

                //---- returnButton ----
                returnButton.setBackground(new Color(65, 4, 0));
                returnButton.setForeground(new Color(65, 4, 0));
                returnButton.setIcon(new ImageIcon(getClass().getResource("/icons/button_return.png")));
                returnButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/button_returnH.png")));
                returnButton.setText("");
                returnButton.setHorizontalTextPosition(SwingConstants.CENTER);
                returnButton.setMaximumSize(new Dimension(174, 46));
                returnButton.setBorderPainted(false);
                returnButton.setContentAreaFilled(false);
                returnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                returnButton.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        returnButtonKeyPressed(e);
                    }
                });
                panel1.add(returnButton, new GridConstraints(3, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(vSpacer3, new GridConstraints(4, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));
            }
            panel.add(panel1);
            panel1.setBounds(new Rectangle(new Point(0, 0), panel1.getPreferredSize()));

            //---- bordet ----
            bordet.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbordsuddigt.png")));
            bordet.setHorizontalAlignment(SwingConstants.LEFT);
            bordet.setMaximumSize(null);
            bordet.setPreferredSize(null);
            bordet.setMinimumSize(null);
            bordet.setVerticalAlignment(SwingConstants.TOP);
            panel.add(bordet);
            bordet.setBounds(new Rectangle(new Point(0, 0), bordet.getPreferredSize()));

            panel.setPreferredSize(new Dimension(1260, 835));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel;
    private JPanel panel1;
    private JTextArea rules;
    private JButton returnButton;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
