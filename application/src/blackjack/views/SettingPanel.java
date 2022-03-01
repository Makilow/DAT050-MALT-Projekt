package blackjack.views;

import java.awt.*;
import java.awt.event.*;
import blackjack.controllers.SettingController;
import blackjack.models.MainModel;
import blackjack.Observer;

import javax.swing.*;
import com.intellij.uiDesigner.core.*;

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
            panelS.setMaximumSize(new Dimension(1600, 864));
            panelS.setMinimumSize(new Dimension(1600, 864));
            panelS.setPreferredSize(null);
            panelS.setLayout(null);

            //---- bordetS ----
            bordetS.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbordsuddigt.png")));
            bordetS.setHorizontalAlignment(SwingConstants.CENTER);
            bordetS.setMaximumSize(new Dimension(1600, 864));
            bordetS.setPreferredSize(new Dimension(1600, 864));
            bordetS.setMinimumSize(new Dimension(1600, 860));
            panelS.add(bordetS);
            bordetS.setBounds(0, 30, 1600, 900);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelS.getComponentCount(); i++) {
                    Rectangle bounds = panelS.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelS.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelS.setMinimumSize(preferredSize);
                panelS.setPreferredSize(preferredSize);
            }
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel bordetS;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
