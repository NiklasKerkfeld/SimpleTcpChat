package alpro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        // TODO: Make a chat client with given server address, port and System.out as output stream

        // TODO: Make client connect to server

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        while(client.isConnected()) {
            // TODO: Test for data to send and let client send it. Try to not block for input or you will still
            //  be waiting while the connection may have already closed
        }
        System.out.println("Disconnected");
    }
}
