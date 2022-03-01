package blackjack.views;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.border.*;
import javax.swing.plaf.*;

import blackjack.controllers.ScoreboardController;
import blackjack.models.DatabaseHandler;
import blackjack.models.MainModel;
import blackjack.Observer;
import blackjack.models.Player;

import javax.swing.*;


/**
 * ScoreboardPanel, the panel that showcase the scoreboard
 * @author Lukas Wigren
 */
public class ScoreboardPanel extends JPanel implements Observer<MainModel> {

    public ScoreboardPanel(ScoreboardController scoreboardController) {
        initComponents();
        setLayout(new GridLayout(1, 0));
        showScoreBoard();
        add(panel);
    }

    private void showScoreBoard() {

        //Detta är koden som hämtar från databasen
        /*
        DatabaseHandler dbH = new DatabaseHandler();
        List<Player> list = new ArrayList<>();
        try {
            list = dbH.getScoreBoard();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        */

        //Testkod pga "too many connections" på databas
        List<Player> list = new ArrayList<>();
        list.add(new Player("Tor", 10000));
        list.add(new Player("Tomas", 500));
        list.add(new Player("Arvin", 0));


        for (Player player : list) {
            JLabel l = new JLabel(player.getName() + ": " + (int)player.getBalance());
            l.setAlignmentX(Component.CENTER_ALIGNMENT);
            l.setFont(l.getFont().deriveFont(l.getFont().getSize() + 9f));
            l.setForeground(Color.darkGray);
            scorePanel.add(l);
        }
        scorePanel.revalidate();
    }

    @Override
    public void update(MainModel observable) {
    }

    private void startButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel = new JPanel();
        scorePanel = new JPanel();
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

            //======== scorePanel ========
            {
                scorePanel.setOpaque(false);
                scorePanel.setBackground(Color.black);
                scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
            }
            panel.add(scorePanel);
            scorePanel.setBounds(490, 175, 300, 480);

            //---- exitButton ----
            exitButton.setBorderPainted(false);
            exitButton.setContentAreaFilled(false);
            exitButton.setIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_exit.png")));
            exitButton.setLabel("");
            exitButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/menu_icons/button_exitH.png")));
            exitButton.setSelected(true);
            exitButton.setText("");
            panel.add(exitButton);
            exitButton.setBounds(535, 670, 212, 66);

            //---- panel2 ----
            panel2.setBackground(new Color(65, 4, 0));
            panel2.setEditable(false);
            panel2.setFont(new Font("Luminari", Font.BOLD, 101));
            panel2.setForeground(Color.white);
            panel2.setOpaque(false);
            panel2.setRequestFocusEnabled(false);
            panel2.setSelectionEnd(928);
            panel2.setTabSize(2);
            panel2.setText("Player Scores");
            panel2.setWrapStyleWord(false);
            panel.add(panel2);
            panel2.setBounds(320, 35, 640, panel2.getPreferredSize().height);

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
    private JPanel scorePanel;
    private JButton exitButton;
    private JTextArea panel2;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
