package blackjack.views;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.border.*;
import javax.swing.plaf.*;

import blackjack.controllers.ScoreboardController;
import blackjack.models.DatabaseHandler;
import blackjack.models.MainModel;
import blackjack.Observer;
import blackjack.models.Player;

import javax.swing.*;
import com.intellij.uiDesigner.core.*;


/**
 * ScoreboardPanel, the panel that showcase the scoreboard
 * @author Lukas Wigren
 */
public class ScoreboardPanel extends JPanel implements Observer<MainModel> {

    public ScoreboardPanel(ScoreboardController scoreboardController) {
        initComponents();
        showScoreBoard();
        exitButton.addActionListener(scoreboardController);
        exitButton.setActionCommand("EXIT");
        setLayout(new GridLayout(1, 0));
        add(panel);
    }

    private void showScoreBoard() {

        //Hämtar lista från database, sorterad efter score
        DatabaseHandler dbH = new DatabaseHandler();
        List<Player> list = new ArrayList<>();
        try {
            list = dbH.getScoreBoard();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*
        //Testkod som kan köras ifall"too many connections" på databasen
        List<Player> list = new ArrayList<>();
        list.add(new Player("Tor", 10000));
        list.add(new Player("Tomas", 500));
        list.add(new Player("Arvin", 0));
        */

        //Skapar och lägger till en JLabel för varje spelare
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
    public void update(MainModel o) {
        if (o.getState() != State.SCOREBOARD) {return;}
        updateBackground(o.getWidth(),o.getHeight());
    }
    private void updateBackground(int width, int height) {
        panel.setSize(new Dimension(width,height));
        panel1.setSize(new Dimension(width,height));
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(new File("src/icons/blackjackbordsuddigt.png"));
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
        var vSpacer1 = new Spacer();
        scorePanel = new JPanel();
        exitButton = new JButton();
        panel2 = new JTextArea();
        var hSpacer1 = new Spacer();
        var hSpacer2 = new Spacer();
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
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panel.setLayout(null);

            //======== panel1 ========
            {
                panel1.setPreferredSize(null);
                panel1.setOpaque(false);
                panel1.setMinimumSize(null);
                panel1.setMaximumSize(null);
                panel1.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), 5, -1));
                panel1.add(vSpacer1, new GridConstraints(0, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, new Dimension(0, 50), null));

                //======== scorePanel ========
                {
                    scorePanel.setOpaque(false);
                    scorePanel.setBackground(Color.black);
                    scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
                }
                panel1.add(scorePanel, new GridConstraints(2, 1, 1, 1,
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
                panel1.add(exitButton, new GridConstraints(3, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

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
                panel1.add(panel2, new GridConstraints(1, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(hSpacer1, new GridConstraints(2, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));
                panel1.add(hSpacer2, new GridConstraints(2, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));
                panel1.add(vSpacer2, new GridConstraints(4, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, new Dimension(0, 50), null));
            }
            panel.add(panel1);
            panel1.setBounds(new Rectangle(new Point(0, 0), panel1.getPreferredSize()));

            //---- bordet ----
            bordet.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbordsuddigt.png")));
            bordet.setHorizontalAlignment(SwingConstants.LEFT);
            bordet.setMaximumSize(new Dimension(1900, 1080));
            bordet.setPreferredSize(null);
            bordet.setMinimumSize(new Dimension(1900, 1080));
            bordet.setBackground(null);
            bordet.setForeground(null);
            bordet.setVerticalAlignment(SwingConstants.TOP);
            panel.add(bordet);
            bordet.setBounds(new Rectangle(new Point(0, 0), bordet.getPreferredSize()));

            panel.setPreferredSize(new Dimension(1300, 775));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel;
    private JPanel panel1;
    private JPanel scorePanel;
    private JButton exitButton;
    private JTextArea panel2;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
