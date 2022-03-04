package blackjack.models;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    /*  Constructor used to instantiate every property.
        It takes a socket object for communication with server or chatClientHandler.
        It also takes a String username to actually represent the client
     */
    public ChatClient(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // Send Text-Data
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));   // Read Text-Data
            this.username = username;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /*  Method to send messages to our ChatClientHandler.
        Basically the connection the server has spawned to handle a client.
     */
    public void sendMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            /* Get what data the user typed into the chat-console with the scanner
                and then send it over.
             */
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(username + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
}
