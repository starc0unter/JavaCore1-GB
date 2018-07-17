package ru.chentsov.javacore.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*Создать окно для клиентской части чата: большое текстовое поле для отображения переписки
в центре окна. Однострочное текстовое поле для ввода сообщений и кнопка для отсылки
сообщений на нижней панели. Сообщение должно отсылаться либо по нажатию кнопки на
форме, либо по нажатию кнопки Enter. При «отсылке» сообщение перекидывается из нижнего*/

/**
 * @author Evgenii Chentsov
 */
public class ChatWindow extends JFrame {

    private final static String DEFAULT_MESSAGE_TEXT = "Write a message... ";

    private final JTextArea viewMessageArea = new JTextArea();
    private final JScrollPane viewMessageScroll  = new JScrollPane(viewMessageArea);
    private final JPanel sendMessagePanel = new JPanel(new BorderLayout());
    private final JButton sendMessageButton = new JButton("Send");
    private final JTextField sendMessageField = new JTextField(DEFAULT_MESSAGE_TEXT, 27);
    private final JScrollPane sendMessageScroll = new JScrollPane(sendMessageField);

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menu = new JMenu("Window");
    private final JMenuItem menuItemClear = new JMenuItem("Clear");
    private final JMenuItem menuItemExit = new JMenuItem("Exit");

    public ChatWindow() {
        setTitle ("Chat Window");
        setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);
        setBounds (300 , 300 , 400 , 300);
        setMinimumSize(new Dimension(400, 300));

        addViewMessageArea();
        addSendMessagePanel();
        addMenuBar();
    }

    private void addViewMessageArea() {
        viewMessageArea.setLineWrap(true);
        viewMessageArea.setEditable(false);

        add(viewMessageScroll, BorderLayout.CENTER);
    }

    private void addSendMessagePanel() {
        sendMessageField.addFocusListener(getMessageSendFocusListener());
        sendMessageField.addActionListener(e -> sendMessage());

        sendMessageScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        sendMessageButton.addActionListener(e -> sendMessage());

        sendMessagePanel.add(sendMessageScroll, BorderLayout.CENTER);
        sendMessagePanel.add(sendMessageButton, BorderLayout.EAST);

        getContentPane().add(BorderLayout.SOUTH, sendMessagePanel);
    }

    private void addMenuBar() {
        menuBar.add(menu);

        menuItemClear.addActionListener(e -> viewMessageArea.setText(""));
        menu.add(menuItemClear);

        menuItemExit.addActionListener(e -> System.exit(0));
        menu.add(menuItemExit);

        add(menuBar, BorderLayout.NORTH);
    }

    private void sendMessage() {
        final boolean hasDefaultMessageText = sendMessageField.getText().equals(DEFAULT_MESSAGE_TEXT);
        final boolean isMessageEmpty = sendMessageField.getText().isEmpty();
        if (hasDefaultMessageText || isMessageEmpty) return;
        final String chatText = viewMessageArea.getText() + sendMessageField.getText() + System.lineSeparator();
        viewMessageArea.setText(chatText);
        sendMessageField.setText("");
    }

    private FocusListener getMessageSendFocusListener() {
        return new FocusListener() {
            @Override
            public void focusGained(final FocusEvent e) {
                if (DEFAULT_MESSAGE_TEXT.equals(sendMessageField.getText()))  sendMessageField.setText("");
            }

            @Override
            public void focusLost(final FocusEvent e) {
                if (sendMessageField.getText().isEmpty()) sendMessageField.setText(DEFAULT_MESSAGE_TEXT);
            }
        };
    }

}
