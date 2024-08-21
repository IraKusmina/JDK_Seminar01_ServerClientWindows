package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new ServerWindow();
        new ClientWindow();
        System.out.println("Method Main is over");
    }
}

//        Обратите внимание, что чаще всего история сообщений хранится на сервере
//и заполнение истории чата лучше делать при соединении с сервером, а не при
//открытии окна клиента.