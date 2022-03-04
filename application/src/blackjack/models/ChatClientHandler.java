package blackjack.models;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ChatClientHandler implements Runnable{

    /*  Static ArrayList of every chatClientHandler object that we have instantiated.
        Keep track of all our clients so that whenever a client sends a message we can loop through ArrayList of
        clients and send a message to each client.
        Responsible for allowing to communicate messages to multiple clients.
    */
    public static ArrayList<ChatClientHandler> chatClientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    /*  Constructor
        Socket represents a connecting between server - clientHandler - client.
        Each socket connection has an OutputStream used to SEND data to server
        and a InputStream used to READ data from server.
        Using Buffer which will make the communication more efficient.
    */
    public ChatClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // Send Text-Data
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));   // Read Text-Data
            this.clientUsername = bufferedReader.readLine();
            chatClientHandlers.add(this);
            broadcastMessage("SERVER: " + clientUsername + " has entered the chat.");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    /*
        This method is run on a separated thread, listening for messages.
        Blocking operation - the program will be stuck until the operation is completed.
        If multiple-threads not used, program will be stuck waiting for a message from a client.
        So instead - use a separated thread waiting for messages and another one working with the rest of application
     */
    @Override
    public void run() {
        String messageFromChatClient;

        while (socket.isConnected()) {
            try {
                messageFromChatClient = bufferedReader.readLine();
                broadcastMessage(messageFromChatClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }


    /*
        broadcastMessage-method which will be used to send the message client wrote
        to everyone in the chat.
        Method takes the message that we want to send.
        Looping through chatClientHandlers ArrayList to send message to every client connected.
     */
    public void broadcastMessage(String messageToSend) {
        for(ChatClientHandler chatClientHandler : chatClientHandlers) {
            try {
                if(!chatClientHandler.clientUsername.equals(clientUsername)) { // to not send message back to the sender
                    chatClientHandler.bufferedWriter.write(messageToSend);
                    chat
                }
            }
        }

    }
}
