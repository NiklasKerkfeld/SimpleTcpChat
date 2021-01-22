package alpro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        // TODO: Make a chat client with given server address, port and System.out as output stream

        try {
            // TODO: Make client connect to server
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        while(client.isConnected()) {
            try {
                if(inputReader.ready()) {
                    String message = inputReader.readLine();
                    if(message.equals("exit")) {
                        client.disconnect();
                    }
                    else {
                        try {
                            client.send(message);
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Could not read input: " + e.getMessage());
            }
        }
        System.out.println("Chat ended");
    }
}
