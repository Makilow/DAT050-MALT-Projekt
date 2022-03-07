package blackjack.controllers;

import blackjack.models.MainModel;
import blackjack.views.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * ChatController, a controller for ChatPanel
 * @author  Arvin Allahbakhsh, Lukas Wigren
 */
public class ChatController implements ActionListener, KeyListener {
    private MainModel mainModel;
    private String message = "";
    
    /**
     * Constructor for ChatController
     * @param mainModel the MainModel
     */
    public ChatController(MainModel mainModel) {
        this.mainModel = mainModel;
    }
    /**
     * Actionlistener for Chatcontroller
     * @param e ActionEvent
     */
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
    /**
     * Keytyped listener, adds char to message
     * @param e KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {
        message += e.getKeyChar();
    }
    /**
     * Key pressed, unused
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }
    /**
     * Key released, unused
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
