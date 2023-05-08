package Multicasting;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class client {
    
public static void main(String[] args)throws IOException {
    
int portNum = 5000;
if (args.length <= 1) {
    portNum = Integer.parseInt(args[0]);
}


MulticastSocket serverMulticastSocket = new MulticastSocket(portNum);
System.out.println("multicast socket created on port : " + portNum);

InetAddress group = InetAddress.getByName("225.0.0.1");
serverMulticastSocket.joinGroup(group);
System.out.println("join group method is called ");


boolean infinite = true;
while (infinite) {
    byte buff[] = new byte[1024];
    DatagramPacket data = new DatagramPacket(buff, buff.length);
    serverMulticastSocket.receive(data);
    String message = new String(data.getData()).trim();
    System.out.println("client : "  + message);
}
serverMulticastSocket.close();



}



}
