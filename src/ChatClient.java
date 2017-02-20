import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Created by Martin H on 20-02-2017.
 */
public class ChatClient extends Application {

    private static InetAddress host;
    private static final int port = 1234;

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
                System.out.println("/nServer> " + response);
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
