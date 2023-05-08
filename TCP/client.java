package TCP;

import java.net.*;
import java.io.*;

public class client {

    private static final int PORT = 1234;
    private static final String IP = "127.0.0.1";

    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket(IP, PORT);
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String userInputLine;
        while ((userInputLine = userInput.readLine()) != null) {
            // 1
            output.println(userInputLine);
            // 2
            String response = input.readLine();
            System.out.println("[Server] : " + response);

            if (userInputLine.equals("exit")) {
                break;
            }
        }
        // 3
        clientSocket.close();
        System.exit(0);
    }

}