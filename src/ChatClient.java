import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.*;
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
//Rewrite Scannner to printwriter/bufferreader


/**
 * Created by Martin H on 20-02-2017.
 */
public class ChatClient extends Application {

    private static InetAddress host;
    private static int port = 1234;

    public static void main(String[] args) {

        try {
            host = InetAddress.getLocalHost();

        } catch (UnknownHostException uhEx) {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();
    }

    private static void accessServer() {
        Socket link = null;

        try {
            link = new Socket(host, port);

            Scanner input = new Scanner(link.getInputStream());

            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            //Set up stream for keyboard entry
            Scanner userEntry = new Scanner(System.in);

            String message, response;

            do {
                System.out.println("Enter message: ");
                message = userEntry.nextLine();
                output.println(message);
                response = input.next();
                System.out.println("\nServer> " + response);
            }
            while (!message.equals("***CLOSE****"));
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        finally {
            try {
                System.out.println("\n* Closing connection... *");
                link.close();
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
