import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;



public class client {
    

    private static InetAddress host;
    private static final int port_number = 800;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;

    public static void main(String[] args)throws IOException {

        host = InetAddress.getLocalHost();
        datagramSocket = new DatagramSocket();
        Scanner scan = new Scanner(System.in);
        String message = " " , response = " ";
        do {
            System.out.println(" Enter Message : ");
            message = scan.nextLine();
            if (!message.equals("exit")) {
                outPacket = new DatagramPacket(message.getBytes(), message.length(), host, port_number);
                
                datagramSocket.send(outPacket);
                buffer = new byte[256];
                inPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(inPacket);
                response = new String(inPacket.getData(),0,inPacket.getLength());
                System.out.println("\n [SERVER] : " + response);

            }
        } while (!message.equals("exit"));
        System.out.println("\n Closing Connection.......");
        datagramSocket.close();
    }


}
