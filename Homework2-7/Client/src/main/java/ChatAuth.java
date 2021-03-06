import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatAuth extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Network network = Network.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("auth.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("HotSous - Login");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(request -> {
            try {
                network.writeMessage("/quit");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}