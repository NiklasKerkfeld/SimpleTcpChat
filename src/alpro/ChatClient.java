package alpro;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ChatClient {

    private Socket socket = null;
    private ChatListener listener;
    // TODO: Add member variables for construcotr args

    public ChatClient(String hostname, int port, PrintStream outputPrintStream) {
        // TODO: Store arguments in member variables
    }

    public void connect() {
        // TODO: Create new socket with target given in constructor arguments. Abort when connection fails.

        System.out.println("Connected to " + socket.getInetAddress().getHostAddress() + " through port " + socket.getLocalPort());

        BufferedReader in;
        // TODO: Create BufferedReader ontop of InputStreamReader ontop of the output stream of the socket. Abort if that fails.

        // TODO: Create new ChatListener with BufferedReader 'in' and 'outputPrintStream'. Store in member variable 'listener'
        // TODO: Create and start a new Thread with a ChatListener
    }

    public boolean isConnected() {
        if(socket == null || listener == null)
            return false;
        return !socket.isClosed() && socket.isConnected() && listener.isConnected();
    }

    public void send(String message) {
        // TODO: Throw IllegalStateException when client is not connected
        try {
            // TODO: Create a PrintWriter around the output stream of the socket and print the message on it.
            // Don't forget to flush (auto-flush is an option)!
        } catch (IOException e) {
            System.out.println("Could not send message: " + e.getMessage());
        }
    }

}
