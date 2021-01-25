package alpro;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ChatClient {

    private boolean connected;
    private Socket socket = null;
    private ChatListener listener = null;
    private final PrintStream outputPrintStream;
    private final String hostname;
    private final int port;

    public ChatClient(String hostname, int port, PrintStream outputPrintStream) {
        this.hostname = hostname;
        this.port = port;
        this.outputPrintStream = outputPrintStream;
        this.connected = false;
    }

    public void connect() throws IOException {
        try {
            // TODO: Create new socket with target given in constructor arguments.
            socket = new Socket(this.hostname, this.port);
        } catch (IOException e) {
            throw new IOException("Could not connect to " + hostname + ":" + port + " : " + e.getMessage(), e);
        }
        this.connected = true;
        outputPrintStream.println("Connected to " + socket.getInetAddress().getHostAddress() + " through port " + socket.getLocalPort());

        BufferedReader in;
        // TODO: Create BufferedReader on top of InputStreamReader on top of the input stream of the socket. Throw IOException if that fails
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // TODO: Create new ChatListener with BufferedReader 'in' and 'outputPrintStream'. Store in member variable 'listener'
        listener = new ChatListener(in, outputPrintStream);
        // TODO: Create and start a new Thread with this ChatListener

        Thread chatListenerThread = new Thread(listener);
        chatListenerThread.start();

    }

    public void disconnect() {
        if(!isConnected())
            return;
        try {
            socket.close();
            this.connected = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        if(socket == null || listener == null)
            return false;
        return !socket.isClosed() && socket.isConnected() && listener.isConnected();
    }

    public void send(String message) throws IOException{
        // TODO: Throw IllegalStateException when client is not connected
        if (!this.connected){
            throw new IllegalStateException("not connected");
        }
        try {
            // TODO: Create a PrintWriter around the output stream of the socket and print the message on it with a line break at the end!
            // Don't forget to flush (auto-flush is an option)!
            PrintWriter printer = new PrintWriter(socket.getOutputStream(), true);
            printer.println(message);

        } catch (IOException e) {
            throw new IOException("Could not send message: " + e.getMessage(), e);
        }
    }

}
