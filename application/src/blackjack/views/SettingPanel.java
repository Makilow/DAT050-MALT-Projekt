package blackjack.views;

import javax.swing.border.*;
import blackjack.Observer;
import blackjack.controllers.ScoreboardController;
import blackjack.controllers.SettingController;
import blackjack.models.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * SettingPanel, the panel that showcase the settings
 * @author Lukas Wigren
 */

public class SettingPanel extends JPanel implements Observer<MainModel> {

    public SettingPanel(SettingController settingController) {
            initComponents();
            setLayout(new GridLayout(1, 0));
            add(panel1);
        }

    @Override
    public void update(MainModel observable) {


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
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
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}