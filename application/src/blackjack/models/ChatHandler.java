package blackjack.models;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ChatHandler, the model that showcase the logic between server and client.
 * Responsible for allowing to communicate messages to multiple clients.
 * @author Arvin Allahbakhsh
 */

public class ChatHandler implements Runnable{

    /**  Static ArrayList of every chatClientHandler object that we have instantiated.
        Keep track of all our clients so that whenever a client sends a message we can loop through ArrayList of
        clients and send a message to each client.
        Responsible for allowing to communicate messages to multiple clients.
    */
    public static ArrayList<ChatHandler> chatHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    /**  Constructor
        Socket represents a connecting between server - clientHandler - client.
        Each socket connection has an OutputStream used to SEND data to server
        and a InputStream used to READ data from server.
        Using Buffer which will make the communication more efficient.
        @param socket takes in socket connection.
    */
    public ChatHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // Send Text-Data
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));   // Read Text-Data
            this.clientUsername = bufferedReader.readLine();
            chatHandlers.add(this);
            broadcastMessage("SERVER: " + clientUsername + " has entered the chat!");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**  This method is run on a separated thread, listening for messages.
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

    /**  broadcastMessage-method which will be used to send the message client wrote
        to everyone in the chat.
        Method takes the message that we want to send.
        Looping through chatClientHandlers ArrayList to send message to every client connected.
        @param messageToSend takes in string to broadcast message.
     */
    public void broadcastMessage(String messageToSend) {
        for(ChatHandler chatHandler : chatHandlers) {
            try {
                if(chatHandler != this) { // to not send message back to the sender
                    chatHandler.bufferedWriter.write(messageToSend);
                    chatHandler.bufferedWriter.newLine();
                    chatHandler.bufferedWriter.flush(); // manually flush bufferedwriter, else the buffer wont reset
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    /**  Method to signal that a user has disconnected from the chat.
        Remove the client from the arrayList
     */
    public void removeChatClientHandler() {
        chatHandlers.remove(this);
        broadcastMessage("SERVER: " + clientUsername + " has left the chat!");
    }

    /**  Method to close everything.
        This method is used to close down the connection and streams.
        @param socket takes in socket to close connection.
        @param bufferedReader takes in stream to close the connection.
        @param bufferedWriter takes in stream to close the connection.
     */
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeChatClientHandler();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
