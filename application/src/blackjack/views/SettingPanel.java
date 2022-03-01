package blackjack.views;

import javax.swing.border.*;
import blackjack.Observer;
import blackjack.controllers.SettingController;
import blackjack.models.MainModel;

import javax.swing.*;
import java.awt.*;

/**
 * SettingPanel, the panel that showcase the settings
 * @author Lukas Wigren
 */
public class SettingPanel extends JPanel implements Observer<MainModel> {

    public SettingPanel(SettingController settingController) {

        initComponents();
        add(bordetS);
    }

    @Override
    public void update(MainModel observable) {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        var panelS = new JPanel();
        bordetS = new JLabel();

        //======== panelS ========
        {
            panelS.setMaximumSize(new Dimension(1280, 720));
            panelS.setMinimumSize(null);
            panelS.setPreferredSize(new Dimension(1280, 720));
            panelS.setAlignmentX(0.0F);
            panelS.setAlignmentY(0.0F);
            panelS.setBackground(new Color(7, 65, 0));
            panelS.setBorder(new TitledBorder("text"));
            panelS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panelS.setLayout(null);

            //---- bordetS ----
            bordetS.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbordsuddigt.png")));
            bordetS.setHorizontalAlignment(SwingConstants.CENTER);
            bordetS.setMaximumSize(new Dimension(1900, 1080));
            bordetS.setPreferredSize(null);
            bordetS.setMinimumSize(new Dimension(1900, 1080));
            bordetS.setBackground(null);
            bordetS.setForeground(null);
            panelS.add(bordetS);
            bordetS.setBounds(-160, -65, 1600, 900);

            panelS.setPreferredSize(new Dimension(1440, 835));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel bordetS;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
