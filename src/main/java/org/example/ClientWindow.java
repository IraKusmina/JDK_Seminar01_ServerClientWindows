package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ClientWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea textArea = new JTextArea();
    ServerWindow serverWindow = new ServerWindow();

    private final JPanel panelTop = new JPanel(new GridLayout(2,3));
    private final JTextField tfIP = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tflogin = new JTextField("ivan");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    String message;
    String PATH_HISTORY_MESSAGE = "history_message.txt";
    String PATH_LOG = "log.txt";

    Logger logger = Logger.getLogger(ClientWindow.class.getName());
    FileHandler fileHandler = new FileHandler(PATH_LOG, true);

    ClientWindow() throws IOException {
        logger.addHandler(fileHandler);
        SimpleFormatter sFormat = new SimpleFormatter();
        fileHandler.setFormatter(sFormat);
        setButtom();
        setActionOfButtom();
    }

    private void setActionOfButtom() {
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMessage();
            }
        });

        tfMessage.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == e.VK_ENTER) {getMessage();}
            }
        });
    }

    private void setButtom() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        panelTop.add(tfIP);
        panelTop.add(tfPort);
        panelTop.add(tflogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        panelBottom.setFocusable(true);
        add(panelBottom, BorderLayout.SOUTH);

        textArea.setEditable(true);
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(false);
        JScrollPane scrollLog = new JScrollPane(textArea);
        add(scrollLog);

        setVisible(true);

        getHistoryMessage(PATH_HISTORY_MESSAGE);

//        if (serverWindow.getIsServerWorking()){
//            getHistoryMessage(PATH_HISTORY_MESSAGE);
//        }
    }

    public void getMessage(){
        message = tflogin.getText() + ": " + tfMessage.getText() + "\n";
        logger.log(Level.INFO, message);
        textArea.append(message);
        recordToFile(PATH_HISTORY_MESSAGE, message);
        tfMessage.setText("");
    }

    public static void recordToFile(String path, String message){

            try (FileWriter fileWriter = new FileWriter(path, true)) {
                fileWriter.write(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void getHistoryMessage(String path){
        File f = new File(path);
        if (f.exists()) {
            StringBuilder result = new StringBuilder();
            try(BufferedReader reader= new BufferedReader(new FileReader(path))){
                String line;

                while((line = reader.readLine()) != null) {
                    result.append(line);
                    result.append('\n');
                }
            } catch(IOException e){
                e.printStackTrace();
            }
            textArea.setText(result.toString());
        } else textArea.setText("История отсутствует");
    }
}
