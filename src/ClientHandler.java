import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

/**
 * Created by Martin H on 21-02-2017.
 */

public class ClientHandler extends Thread {
    private Socket clientSock;
    private BufferedReader input;
    private PrintWriter output; // remember
    private boolean connection;

    public ClientHandler(Socket socket) {
        //set up reference to associated socket
        clientSock = socket;
        try {
            input = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
            output = new PrintWriter(clientSock.getOutputStream(), true);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void run() {
        connection = true;
        do {
            String received;
            try {

                //Accept message from client on the socket's input stream
                received = input.readLine();
                //System.out.println("Test sout Server: "+received);

                //Echo message back to client on the socket's out stream
                output.println("ECHO: " + received);
                //Repeat above until 'QUIT' sent by client
                if (clientSock != null) {
                    System.out.println("Closing down connection...");
                    clientSock.close();
                }
            } catch (IOException ioe) {
                System.out.println("Unable to disconnect");
            }
        } while (!connection); // do the above
    } // run END

}