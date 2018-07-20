package ru.chentsov.javacore;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Evgenii Chentsov
 */
public final class Server {

    private int port;

    public Server(final int serverPort) {
        this.port = serverPort;
    }

    public static void main(String[] args) {
        Server server = new Server(8841);
        server.initServer(server.port);
    }

    public void initServer(final int serverPort) {
        try (
                ServerSocket serverSocket = new ServerSocket(serverPort);
                Socket socket = serverSocket.accept();
                Scanner socketInputScanner = new Scanner(socket.getInputStream());
                PrintWriter socketOutputWriter = new PrintWriter(socket.getOutputStream())
        ) {
            System.out.println("Server is up and client is connected");
            new Session().startSession(socketInputScanner, socketOutputWriter, "Server");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server has been shut down");
    }

}
