package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ServerWindow extends JFrame {
    private static final int WINDOW_HEIGTH = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;

    JButton btnStart = new JButton("Start");
    JButton btnStop = new JButton("Stop");
    public Boolean isServerWorking;
    JTextArea jTextArea = new JTextArea(5, 40);
    JScrollPane jScrollPane = new JScrollPane(jTextArea);
    ServerWindow() throws IOException {
        isServerWorking = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGTH);

        setTitle("Server");
        setResizable(false);
        setVisible(true);

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnStop);

        jTextArea.setEditable(true);
        jTextArea.setLineWrap(false);
        jTextArea.setWrapStyleWord(false);

        add(panBottom);
        add(jScrollPane, BorderLayout.SOUTH);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    isServerWorking = true;
                    startStopServer(true);
                } else jTextArea.append("Сервер уже запущен\n");
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    startStopServer(false);
                } else jTextArea.append("Сервер уже остановлен\n");
            }
        });
    }

    void startStopServer (Boolean flag){
        if (flag){ jTextArea.append("Сервер запущен\n");}
        else { jTextArea.append("Сервер остановлен\n");}

    }

    public Boolean getIsServerWorking(){
        return this.isServerWorking;
    }
}
