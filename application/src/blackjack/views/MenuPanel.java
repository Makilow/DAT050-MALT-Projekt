package blackjack.views;

import java.awt.event.*;
import javax.swing.border.*;
import blackjack.Observer;
import blackjack.controllers.MenuController;
import blackjack.models.MainModel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import com.intellij.uiDesigner.core.*;


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
        var texten = new JLabel();
        bordet = new JLabel();

        //======== panel ========
        {
            panel.setBackground(new Color(7, 65, 0));
            panel.setEnabled(true);
            panel.setInheritsPopupMenu(false);
            panel.setBorder(new TitledBorder(BorderFactory.createEmptyBorder(), ""));
            panel.setMinimumSize(new Dimension(960, 600));
            panel.setMaximumSize(new Dimension(1500, 800));
            panel.setPreferredSize(null);
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
            startButton.setBounds(735, 215, 212, startButton.getPreferredSize().height);

            //---- settingsButton ----
            settingsButton.setBorderPainted(false);
            settingsButton.setContentAreaFilled(false);
            settingsButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_settings.png")));
            settingsButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_settingsH.png")));
            settingsButton.setSelected(true);
            settingsButton.setText("");
            panel.add(settingsButton);
            settingsButton.setBounds(735, 320, 212, 66);

            //---- rulesButton ----
            rulesButton.setBorderPainted(false);
            rulesButton.setContentAreaFilled(false);
            rulesButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_rules.png")));
            rulesButton.setLabel("");
            rulesButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_rulesH.png")));
            rulesButton.setSelected(true);
            rulesButton.setText("");
            panel.add(rulesButton);
            rulesButton.setBounds(735, 430, 212, 66);

            //---- scoreboardButton ----
            scoreboardButton.setBorderPainted(false);
            scoreboardButton.setContentAreaFilled(false);
            scoreboardButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_scoreboard.png")));
            scoreboardButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_scoreboardH.png")));
            scoreboardButton.setSelected(true);
            scoreboardButton.setText("");
            panel.add(scoreboardButton);
            scoreboardButton.setBounds(740, 535, 212, 66);

            //---- exitButton ----
            exitButton.setBorderPainted(false);
            exitButton.setContentAreaFilled(false);
            exitButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_exit.png")));
            exitButton.setLabel("");
            exitButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_exitH.png")));
            exitButton.setSelected(true);
            exitButton.setText("");
            panel.add(exitButton);
            exitButton.setBounds(740, 640, 212, 66);

            //---- texten ----
            texten.setBackground(new Color(176, 178, 180));
            texten.setFont(new Font("Superclarendon", Font.BOLD, 74));
            texten.setForeground(Color.white);
            texten.setHorizontalAlignment(SwingConstants.CENTER);
            texten.setHorizontalTextPosition(SwingConstants.CENTER);
            texten.setOpaque(false);
            texten.setText("BLACKJACK");
            texten.setMaximumSize(new Dimension(5600, 9400));
            panel.add(texten);
            texten.setBounds(565, 110, texten.getPreferredSize().width, 80);

            //---- bordet ----
            bordet.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbordsuddigt.png")));
            bordet.setHorizontalAlignment(SwingConstants.CENTER);
            bordet.setMaximumSize(new Dimension(1500, 864));
            bordet.setPreferredSize(new Dimension(1500, 864));
            bordet.setMinimumSize(new Dimension(1500, 860));
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
    private JButton startButton;
    private JButton settingsButton;
    private JButton rulesButton;
    private JButton scoreboardButton;
    private JButton exitButton;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
