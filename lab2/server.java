package lab2;

import java.net.*;
import java.io.*;
import java.util.*;

public class server {

    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException {

        // 1
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Waiting for connection.........");

        // 2
        Socket clientSocket = serverSocket.accept();
        System.out.println("Server Connected to Client");

        // 3
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            // 4
            String upperCaseInput = inputLine.toUpperCase();
            System.out.println("[Client] : " + inputLine);
            System.out.println("[Server] : " + upperCaseInput);

            // 5
            output.println(upperCaseInput);

            if (inputLine.equals("exit")) {
                break;
            }
        }

        // 6
        serverSocket.close();
        clientSocket.close();

    }

}
