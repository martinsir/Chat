import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.io.*;

public class ChatServer {
    //edit all Scanner to bufferedReader ect.

    private static ServerSocket serverSocket;
    private static int port = 2234;
    //
    // private static ServerSocketChannel ssChanel;
    //private static Selector selector;
    /*Above Selector used both for detecting new connections
    (on the serverSocketChannel) and for detecting incoming data
    from existing connections (on the SocketChannel)*/

    ///Not in use atm
    //private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<ClientHandler>());

    public static void main(String[] args) throws IOException {

        //System.out.println("Opening port...\n");
        try {

            serverSocket = new ServerSocket(port);

        } catch (IOException io) {

            System.out.println("Unable to attach to port\n" + io);
            System.exit(1);
        }
        do {
            //handleClient();
            //wait for client...
            Socket clientSocket = serverSocket.accept();
            System.out.println("\nNew client accepted:\n"+"IP: "+clientSocket.getInetAddress()+"\nPort: "+clientSocket.getPort());
            /*Create a thread to handle communication with
            this client and pass the constructor  for this
            thread a reference to the relevant socket...*/
            ClientHandler handler = new ClientHandler(clientSocket);
            handler.start();

            //clients.add(handler);

        } while(true);


    }

    /*public static void broadcastMessage(String chatName, ByteBuffer buffer) {
        String messagePrefix = chatName + ": ";
        byte[] messagePrefixBytes = messagePrefix.getBytes();
        final byte[] CR ="\n".getBytes(); //Carriage return.
        try {
            int messageSize = buffer.position();
            byte[] messageBytes = buffer.array();
            byte [] messageBytesCopy = new byte[messageSize];
            for (int i = 0; i < messageSize; i++) {
                messageBytesCopy[i]=messageBytes[i];
            }
            buffer.clear();
            //Concatenate message text onto message prefix
            buffer.put(messagePrefixBytes);
            for (int i = 0; i < messageSize; i++) {
                buffer.put(messageBytesCopy[i]);
            }
            buffer.put(CR);
            SocketChannel chatSocketChannel;
            for (ChatUser chatUser:allUser) {
                chatSocketChannel = chatUser.getSocketChannel();
                buffer.flip();
                //write full message (with user's name)
                chatSocketChannel.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    //OLD STUFF
    private static void handleClient() {

        /*try {

            // EDIT SCANNER to InputStream BufferReader
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            int numMessages = 0;
            String message = input.readLine();
            while (!message.equals(null)) {
                System.out.println("Message received.");
                numMessages++;
                output.println("Message " + numMessages + ": " + message);
                message = input.readLine();
            }
            output.println(numMessages + " messages received.");
        } catch (IOException io2) {
            io2.printStackTrace();
        } finally {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("\n Closing connection...*");
                clientSocket.close();
            } catch (IOException ioe3) {
                System.out.println("Unable to disconnect!");
                System.exit(1);

            }*/
        } //handleClient END
    }

