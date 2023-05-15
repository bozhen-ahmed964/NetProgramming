package Multicasting;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class client {

    public static void main(String[] args) throws IOException {

        int portNum = 5000;
        if (args.length >= 1) {
            portNum = Integer.parseInt(args[0]);
        }

        MulticastSocket clientMulticastSocket = new MulticastSocket(portNum);
        InetAddress group = InetAddress.getByName("225.0.0.1");
        clientMulticastSocket.joinGroup(group);

        byte[] buff = new byte[1024];
        DatagramPacket data = new DatagramPacket(buff, buff.length);

        while (true) {
            clientMulticastSocket.receive(data);
            String message = new String(data.getData(), 0, data.getLength()).trim();
            System.out.println("Server : " + message);

            if (message.equals("***")) {
                break;
            }
        }

        clientMulticastSocket.close();
    }
}