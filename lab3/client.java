import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class client {
    private static InetAddress host;
    private static final int PORT_NUMBER = 800;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;

    public static void main(String[] args) throws IOException {
        host = InetAddress.getLocalHost();
        datagramSocket = new DatagramSocket();
        Scanner scan = new Scanner(System.in);
        String message = "", response = "";
        do {
            // Send message to server
            System.out.print("[CLIENT]: ");
            message = scan.nextLine();
            outPacket = new DatagramPacket(message.getBytes(), message.length(), host, PORT_NUMBER);
            datagramSocket.send(outPacket);

            // Receive response from server
            buffer = new byte[256];
            inPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(inPacket);
            response = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("[SERVER]: " + response);

        } while (!message.equals("***"));
        System.out.println("\nClosing Connection.......");
        datagramSocket.close();
    }
}
