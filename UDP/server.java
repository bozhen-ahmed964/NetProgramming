import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class server {
    private static final int PORT_NUMBER = 800;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;

    public static void main(String[] args) throws IOException {
        datagramSocket = new DatagramSocket(PORT_NUMBER);
        System.out.println("Server started on port " + PORT_NUMBER);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // Receive message from client
            buffer = new byte[256];
            inPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(inPacket);

            String messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("[CLIENT]: " + messageIn);

            InetAddress clientAddress = inPacket.getAddress();
            int clientPort = inPacket.getPort();

            
            if (messageIn.equals("***")) {
                System.out.println("Closing connection...");
                datagramSocket.close();
                System.exit(0);
            }

            // Send response to client
            String messageOut = messageIn;
            outPacket = new DatagramPacket(
                    messageOut.getBytes(),
                    messageOut.length(),
                    clientAddress,
                    clientPort);
            datagramSocket.send(outPacket);

            // Read message 
            System.out.print("[SERVER]: ");
            String serverMessage = reader.readLine();
            if (serverMessage.equals("***")) {
                System.out.println("Closing connection...");
                datagramSocket.close();
                System.exit(0);
            }
            outPacket = new DatagramPacket(
                    serverMessage.getBytes(),
                    serverMessage.length(),
                    clientAddress,
                    clientPort);
            datagramSocket.send(outPacket);
        }
    }
}
