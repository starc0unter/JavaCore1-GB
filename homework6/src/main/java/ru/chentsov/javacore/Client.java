package ru.chentsov.javacore;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Evgenii Chentsov
 */
public final class Client {

    private String serverAddress;
    private int serverPort;

    public Client(final String serverAddress, final int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public static void main(String[] args) {
        final Client client = new Client("localhost", 8841);
        client.connectToServer(client.serverAddress, client.serverPort);
    }

    public void connectToServer(final String serverAddress, final int serverPort) {
        try (
                final Socket socket = new Socket(serverAddress, serverPort);
                final Scanner socketInputScanner = new Scanner(socket.getInputStream());
                final PrintWriter socketOutputWriter = new PrintWriter(socket.getOutputStream())
        ) {
            Session.startSession(socketInputScanner, socketOutputWriter, "Client");
        } catch (IOException e) {
            System.out.println("no server found");
        }
    }

}
