package Multicasting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class server {
    

    public static void main(String[] args)throws IOException {
        int portNumber = 5000;

        if (args.length <= 1) {
            portNumber = Integer.parseInt(args[0]);
        }


        MulticastSocket clientMulticastSocket = new MulticastSocket(portNumber);
        InetAddress group = InetAddress.getByName("225.0.0.1");
        clientMulticastSocket.joinGroup(group);

        String message = "";
        System.out.println("type a message for the server ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        message = input.readLine();

        DatagramPacket data =  new DatagramPacket(message.getBytes(),0, message.length(),group,portNumber);
        clientMulticastSocket.send(data);

        while (!message.equals("***")) {
            clientMulticastSocket.close();
        }


    }
}
