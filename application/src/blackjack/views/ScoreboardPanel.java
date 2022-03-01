package blackjack.views;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.border.*;

import blackjack.controllers.ScoreboardController;
import blackjack.models.MainModel;
import blackjack.Observer;

import javax.swing.*;
/**
 * ScoreboardPanel, the panel that showcase the scoreboard
 * @author Lukas Wigren
 */
public class ScoreboardPanel extends JPanel implements Observer<MainModel> {

    public ScoreboardPanel(ScoreboardController scoreboardController) {
        initComponents();
        setLayout(new GridLayout(1, 0));
        add(bordetSB);
    }

    @Override
    public void update(MainModel observable) {
    }

    private void startButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panelSB = new JPanel();
        bordetSB = new JLabel();

        //======== panelSB ========
        {
            panelSB.setMaximumSize(new Dimension(1280, 720));
            panelSB.setMinimumSize(null);
            panelSB.setPreferredSize(new Dimension(1280, 720));
            panelSB.setAlignmentX(0.0F);
            panelSB.setAlignmentY(0.0F);
            panelSB.setBackground(new Color(7, 65, 0));
            panelSB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panelSB.setBorder(new TitledBorder("text"));
            panelSB.setLayout(null);

            panelSB.setPreferredSize(new Dimension(1535, 900));
        }

        //---- bordetSB ----
        bordetSB.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbordsuddigt.png")));
        bordetSB.setHorizontalAlignment(SwingConstants.CENTER);
        bordetSB.setMaximumSize(new Dimension(1900, 1080));
        bordetSB.setPreferredSize(null);
        bordetSB.setMinimumSize(new Dimension(1900, 1080));
        bordetSB.setBackground(null);
        bordetSB.setForeground(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panelSB;
    private JLabel bordetSB;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
