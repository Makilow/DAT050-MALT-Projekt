package blackjack.views;

import blackjack.Observer;
import blackjack.controllers.SettingController;
import blackjack.models.MainModel;
import com.intellij.uiDesigner.core.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * SettingPanel, the panel that showcase the settings
 *
 * @author Lukas Wigren, Mark Villarosa
 */

public class SettingPanel extends JPanel implements Observer<MainModel> {

    public SettingPanel(SettingController settingController) {
        initComponents();
        okButton.setActionCommand("OK");
        fullscreenCheckbox.setActionCommand("FULLSCREEN");
        soundCheckbox.setActionCommand("SOUND");
        okButton.addActionListener(settingController);
        resolutions.addItemListener(settingController);
        fullscreenCheckbox.addActionListener(settingController);
        soundCheckbox.addActionListener(settingController);
        music.addItemListener(settingController);
        setLayout(new GridLayout(1, 0));
        add(panel);
    }

    @Override
    public void update(MainModel o) {
        if (o.getState() != State.SETTINGS) {
            return;
        }
        updateBackground(o.getWidth(), o.getHeight());
    }
  
    private void updateBackground(int width, int height) {
        panel.setSize(new Dimension(width, height));
        panel1.setSize(new Dimension(width, height));
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(getClass().getClassLoader().getResource("icons/blackjackbordsuddigt.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Missing file: \"src/icons/blackjackbordsuddigt.png\"");
            System.exit(0);
        }
        Image image = bImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
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
        var hSpacer2 = new Spacer();
        fullscreenCheckbox = new JCheckBox();
        soundLabel = new JLabel();
        soundCheckbox = new JCheckBox();
        music = new JComboBox<>();
        soundLabel2 = new JLabel();
        musicCheckbox = new JCheckBox();
        var vSpacer3 = new Spacer();
        okButton = new JButton();
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
                panel1.setLayout(new GridLayoutManager(12, 9, new Insets(0, 0, 0, 0), -1, -1));
                panel1.add(vSpacer1, new GridConstraints(0, 0, 1, 9,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, new Dimension(0, 100), null));

                //---- title ----
                title.setText("SETTINGS");
                title.setFont(new Font("Times New Roman", Font.BOLD, 31));
                title.setForeground(new Color(255, 153, 204));
                panel1.add(title, new GridConstraints(1, 0, 1, 9,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(hSpacer1, new GridConstraints(1, 0, 8, 1,
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
                resolutions.setBackground(Color.white);
                resolutions.setForeground(Color.red);
                resolutions.setFont(resolutions.getFont().deriveFont(resolutions.getFont().getStyle() | Font.BOLD));
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
                panel1.add(hSpacer2, new GridConstraints(1, 8, 8, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));

                //---- fullscreenCheckbox ----
                fullscreenCheckbox.setOpaque(false);
                fullscreenCheckbox.setBackground(Color.white);
                fullscreenCheckbox.setForeground(Color.white);
                panel1.add(fullscreenCheckbox, new GridConstraints(3, 5, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- soundLabel ----
                soundLabel.setText("Music Sound");
                soundLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
                soundLabel.setForeground(new Color(255, 153, 204));
                panel1.add(soundLabel, new GridConstraints(4, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- soundCheckbox ----
                soundCheckbox.setOpaque(false);
                soundCheckbox.setBackground(Color.white);
                soundCheckbox.setForeground(Color.white);
                panel1.add(soundCheckbox, new GridConstraints(4, 5, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- music ----
                music.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Music 1",
                    "Music 2"
                }));
                music.setBackground(Color.white);
                music.setForeground(Color.red);
                music.setFont(music.getFont().deriveFont(music.getFont().getStyle() | Font.BOLD));
                music.setOpaque(false);
                panel1.add(music, new GridConstraints(4, 6, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- soundLabel2 ----
                soundLabel2.setText("VFX Sound");
                soundLabel2.setFont(new Font("Times New Roman", Font.BOLD, 18));
                soundLabel2.setForeground(new Color(255, 153, 204));
                soundLabel2.setVisible(false);
                panel1.add(soundLabel2, new GridConstraints(5, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- musicCheckbox ----
                musicCheckbox.setOpaque(false);
                musicCheckbox.setBackground(Color.white);
                musicCheckbox.setForeground(Color.white);
                musicCheckbox.setVisible(false);
                panel1.add(musicCheckbox, new GridConstraints(5, 5, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(vSpacer3, new GridConstraints(6, 1, 1, 6,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));

                //---- okButton ----
                okButton.setText("OK");
                panel1.add(okButton, new GridConstraints(7, 1, 1, 6,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(vSpacer2, new GridConstraints(9, 0, 2, 9,
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
    private JComboBox<String> music;
    private JLabel soundLabel2;
    private JCheckBox musicCheckbox;
    private JButton okButton;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
