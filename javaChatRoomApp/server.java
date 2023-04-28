package javaChatRoomApp;

import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class server {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter port number: ");
        int port = scanner.nextInt();

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Waiting for connection.........");

        ArrayList<Socket> clientSockets = new ArrayList<Socket>();

        // start the broadcast handler thread
        BroadcastHandler broadcastHandler = new BroadcastHandler(clientSockets);
        Thread broadcastThread = new Thread(broadcastHandler);
        broadcastThread.start();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            clientSockets.add(clientSocket);
            System.out.println("Server Connected to Client");

            Thread t = new Thread(new ClientHandler(clientSocket, broadcastHandler));
            t.start();
        }
    }
}

class BroadcastHandler implements Runnable {
    private ArrayList<Socket> clientSockets;

    public BroadcastHandler(ArrayList<Socket> clientSockets) {
        this.clientSockets = clientSockets;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String message = scanner.nextLine();
            broadcastMessage("[Server] " + message);
        }
    }

    public void broadcastMessage(String message) {
        for (Socket socket : clientSockets) {
            try {
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                output.println(message);
            } catch (IOException e) {
                System.out.println("Exception caught when trying to write to socket");
                System.out.println(e.getMessage());
            }
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;
    private String name;
    private BroadcastHandler broadcastHandler;

    public ClientHandler(Socket socket, BroadcastHandler broadcastHandler) throws IOException {
        this.clientSocket = socket;
        this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.output = new PrintWriter(clientSocket.getOutputStream(), true);
        this.broadcastHandler = broadcastHandler;
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
                System.out.println(name + " : " + inputLine);
                broadcastHandler.broadcastMessage("[" + name + "] " + inputLine);
            }
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to read/write from socket");
            System.out.println(e.getMessage());
        }
    }
}