package blackjack.controllers;

import blackjack.models.ChatClient;
import blackjack.models.ChatHandler;
import blackjack.models.ChatServer;
import blackjack.models.MainModel;
import blackjack.views.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * ChatController, a controller for ChatPanel
 * @author  Arvin Allahbakhsh
 */
public class ChatController implements ActionListener {

    private MainModel mainModel;
    private ChatClient chatClient;
    private ChatServer chatServer;
    private ChatHandler chatHandler;

    public ChatController(MainModel mainModel) {this.mainModel = mainModel;}

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "MENU" -> mainModel.setState(State.MENU);
            case "SEND"-> chatClient.sendMessage();
            default -> {
            }
        }
    }
}
