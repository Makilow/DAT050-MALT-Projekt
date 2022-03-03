package blackjack.views;

import javax.imageio.ImageIO;
import javax.swing.border.*;
import blackjack.Observer;
import blackjack.controllers.ScoreboardController;
import blackjack.controllers.SettingController;
import blackjack.models.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.intellij.uiDesigner.core.*;

/**
 * SettingPanel, the panel that showcase the settings
 * @author Lukas Wigren
 */

public class SettingPanel extends JPanel implements Observer<MainModel> {

    public SettingPanel(SettingController settingController) {
        initComponents();
        okButton.setActionCommand("OK");
        cancelButton.setActionCommand("CANCEL");
        fullscreenCheckbox.setActionCommand("FULLSCREEN");
        soundCheckbox.setActionCommand("SOUND");
        okButton.addActionListener(settingController);
        cancelButton.addActionListener(settingController);
        resolutions.addItemListener(settingController);
        fullscreenCheckbox.addActionListener(settingController);
        soundCheckbox.addActionListener(settingController);
        setLayout(new GridLayout(1, 0));
        add(panel);
    }

    @Override
    public void update(MainModel o) {
        if (o.getState() != State.SETTINGS) {return;}
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
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel = new JPanel();
        panel1 = new JPanel();
        var vSpacer1 = new Spacer();
        title = new JLabel();
        var hSpacer1 = new Spacer();
        resolutionLabel = new JLabel();
        resolutions = new JComboBox<>();
        fullscreenLabel = new JLabel();
        fullscreenCheckbox = new JCheckBox();
        var hSpacer2 = new Spacer();
        soundLabel = new JLabel();
        soundCheckbox = new JCheckBox();
        var vSpacer3 = new Spacer();
        okButton = new JButton();
        cancelButton = new JButton();
        var vSpacer2 = new Spacer();
        bordet = new JLabel();

        //======== panel ========
        {
            panel.setBackground(new Color(7, 65, 0));
            panel.setPreferredSize(null);
            panel.setMaximumSize(null);
            panel.setMinimumSize(null);
            panel.setAlignmentX(0.0F);
            panel.setAlignmentY(0.0F);
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panel.setBorder(new TitledBorder("text"));
            panel.setOpaque(false);
            panel.setLayout(null);

            //======== panel1 ========
            {
                panel1.setOpaque(false);
                panel1.setLayout(new GridLayoutManager(11, 8, new Insets(0, 0, 0, 0), -1, -1));
                panel1.add(vSpacer1, new GridConstraints(0, 0, 1, 8,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, new Dimension(0, 100), null));

                //---- title ----
                title.setText("SETTINGS");
                title.setFont(new Font("Times New Roman", Font.BOLD, 31));
                title.setForeground(new Color(255, 153, 204));
                panel1.add(title, new GridConstraints(1, 0, 1, 8,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(hSpacer1, new GridConstraints(1, 0, 7, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));

                //---- resolutionLabel ----
                resolutionLabel.setText("Screen resolution");
                resolutionLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
                resolutionLabel.setForeground(new Color(255, 153, 204));
                panel1.add(resolutionLabel, new GridConstraints(2, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- resolutions ----
                resolutions.setModel(new DefaultComboBoxModel<>(new String[] {
                    "1280x720",
                    "1600x900",
                    "1920x1080"
                }));
                resolutions.setOpaque(false);
                panel1.add(resolutions, new GridConstraints(2, 6, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- fullscreenLabel ----
                fullscreenLabel.setText("Fullscreen");
                fullscreenLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
                fullscreenLabel.setForeground(new Color(255, 153, 204));
                panel1.add(fullscreenLabel, new GridConstraints(3, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- fullscreenCheckbox ----
                fullscreenCheckbox.setOpaque(false);
                panel1.add(fullscreenCheckbox, new GridConstraints(3, 6, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(hSpacer2, new GridConstraints(1, 7, 7, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));

                //---- soundLabel ----
                soundLabel.setText("VFX ON/OFF");
                soundLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
                soundLabel.setForeground(new Color(255, 153, 204));
                panel1.add(soundLabel, new GridConstraints(4, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- soundCheckbox ----
                soundCheckbox.setOpaque(false);
                panel1.add(soundCheckbox, new GridConstraints(4, 6, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(vSpacer3, new GridConstraints(5, 1, 1, 6,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));

                //---- okButton ----
                okButton.setText("OK");
                panel1.add(okButton, new GridConstraints(6, 1, 1, 6,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- cancelButton ----
                cancelButton.setText("CANCEL");
                panel1.add(cancelButton, new GridConstraints(7, 1, 1, 6,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(vSpacer2, new GridConstraints(8, 0, 2, 8,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, new Dimension(0, 100), null));
            }
            panel.add(panel1);
            panel1.setBounds(0, 0, 740, 525);

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

            panel.setPreferredSize(new Dimension(1105, 835));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel;
    private JPanel panel1;
    private JLabel title;
    private JLabel resolutionLabel;
    private JComboBox<String> resolutions;
    private JLabel fullscreenLabel;
    private JCheckBox fullscreenCheckbox;
    private JLabel soundLabel;
    private JCheckBox soundCheckbox;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}