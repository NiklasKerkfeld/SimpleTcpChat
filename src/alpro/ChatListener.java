package alpro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;

// TODO: make Chatlistener implement interface Runnable
public class ChatListener implements Runnable {

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
        // TODO: listen continuosly for incoming messages and print them on to 'output'
        // You can use the method readLine() in BufferedReader that blocks for input and returns a whole line that can be printed directly
        // readLine() returns null if the underlying stream has been closed and throws a SocketException when the connection has reset
        //      Use this to set 'connected' to false and end the run()-method when the connection has failed
        while(true){
            String text = null;
            try {
                text = reader.readLine();
            } catch (IOException e) {
                this.connected = false;
                return;
            }
            if (text != null) {
                output.println(text);
            }
        }

    }
}
