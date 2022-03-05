package blackjack.views;

import java.awt.event.*;
import javax.imageio.ImageIO;
import blackjack.Observer;
import blackjack.controllers.ChatController;
import blackjack.models.ChatClient;
import blackjack.models.ChatHandler;
import blackjack.models.ChatServer;
import blackjack.models.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

import com.intellij.uiDesigner.core.*;

/**
 * ChatPanel, the panel that showcase the chat.
 * @author Arvin Allahbakhsh
 */
public class ChatPanel extends JPanel implements Observer<MainModel> {

    private ChatServer chatServer;
    private ChatClient chatClient;
    private ChatHandler chatHandler;


    public ChatPanel(ChatController chatController) {
        initComponents();
        returnButton.setActionCommand(State.MENU.toString());
        sendButton.setActionCommand("SEND");
        returnButton.addActionListener(chatController);
        sendButton.addActionListener(chatController);
        setLayout(new GridLayout(1, 0));
        add(panel);
    }

    public void initialize(URL location, ResourceBundle resource) {
        try {
            chatServer = new ChatServer(new ServerSocket(1234));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating the server!");
        }

        //chatHandler.run(msgArea);

    }


    @Override
    public void update(MainModel o) {
        if (o.getState() != State.CHAT) {return;}
        updateBackground(o.getWidth(), o.getHeight());
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

    private void returnButtonKeyPressed(KeyEvent e) {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel = new JPanel();
        panel1 = new JPanel();
        returnButton = new JButton();
        scrollPane1 = new JScrollPane();
        msgArea = new JTextArea();
        scrollPane2 = new JScrollPane();
        msgText = new JTextField();
        sendButton = new JButton();
        bordet = new JLabel();

        //======== panel ========
        {
            panel.setBackground(new Color(51, 51, 51));
            panel.setPreferredSize(new Dimension(1280, 820));
            panel.setMaximumSize(new Dimension(1280, 820));
            panel.setMinimumSize(new Dimension(1280, 820));
            panel.setAlignmentX(0.0F);
            panel.setAlignmentY(0.0F);
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panel.setBorder(null);
            panel.setLayout(null);

            //======== panel1 ========
            {
                panel1.setOpaque(false);
                panel1.setLayout(new GridLayoutManager(9, 18, new Insets(0, 0, 0, 0), 5, -1));

                //---- returnButton ----
                returnButton.setBackground(new Color(65, 4, 0));
                returnButton.setForeground(new Color(65, 4, 0));
                returnButton.setIcon(new ImageIcon(getClass().getResource("/icons/button_return.png")));
                returnButton.setRolloverSelectedIcon(new ImageIcon(getClass().getResource("/icons/button_returnH.png")));
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
                panel1.add(returnButton, new GridConstraints(0, 5, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(msgArea);
                }
                panel1.add(scrollPane1, new GridConstraints(0, 7, 1, 4,
                    GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, new Dimension(600, 400), null));

                //======== scrollPane2 ========
                {

                    //---- msgText ----
                    msgText.setForeground(new Color(153, 153, 153));
                    msgText.setMaximumSize(new Dimension(100, 100));
                    scrollPane2.setViewportView(msgText);
                }
                panel1.add(scrollPane2, new GridConstraints(1, 8, 1, 1,
                    GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, new Dimension(400, 60), null));

                //---- sendButton ----
                sendButton.setText("SEND");
                sendButton.setMinimumSize(new Dimension(100, 20));
                sendButton.setPreferredSize(new Dimension(100, 50));
                panel1.add(sendButton, new GridConstraints(1, 9, 1, 1,
                    GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            }
            panel.add(panel1);
            panel1.setBounds(30, 5, 1200, 800);

            //---- bordet ----
            bordet.setIcon(new ImageIcon(getClass().getResource("/icons/blackjackbordsuddigt.png")));
            bordet.setHorizontalAlignment(SwingConstants.LEFT);
            bordet.setMaximumSize(null);
            bordet.setPreferredSize(null);
            bordet.setMinimumSize(null);
            bordet.setVerticalAlignment(SwingConstants.TOP);
            panel.add(bordet);
            bordet.setBounds(0, 0, 1280, 820);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel.getComponentCount(); i++) {
                    Rectangle bounds = panel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel.setMinimumSize(preferredSize);
                panel.setPreferredSize(preferredSize);
            }
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel;
    private JPanel panel1;
    private JButton returnButton;
    private JScrollPane scrollPane1;
    private static JTextArea msgArea;
    private JScrollPane scrollPane2;
    private JTextField msgText;
    private JButton sendButton;
    private JLabel bordet;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
