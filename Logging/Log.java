package Logging;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Log {
   
    private static Logger logger = Logger.getLogger(Log.class.getName());

public static void main(String[] args) throws SecurityException, IOException {

    try {
        ServerSocket serverSocket = new ServerSocket(999);
        System.out.println("server is loading.....");
        logger.setLevel(Level.INFO);
        logger.severe("message 1");

        Socket clientSocket = serverSocket.accept();
        System.out.println("client connected to server");
        logger.setLevel(Level.INFO);
        logger.severe("message 1");
        
    } catch (Exception e) {
        System.err.println(e);
    }

    FileHandler handler = new FileHandler();
    logger.addHandler(handler);
    handler.setFormatter(new SimpleFormatter());


}

}
