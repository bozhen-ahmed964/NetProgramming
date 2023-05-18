package javaChatRoomApp;

import java.net.*;
import java.nio.CharBuffer;
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
                if (inputLine.startsWith("[Server] You have been kicked")) {
                    // Existing code...
                } else if (inputLine.startsWith("/file ")) {
                    String fileName = inputLine.substring(6);
                    receiveFile(fileName);
                } else {
                    System.out.println(inputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to read/write from socket");
            System.out.println(e.getMessage());
        }
    }

     private void receiveFile(String fileName) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = input.read()) != -1) {
                    bufferedOutputStream.write(buffer, 0, bytesRead);
                    bufferedOutputStream.flush();
                }

                System.out.println("File '" + fileName + "' received and saved.");
            } catch (IOException e) {
                System.out.println("Error receiving file: " + e.getMessage());
            }
        }
    }
}
