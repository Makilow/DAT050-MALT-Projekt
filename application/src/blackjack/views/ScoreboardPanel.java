package blackjack.views;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.border.*;

import blackjack.controllers.ScoreboardController;
import blackjack.models.MainModel;
import blackjack.Observer;

import javax.swing.*;
import com.intellij.uiDesigner.core.*;
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
        var panelSB = new JPanel();
        bordetSB = new JLabel();

        //======== panelSB ========
        {
            panelSB.setMaximumSize(new Dimension(1600, 864));
            panelSB.setMinimumSize(new Dimension(1600, 864));
            panelSB.setPreferredSize(null);
            panelSB.setLayout(null);

            //---- bordetSB ----
            bordetSB.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbordsuddigt.png")));
            bordetSB.setHorizontalAlignment(SwingConstants.CENTER);
            bordetSB.setMaximumSize(new Dimension(1600, 864));
            bordetSB.setPreferredSize(new Dimension(1600, 864));
            bordetSB.setMinimumSize(new Dimension(1600, 860));
            panelSB.add(bordetSB);
            bordetSB.setBounds(0, 30, 1600, 900);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelSB.getComponentCount(); i++) {
                    Rectangle bounds = panelSB.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelSB.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelSB.setMinimumSize(preferredSize);
                panelSB.setPreferredSize(preferredSize);
            }
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel bordetSB;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
