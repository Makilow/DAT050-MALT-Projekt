package blackjack.views;

import java.awt.event.*;
import javax.imageio.ImageIO;
import blackjack.Observer;
import blackjack.controllers.ChatController;
import blackjack.models.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.intellij.uiDesigner.core.*;



/**
 * RulePanel, the panel that showcase the rules
 *
 * @author Lukas Wigren , Mark Villarosa
 */
public class ChatPanel extends JPanel implements Observer<MainModel> {

    public ChatPanel(ChatController chatController) {
        initComponents();
        returnButton.setActionCommand(State.MENU.toString());
        returnButton.addActionListener(chatController);
        setLayout(new GridLayout(1, 0));
        add(fullPanel);
    }

    @Override
    public void update(MainModel o) {
        if (o.getState() != State.CHAT) {return;}
        updateBackground(o.getWidth(), o.getHeight());
    }
    private void updateBackground(int width, int height) {
        fullPanel.setSize(new Dimension(width,height));
        chatMenuPanel.setSize(new Dimension(width,height));
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

    private void returnButtonKeyPressed(KeyEvent e) {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        fullPanel = new JPanel();
        chatMenuPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        msgArea = new JTextArea();
        scrollPane2 = new JScrollPane();
        msgText = new JTextField();
        enterButton = new JButton();
        returnButton = new JButton();
        bordet = new JLabel();

        //======== fullPanel ========
        {
            fullPanel.setBackground(new Color(51, 51, 51));
            fullPanel.setPreferredSize(new Dimension(1280, 820));
            fullPanel.setMaximumSize(new Dimension(1280, 820));
            fullPanel.setMinimumSize(new Dimension(1280, 820));
            fullPanel.setAlignmentX(0.0F);
            fullPanel.setAlignmentY(0.0F);
            fullPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            fullPanel.setBorder(null);
            fullPanel.setLayout(null);

            //======== chatMenuPanel ========
            {
                chatMenuPanel.setOpaque(false);
                chatMenuPanel.setLayout(new GridLayoutManager(21, 12, new Insets(0, 0, 0, 0), 5, -1));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(msgArea);
                }
                chatMenuPanel.add(scrollPane1, new GridConstraints(1, 1, 9, 5,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //======== scrollPane2 ========
                {

                    //---- msgText ----
                    msgText.setForeground(new Color(153, 153, 153));
                    msgText.setMaximumSize(new Dimension(100, 100));
                    scrollPane2.setViewportView(msgText);
                }
                chatMenuPanel.add(scrollPane2, new GridConstraints(18, 1, 1, 2,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- enterButton ----
                enterButton.setText("ENTER");
                chatMenuPanel.add(enterButton, new GridConstraints(18, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- returnButton ----
                returnButton.setBackground(new Color(65, 4, 0));
                returnButton.setForeground(new Color(65, 4, 0));
                returnButton.setIcon(new ImageIcon(getClass().getResource("/icons/button_return.png")));
                returnButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/button_returnH.png")));
                returnButton.setText("");
                returnButton.setHorizontalTextPosition(SwingConstants.CENTER);
                returnButton.setMaximumSize(new Dimension(174, 46));
                returnButton.setBorderPainted(false);
                returnButton.setSelected(true);
                returnButton.setContentAreaFilled(false);
                returnButton.setVerticalAlignment(SwingConstants.BOTTOM);
                returnButton.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        returnButtonKeyPressed(e);
                    }
                });
                chatMenuPanel.add(returnButton, new GridConstraints(20, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            }
            fullPanel.add(chatMenuPanel);
            chatMenuPanel.setBounds(30, 5, 1200, 800);

            //---- bordet ----
            bordet.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbordsuddigt.png")));
            bordet.setHorizontalAlignment(SwingConstants.LEFT);
            bordet.setMaximumSize(null);
            bordet.setPreferredSize(null);
            bordet.setMinimumSize(null);
            bordet.setVerticalAlignment(SwingConstants.TOP);
            fullPanel.add(bordet);
            bordet.setBounds(0, 0, 1280, 820);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < fullPanel.getComponentCount(); i++) {
                    Rectangle bounds = fullPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = fullPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                fullPanel.setMinimumSize(preferredSize);
                fullPanel.setPreferredSize(preferredSize);
            }
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel fullPanel;
    private JPanel chatMenuPanel;
    private JScrollPane scrollPane1;
    private static JTextArea msgArea;
    private JScrollPane scrollPane2;
    private JTextField msgText;
    private JButton enterButton;
    private JButton returnButton;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
