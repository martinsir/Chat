import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.util.*;
// CLIENT:  JOIN {user_name}, {server_ip}:{server_port}
         /* (From client to server. The user name is given by the user. User
            name is max 12 chars long, only letters, digits, ‘-‘ and ‘_’ allowed.) */
// SERVER:  J_OK (From server to client)
// SERVER:  J_ERR (From server to client. Client not accepted. Duplicate name, please try again with different user name)
// CLIENT:  DATA {USer_Name}: {free text}
// CLIENT:  Alive (From client to server. Client sends this heartbeat alive every 1 min)
// CLIENT:  QUIT (From client to server. Client is closing down and leaving group)
// SERVER:  LIST (name1, name2, name3)

// 3 Thread - 1. Chat window 2. send 3. joined list ?
// Multithreaded servers?
//Rewrite Scannner to printwriter/bufferreader


/**
 * Created by Martin H on 20-02-2017.
 */
public class ChatClient extends Application {

    private static InetAddress host;
    private static int port = 2234;

    public static void main(String[] args) {

        try {
            host = InetAddress.getLocalHost();

        } catch (UnknownHostException uhEx) {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        sendMessages();
    }

    private static void sendMessages() {
        Socket socket = null;

        try {
            socket = new Socket(host, port);
// EDIT SCANNER INPUT BuffereedReader input?
            //Scanner input = new Scanner(socket.getInputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
//EDIT Scanner to BufferedReader
            //Set up stream for keyboard entry
            //Scanner userEntry = new Scanner(System.in);
            BufferedReader userEntry = new BufferedReader(new InputStreamReader(System.in));

            String message, response;

            do {
                System.out.print("Enter message('QUIT' to exit): ");
                message = userEntry.readLine();
                //send message to server on the socket's out stream

                //accept response from server on the socket's input stream
                output.println(message);
                response = input.readLine();
                System.out.println("\nServer> " + response);
            }
            while (!message.equals("QUIT"));
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        finally {
            try {
                System.out.println("\nClosing connection... ");
                socket.close();
            }
            catch (IOException ioEX) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Views/Login.fxml"));
        primaryStage.setTitle("Chat chat");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
