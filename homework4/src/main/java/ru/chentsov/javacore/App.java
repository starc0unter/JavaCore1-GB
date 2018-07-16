package ru.chentsov.javacore;

import ru.chentsov.javacore.windows.ChatWindow;

/**
 * @author Evgenii Chentsov
 */
public class App 
{
    public static void main(String[] args)
    {
        final ChatWindow chatWindow = new ChatWindow();
        chatWindow.setVisible(true);
    }
}
