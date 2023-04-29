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

        // Create a separate thread to continuously read input from the server
        Thread serverInputThread = new Thread(new ServerInputHandler(input));
        serverInputThread.start();

        String userInputLine;
        while ((userInputLine = userInput.readLine()) != null) {
            output.println(userInputLine);
            if (userInputLine.equals("/exit")) {
                break;
            }
        }
        clientSocket.close();
        System.exit(0);
    }

    static class ServerInputHandler implements Runnable {
        private BufferedReader input;

        public ServerInputHandler(BufferedReader input) {
            this.input = input;
        }

        public void run() {
            String inputLine;
            try {
                while ((inputLine = input.readLine()) != null) {
                    System.out.println(inputLine);
                }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to read/write from socket");
                System.out.println(e.getMessage());
            }
        }
    }
}