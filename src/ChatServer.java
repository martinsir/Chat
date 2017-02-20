import java.net.*;
import java.util.*;
import java.io.*;

public class ChatServer {

    ////// argument port edit client and server !!!!


    // JOIN {user_name}, {server_ip}:{server_port}
    /* (From client to server. The user name is given by the user. User
      name is max 12 chars long, only letters, digits, ‘-‘ and ‘_’ allowed.) */
    // J_OK (From server to client)
    // J_ERR (From server to client. Client not accepted. Duplicate name, please try again with different user name)
    // DATA {USer_Name}: {free text}
    // Alive (From client to server. Client sends this heartbeat alive every 1 min)
    // QUIT (From client to server. Client is closing down and leaving group)
    // LIST (name1, name2, name3)

    // 3 Thread - 1. Chat window 2. send 3. joined list ?

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
