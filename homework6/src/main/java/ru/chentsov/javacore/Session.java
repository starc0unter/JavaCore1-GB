package ru.chentsov.javacore;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Evgenii Chentsov
 * Current class is used to send and receive message by hosts. According to proposed
 * protocol, all message senders are pushed to concurrent thread.
 */
public final class Session {

    private static final String END_SESSION_MESSAGE =  "end session";

    public void startSession(Scanner in, PrintWriter out, String clientName) {
        final Thread sendMessageThread = new Thread(() -> sendMessages(out, clientName));
        sendMessageThread.setDaemon(true);
        sendMessageThread.start();
        listenForMessages(in);
    }

    private void sendMessages(final PrintWriter out, final String senderName) {
        try (Scanner messageScanner = new Scanner(System.in)) {
            String clientMessage;
            while (!END_SESSION_MESSAGE.equals(clientMessage = messageScanner.nextLine())) {
                out.println(senderName + ": " + clientMessage);
                out.flush();
            }
            out.println(clientMessage);
            out.flush();
        }
    }

    private void listenForMessages(final Scanner in) {
        while (in.hasNext()) {
            String serverMessage = in.nextLine();
            if (END_SESSION_MESSAGE.equals(serverMessage)) {
                return;
            }
            System.out.println(serverMessage);
        }
    }

}
