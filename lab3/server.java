import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class server {


    private static int port_number = 800;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;

    public static void main(String[] args)throws IOException {
        

        datagramSocket = new DatagramSocket(port_number);
        String messageIn, messageOut;
        int numMessage = 0;

        
        //task part in LAB 
        // for (int i = 0; i < 1000; i++) {
        //     if (i == port_number) {bahshh yuuuuuuusf
        //         System.out.println(i + " port is used " + datagramSocket);
        //     }else{
        //         System.out.println(i);
        //     }
            
        // }

        do {
            buffer = new byte[256];
            inPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(inPacket);
            InetAddress clientAddress = inPacket.getAddress();
            int clientPort = inPacket.getPort();
            messageIn = new String(inPacket.getData(),0, inPacket.getLength());
            System.out.println("Message Received");
            numMessage++;
            messageOut = "Message " + numMessage + " : " + messageIn;

            outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
            datagramSocket.send(outPacket);
        } while (true);

    }


}
