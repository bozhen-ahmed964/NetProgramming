package javaChatRoomApp;

import java.net.*;
import java.io.*;
import java.util.*;

public class client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter port number:");
        int port = scanner.nextInt();
        scanner.nextLine();

        Socket clientSocket = new Socket("localhost", port);
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        output.println(name);

        String userInputLine;
        while ((userInputLine = userInput.readLine()) != null) {
            output.println(userInputLine);
            String response = input.readLine();
            System.out.println(response);
            if (userInputLine.equals("exit")) {
                break;
            }
        }
        clientSocket.close();
        System.exit(0);
    }
}
