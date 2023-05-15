package Multicasting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class server {

    public static void main(String[] args) throws IOException {
        int portNumber = 5000;

        if (args.length >= 1) {
            portNumber = Integer.parseInt(args[0]);
        }

        MulticastSocket serverMulticastSocket = new MulticastSocket(portNumber);
        InetAddress group = InetAddress.getByName("225.0.0.1");
        serverMulticastSocket.joinGroup(group);

        String message = "";
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        while (!message.equals("***")) {
            System.out.println("Type a message for the server:");
            message = input.readLine();

            DatagramPacket data = new DatagramPacket(message.getBytes(), message.length(), group, portNumber);
            serverMulticastSocket.send(data);
        }

        serverMulticastSocket.close();
    }
}
