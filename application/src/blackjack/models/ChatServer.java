package blackjack.models;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    private ServerSocket serverSocket;

    /*  Constructor creating a serverSocket.
     */
    public ChatServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /* This method, startServer, will be responsible for keeping the server running.
       Server is constantly running until the server socket is closed.
     */
    public void startServer(){
        try {
            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");

                ChatClientHandler chatClientHandler = new ChatClientHandler(socket);

                Thread thread = new Thread(chatClientHandler);
                thread.start();
            }
        } catch (IOException e) {
        }
    }

    /*  Method for handling socket error so that we can avoid nested try-catches
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

    public static void main(String[] args) {
        ServerSocket serverSocket = new ServerSocket(1234);
        ChatServer chatServer = new ChatServer(serverSocket);
        chatServer.startServer();
    }
}
