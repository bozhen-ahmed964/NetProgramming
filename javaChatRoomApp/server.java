package javaChatRoomApp;

import java.net.*;
import java.util.Scanner;
import java.io.*;
 
public class server {
   

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter port number: ");
        int port = scanner.nextInt();

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Waiting for connection.........");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Server Connected to Client");

            Thread t = new Thread(new ClientHandler(clientSocket));
            t.start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;
    private String name;

    public ClientHandler(Socket socket) throws IOException {
        this.clientSocket = socket;
        this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.output = new PrintWriter(clientSocket.getOutputStream(), true);
        this.name = input.readLine();
    }

    public void run() {
        String inputLine;
        try {
            InetAddress clientAddress = clientSocket.getInetAddress();
            System.out.println("Client " + name + " connected.");

            while ((inputLine = input.readLine()) != null) {
                if (inputLine.equals("/exit")) { // check for exit 
                    System.out.println("Client " + name + " disconnected.");
                    break;
                }
                String upperCaseInput = inputLine.toUpperCase();
                System.out.println(name + " : " + inputLine);
                System.out.println("[Server] : " + upperCaseInput);
                output.println("[" + name + "] " + upperCaseInput);
            }
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to read/write from socket");
            System.out.println(e.getMessage());
        }
    }

}