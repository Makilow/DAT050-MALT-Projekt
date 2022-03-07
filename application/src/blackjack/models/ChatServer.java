package blackjack.models;

import java.io.IOException;
import java.net.*;

/**
 * ChatServer, the model that creates a server for communication between clients using TCP stream.
 * @author Arvin Allahbakhsh
 */

public class ChatServer {

    private ServerSocket serverSocket;

    /**  Constructor creating a serverSocket.
     * @param serverSocket takes in serverSocket.
     */
    public ChatServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /** This method, startServer, will be responsible for keeping the server running.
       Server is constantly running until the server socket is closed.
     */
    public void startServer(){
        try {
            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");

                ChatHandler chatHandler = new ChatHandler(socket);

                Thread thread = new Thread(chatHandler);
                thread.start();
            }
        } catch (IOException e) {
             e.printStackTrace();
        }
    }

    /**  Method for handling socket error so that we can avoid nested try-catches
     */
    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        ChatServer chatServer = new ChatServer(serverSocket);
        chatServer.startServer();
    }
}
