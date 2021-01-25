package alpro;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;

public class ChatController {
    // private PipedInputStream inputStream;

    private InputStream inputStream;
    private OutputStream outputStream;
    private PipedOutputStream pipedOutputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public TextField textField;
    public TextArea textArea;
    private PrintWriter printWriter;


    public void onPressEnter(ActionEvent actionEvent) {
        printWriter.println(textField.getText());
        textArea.setText(textArea.getText() + "\nME:" + textField.getText());
        textField.setText(" ");
        printWriter.flush();
    }

    public void initialize() throws IOException {
        PipedOutputStream pipeOutputStream = new PipedOutputStream();
        printWriter = new PrintWriter(pipeOutputStream);
        inputStream = new PipedInputStream(pipeOutputStream);

        PipedInputStream pipedInputStream = new PipedInputStream();
        outputStream = new PipedOutputStream(pipedInputStream);

        BufferedReader br = new BufferedReader(new InputStreamReader(pipedInputStream));

        new Thread(() -> {
            try {
                readMethod(br);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        textArea.setEditable(false);
    }

    private void readMethod(BufferedReader br) throws IOException {
        String message;
        while((message = br.readLine()) != null){
            String finalMessage = message;
            Platform.runLater(() -> textArea.setText(textArea.getText()+ "\n" + finalMessage));

        }
    }
}
