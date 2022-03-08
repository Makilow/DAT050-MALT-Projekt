package blackjack.views;

import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.border.*;
import blackjack.Observer;
import blackjack.controllers.MenuController;
import blackjack.models.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.intellij.uiDesigner.core.*;



/**
 * MenuPanel, the panel that showcase the menu
 * @author Lukas Wigren
 */
public class MenuPanel extends JPanel implements Observer<MainModel> {
    /**
     * Constructor for MenuPanel
     * Creates all the swing objects and listeners
     * @param menuController    ChatController for listeners
     */
    public MenuPanel(MenuController menuController) {
        initComponents();
        startButton.setActionCommand(State.GAME.toString());
        settingsButton.setActionCommand(State.SETTINGS.toString());
        rulesButton.setActionCommand(State.RULES.toString());
        scoreboardButton.setActionCommand(State.SCOREBOARD.toString());
        chatButton.setActionCommand(State.CHAT.toString());
        exitButton.setActionCommand(State.EXIT.toString());
        startButton.addActionListener(menuController);
        settingsButton.addActionListener(menuController);
        rulesButton.addActionListener(menuController);
        scoreboardButton.addActionListener(menuController);
        chatButton.addActionListener(menuController);
        exitButton.addActionListener(menuController);
        setLayout(new GridLayout(1, 0));
        add(panel);
        updateBackground(1280,720);
    }
    /**
     * Update functions, called by observer
     * updates the panel with newly updated mainModel
     * @param o MainModel
     */
    @Override
    public void update(MainModel o) {
        if (o.getState() != State.MENU) {return;}
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

    private void startButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel = new JPanel();
        panel1 = new JPanel();
        var vSpacer3 = new Spacer();
        startButton = new JButton();
        panel2 = new JTextArea();
        var vSpacer1 = new Spacer();
        chatButton = new JButton();
        var hSpacer2 = new Spacer();
        scoreboardButton = new JButton();
        var hSpacer3 = new Spacer();
        settingsButton = new JButton();
        rulesButton = new JButton();
        exitButton = new JButton();
        var vSpacer2 = new Spacer();
        bordet = new JLabel();

        //======== panel ========
        {
            panel.setBackground(new Color(7, 65, 0));
            panel.setBorder(new TitledBorder(BorderFactory.createEmptyBorder(), ""));
            panel.setMinimumSize(null);
            panel.setMaximumSize(null);
            panel.setPreferredSize(null);
            panel.setAlignmentX(0.0F);
            panel.setAlignmentY(0.0F);
            panel.setLayout(null);

            //======== panel1 ========
            {
                panel1.setOpaque(false);
                panel1.setMinimumSize(null);
                panel1.setMaximumSize(null);
                panel1.setPreferredSize(new Dimension(1280, 720));
                panel1.setLayout(new GridLayoutManager(11, 3, new Insets(0, 0, 0, 0), 5, -1, true, false));
                panel1.add(vSpacer3, new GridConstraints(0, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));

                //---- startButton ----
                startButton.setActionCommand("");
                startButton.setBorderPainted(false);
                startButton.setDoubleBuffered(false);
                startButton.setHideActionText(false);
                startButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_start.png")));
                startButton.setIconTextGap(4);
                startButton.setInheritsPopupMenu(false);
                startButton.setLabel("");
                startButton.setMargin(new Insets(0, 0, 0, 0));
                startButton.setName("");
                startButton.setOpaque(false);
                startButton.setRolloverEnabled(true);
                startButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_startH.png")));
                startButton.setSelected(true);
                startButton.setText("");
                startButton.setVisible(true);
                startButton.setContentAreaFilled(false);
                startButton.setHorizontalTextPosition(SwingConstants.CENTER);
                startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                startButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        startButtonMouseClicked(e);
                    }
                });
                panel1.add(startButton, new GridConstraints(3, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- panel2 ----
                panel2.setBackground(new Color(65, 4, 0));
                panel2.setEditable(false);
                panel2.setFont(new Font("Hotel De Paris", Font.PLAIN, 130));
                panel2.setForeground(Color.white);
                panel2.setOpaque(false);
                panel2.setRequestFocusEnabled(false);
                panel2.setSelectionEnd(928);
                panel2.setTabSize(2);
                panel2.setText("Blackjack ");
                panel2.setWrapStyleWord(false);
                panel2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                panel1.add(panel2, new GridConstraints(1, 0, 1, 3,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- vSpacer1 ----
                vSpacer1.setMinimumSize(new Dimension(0, 50));
                vSpacer1.setMaximumSize(new Dimension(0, 50));
                vSpacer1.setPreferredSize(new Dimension(0, 50));
                panel1.add(vSpacer1, new GridConstraints(2, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- chatButton ----
                chatButton.setBorderPainted(false);
                chatButton.setContentAreaFilled(false);
                chatButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_chat.png")));
                chatButton.setLabel("");
                chatButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_chath.png")));
                chatButton.setSelected(true);
                chatButton.setText("");
                chatButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                panel1.add(chatButton, new GridConstraints(4, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(hSpacer2, new GridConstraints(5, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));

                //---- scoreboardButton ----
                scoreboardButton.setBorderPainted(false);
                scoreboardButton.setContentAreaFilled(false);
                scoreboardButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_scoreboard.png")));
                scoreboardButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_scoreboardH.png")));
                scoreboardButton.setSelected(true);
                scoreboardButton.setText("");
                scoreboardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                panel1.add(scoreboardButton, new GridConstraints(5, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(hSpacer3, new GridConstraints(5, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));

                //---- settingsButton ----
                settingsButton.setBorderPainted(false);
                settingsButton.setContentAreaFilled(false);
                settingsButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_settings.png")));
                settingsButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_settingsH.png")));
                settingsButton.setSelected(true);
                settingsButton.setText("");
                settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                panel1.add(settingsButton, new GridConstraints(6, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- rulesButton ----
                rulesButton.setBorderPainted(false);
                rulesButton.setContentAreaFilled(false);
                rulesButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_rules.png")));
                rulesButton.setLabel("");
                rulesButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_rulesH.png")));
                rulesButton.setSelected(true);
                rulesButton.setText("");
                rulesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                panel1.add(rulesButton, new GridConstraints(7, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- exitButton ----
                exitButton.setBorderPainted(false);
                exitButton.setContentAreaFilled(false);
                exitButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_exit.png")));
                exitButton.setLabel("");
                exitButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_exitH.png")));
                exitButton.setSelected(true);
                exitButton.setText("");
                exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                panel1.add(exitButton, new GridConstraints(8, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(vSpacer2, new GridConstraints(9, 1, 1, 1,
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
            bordet.setBackground(null);
            bordet.setForeground(null);
            bordet.setVerticalAlignment(SwingConstants.TOP);
            panel.add(bordet);
            bordet.setBounds(new Rectangle(new Point(0, 0), bordet.getPreferredSize()));

            panel.setPreferredSize(new Dimension(2955, 1150));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel;
    private JPanel panel1;
    private JButton startButton;
    private JTextArea panel2;
    private JButton chatButton;
    private JButton scoreboardButton;
    private JButton settingsButton;
    private JButton rulesButton;
    private JButton exitButton;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
