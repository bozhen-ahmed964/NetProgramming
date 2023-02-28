import java.io.*;
import java.net.*;
import java.util.Scanner;

public class lab1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the URL: ");
            String url = scan.nextLine();
            try {
                InetAddress ip = InetAddress.getByName(url);
                System.out.println("Host Name: " + ip.getHostName());
                System.out.println("IP Address: " + ip.getHostAddress());

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
