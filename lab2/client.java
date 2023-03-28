package lab2;

import java.net.*;
import java.io.*;

public class client {

    private static final int PORT = 9999;
    private static final String IP = "127.0.0.1";

    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket(IP, PORT);
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine = input.readLine();
        System.out.println("[Server : ]" + inputLine);

        clientSocket.close();
        System.exit(0);

    }

}
