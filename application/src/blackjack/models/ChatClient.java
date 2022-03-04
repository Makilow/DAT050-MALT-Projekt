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

    /*  Method for listening for messages from the server.
        This will be the broadcasted messages from other users.
        New thread will be used because as we'll be listening for messages
        this will be a blocking operation.
        New thread created and pass a runnable object.

        This method will be waiting for messages that are broadcasted from class chatClientHandler, method broadcastMessage.
     */
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromChat;

                while (socket.isConnected()) {
                    try {
                        messageFromChat = bufferedReader.readLine();
                        System.out.println(messageFromChat);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    /*  Method to close everything.
        This method is used to close down the connection and streams.
     */
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Username for the chat: ");
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost", 1234);
        ChatClient chatClient = new ChatClient(socket, username);
        System.out.println("Welcome " + username + "!");
        chatClient.listenForMessage();
        chatClient.sendMessage();
    }
}
