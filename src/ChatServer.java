import java.net.*;
import java.util.*;
import java.io.*;

public class ChatServer {

    private static ServerSocket serverSocket;
    private static int port = 1234;

    public static void main(String[] args) {

        System.out.println("Opening port...\n");

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException io) {
            System.out.println("Unable to attach to port\n" + io);
            System.exit(1);
        }
        do {
            handleClient();
        } while (true);

    }

    private static void handleClient() {
        Socket link = null;
        try {
            link = serverSocket.accept();
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            int numMessages = 0;
            String message = input.nextLine();
            while (!message.equals("***CLOSE***")) {
                System.out.println("Message received.");
                numMessages++;
                output.println("Message " + numMessages + ": " + message);
                message = input.nextLine();
            }
            output.println(numMessages + " messages received.");
        } catch (IOException io2) {
            io2.printStackTrace();
        } finally {
            try {
                System.out.println("n* Closing connection...*");
                link.close();
            } catch (IOException ioe3) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }

        }
    }
}
