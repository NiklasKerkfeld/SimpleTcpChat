package alpro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);

    }

    private static final String hostname = "116.203.122.182";
    private static final int port = 4567;


    public static void oldmain(InputStream input, PrintStream output) {

        // TODO: Make a chat client with given server address, port and System.out as output stream
        ChatClient client = new ChatClient(hostname, port, output);

        try {
            // TODO: Make client connect to server
            client.connect();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause().printStackTrace();
            return;
        }

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
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
                            e.getCause().printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Could not read input: " + e.getMessage());
            }
        }
        System.out.println("Chat ended");
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ChatFXML.fxml"));

        // fxmlLoader.setController(controller);

        VBox vBox = fxmlLoader.load();
        ChatController controller = fxmlLoader.getController();
        new Thread(() -> {
            oldmain(controller.getInputStream(), new PrintStream(controller.getOutputStream()));
        }).start();

        Scene scene = new Scene(vBox);
        stage.setScene(scene);

        stage.show();

    }
}
