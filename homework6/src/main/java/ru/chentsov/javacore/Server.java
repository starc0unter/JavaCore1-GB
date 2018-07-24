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

    private final int port;

    public Server(final int serverPort) {
        this.port = serverPort;
    }

    public static void main(String[] args) {
        final Server server = new Server(8841);
        server.initServer(server.port);
    }

    public void initServer(final int serverPort) {
        try (
                final ServerSocket serverSocket = new ServerSocket(serverPort);
                final Socket socket = serverSocket.accept();
                final Scanner socketInputScanner = new Scanner(socket.getInputStream());
                PrintWriter socketOutputWriter = new PrintWriter(socket.getOutputStream())
        ) {
            System.out.println("Server is up and client is connected");
            Session.startSession(socketInputScanner, socketOutputWriter, "Server");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server has been shut down");
    }

}
