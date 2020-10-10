package lesson6i1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatServer {
    private ServerSocket serversocket;
    private Socket socket;

    public ChatServer() {
        try {
            serversocket = new ServerSocket(4000);
            socket = serversocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            new Thread(() -> {
                while (true) try {
                    String msg = sc.nextLine();
                    out.writeUTF(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            while (true) {
                String msg = in.readUTF();
                System.out.println("сообщение клиента " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}
