package blackjack.controllers;

import blackjack.models.ChatClient;
import blackjack.models.ChatHandler;
import blackjack.models.ChatServer;
import blackjack.models.MainModel;
import blackjack.views.ChatPanel;
import blackjack.views.State;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * ChatController, a controller for ChatPanel
 * @author  Arvin Allahbakhsh
 */
public class ChatController implements ActionListener, KeyListener {

    private MainModel mainModel;
    private String message = "";

    public ChatController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "MENU" -> mainModel.setState(State.MENU);
            case "SEND"-> {
                mainModel.sendMessage(message);
                message = "";
            }
            default -> {
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        message += e.getKeyChar();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
