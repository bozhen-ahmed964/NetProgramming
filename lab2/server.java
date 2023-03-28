package lab2;

import java.net.*;
import java.io.*;
import java.util.*;

public class server {

    private static final int PORT = 9999;

    public static void main(String[] args) throws IOException {

        // 1
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Waiting for connection.........");

        // 2
        Socket clientSocket = serverSocket.accept();
        System.out.println(" Server Connected to Client");

        // 3
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

        // 4
        InetAddress localIP = InetAddress.getLocalHost();
        String serverDate = localIP + " : " + new Date().toString();

        // 5
        System.out.println(serverDate);
        System.out.println("[Server] : Sent Date Closing..........");

        // 6

        serverSocket.close();
        clientSocket.close();

    }

}
