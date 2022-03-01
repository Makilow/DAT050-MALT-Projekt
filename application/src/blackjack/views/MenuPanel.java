package blackjack.views;

import java.awt.event.*;
import javax.swing.border.*;
import blackjack.Observer;
import blackjack.controllers.MenuController;
import blackjack.models.MainModel;

import javax.swing.*;
import java.awt.*;



/**
 * MenuPanel, the panel that showcase the menu
 *
 * @author Lukas Wigren
 */
public class MenuPanel extends JPanel implements Observer<MainModel> {

    public MenuPanel(MenuController menuController) {
        initComponents();
        startButton.setActionCommand(State.GAME.toString());
        settingsButton.setActionCommand(State.SETTINGS.toString());
        rulesButton.setActionCommand(State.RULES.toString());
        scoreboardButton.setActionCommand(State.SCOREBOARD.toString());
        exitButton.setActionCommand(State.EXIT.toString());
        startButton.addActionListener(menuController);
        settingsButton.addActionListener(menuController);
        rulesButton.addActionListener(menuController);
        scoreboardButton.addActionListener(menuController);
        exitButton.addActionListener(menuController);
        setLayout(new GridLayout(1, 0));
        add(panel);
    }

    @Override
    public void update(MainModel o) {
    }

    private void bordetMouseClicked(MouseEvent e) {
        ImageIcon ii = new ImageIcon(getClass().getResource("/bordetsuddigt.png"));
        Image image = (ii).getImage().getScaledInstance(bordet.getWidth(), bordet.getHeight(),Image.SCALE_SMOOTH);
        ii = new ImageIcon(image);
        bordet.setIcon(ii);
    }

    private void startButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel = new JPanel();
        startButton = new JButton();
        settingsButton = new JButton();
        rulesButton = new JButton();
        scoreboardButton = new JButton();
        exitButton = new JButton();
        panel2 = new JTextArea();
        bordet = new JLabel();

        //======== panel ========
        {
            panel.setBackground(new Color(7, 65, 0));
            panel.setBorder(new TitledBorder(BorderFactory.createEmptyBorder(), ""));
            panel.setMinimumSize(null);
            panel.setMaximumSize(new Dimension(1280, 720));
            panel.setPreferredSize(new Dimension(1280, 720));
            panel.setAlignmentX(0.0F);
            panel.setAlignmentY(0.0F);
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panel.setLayout(null);

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
            startButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    startButtonMouseClicked(e);
                }
            });
            panel.add(startButton);
            startButton.setBounds(535, 190, 211, startButton.getPreferredSize().height);

            //---- settingsButton ----
            settingsButton.setBorderPainted(false);
            settingsButton.setContentAreaFilled(false);
            settingsButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_settings.png")));
            settingsButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_settingsH.png")));
            settingsButton.setSelected(true);
            settingsButton.setText("");
            panel.add(settingsButton);
            settingsButton.setBounds(535, 295, 212, 63);

            //---- rulesButton ----
            rulesButton.setBorderPainted(false);
            rulesButton.setContentAreaFilled(false);
            rulesButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_rules.png")));
            rulesButton.setLabel("");
            rulesButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_rulesH.png")));
            rulesButton.setSelected(true);
            rulesButton.setText("");
            panel.add(rulesButton);
            rulesButton.setBounds(535, 395, 211, 59);

            //---- scoreboardButton ----
            scoreboardButton.setBorderPainted(false);
            scoreboardButton.setContentAreaFilled(false);
            scoreboardButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_scoreboard.png")));
            scoreboardButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_scoreboardH.png")));
            scoreboardButton.setSelected(true);
            scoreboardButton.setText("");
            panel.add(scoreboardButton);
            scoreboardButton.setBounds(535, 495, 212, 66);

            //---- exitButton ----
            exitButton.setBorderPainted(false);
            exitButton.setContentAreaFilled(false);
            exitButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_exit.png")));
            exitButton.setLabel("");
            exitButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_exitH.png")));
            exitButton.setSelected(true);
            exitButton.setText("");
            panel.add(exitButton);
            exitButton.setBounds(535, 600, 212, 66);

            //---- panel2 ----
            panel2.setBackground(new Color(65, 4, 0));
            panel2.setEditable(false);
            panel2.setFont(new Font("Luminari", Font.BOLD, 101));
            panel2.setForeground(Color.white);
            panel2.setOpaque(false);
            panel2.setRequestFocusEnabled(false);
            panel2.setSelectionEnd(928);
            panel2.setTabSize(2);
            panel2.setText("Blackjack");
            panel2.setWrapStyleWord(false);
            panel.add(panel2);
            panel2.setBounds(415, 55, 455, panel2.getPreferredSize().height);

            //---- bordet ----
            bordet.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbordsuddigt.png")));
            bordet.setHorizontalAlignment(SwingConstants.CENTER);
            bordet.setMaximumSize(new Dimension(1900, 1080));
            bordet.setPreferredSize(null);
            bordet.setMinimumSize(new Dimension(1900, 1080));
            bordet.setBackground(null);
            bordet.setForeground(null);
            panel.add(bordet);
            bordet.setBounds(-160, -65, 1600, 900);

            panel.setPreferredSize(new Dimension(1300, 775));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel;
    private JButton startButton;
    private JButton settingsButton;
    private JButton rulesButton;
    private JButton scoreboardButton;
    private JButton exitButton;
    private JTextArea panel2;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
