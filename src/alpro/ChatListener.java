package alpro;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;

// TODO: make Chatlistener implement Runnable
public class ChatListener {

    private final BufferedReader reader;
    private final PrintStream output;

    private boolean connected = true;

    public ChatListener(BufferedReader reader, PrintStream output) {
        this.reader = reader;
        this.output = output;
    }

    public boolean isConnected() {
        return connected;
    }

    @Override
    public void run() {
        // TODO: listen continously for incoming messages and print them on to 'output'
        // You can use the method readLine() in BufferedReader that blocks for input and returns a whole line that can be printed directly
        // readLine() returns null if the underlying stream has been closed -> connection has been broken
        //      Use this set 'connected' to false and end the run()-method when the connection has been aborted
    }
}
