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
    private JScrollPane viewMessageScroll;
    private JTextArea viewMessageArea;
    private JPanel sendMessagePanel;
    private JButton sendMessageButton;
    private JScrollPane sendMessageScroll;
    private JTextField sendMessageField;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;

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
        viewMessageArea = new JTextArea();
        viewMessageArea.setLineWrap(true);
        viewMessageArea.setEditable(false);
        viewMessageScroll = new JScrollPane(viewMessageArea);

        add(viewMessageScroll, BorderLayout.CENTER);
    }

    private void addSendMessagePanel() {
        sendMessageField = new JTextField(DEFAULT_MESSAGE_TEXT, 27);
        sendMessageField.addFocusListener(getMessageSendFocusListener());
        sendMessageField.addActionListener(e -> sendMessage());

        sendMessageScroll = new JScrollPane(sendMessageField);
        sendMessageScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        sendMessageButton = new JButton("Send");
        sendMessageButton.addActionListener(e -> sendMessage());

        sendMessagePanel = new JPanel(new BorderLayout());
        sendMessagePanel.add(sendMessageScroll, BorderLayout.CENTER);
        sendMessagePanel.add(sendMessageButton, BorderLayout.EAST);

        getContentPane().add(BorderLayout.SOUTH, sendMessagePanel);
    }

    private void addMenuBar() {
        menuBar = new JMenuBar();
        menu = new JMenu("Window");
        menuBar.add(menu);

        menuItem = new JMenuItem("Clear");
        menuItem.addActionListener(e -> viewMessageArea.setText(""));
        menu.add(menuItem);

        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(e -> System.exit(0));
        menu.add(menuItem);

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
            public void focusGained(FocusEvent e) {
                if (DEFAULT_MESSAGE_TEXT.equals(sendMessageField.getText()))  sendMessageField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (sendMessageField.getText().isEmpty()) sendMessageField.setText(DEFAULT_MESSAGE_TEXT);
            }
        };
    }

}
