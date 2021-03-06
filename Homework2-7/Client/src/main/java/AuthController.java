import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Base64;

public class AuthController implements Initializable{

    public TextField login;
    public TextField password;
    public Button logButton;
    public Button regButton;
    public Text alertText;
    public Hyperlink logSelector;
    public Hyperlink regSelector;
    private Network network;
//    public ChatApp chatApp;
//    private Stage primaryStage;

    public void attemptLogin(ActionEvent actionEvent) throws IOException {
        String pass = password.getText();
        String encPass = Base64.getEncoder().encodeToString(pass.getBytes(StandardCharsets.UTF_8));
        network.login(login.getText(), encPass);
        password.clear();
    }

    public void attemptRegister(ActionEvent actionEvent) throws IOException{
        String pass = password.getText();
        String encPass = Base64.getEncoder().encodeToString(pass.getBytes(StandardCharsets.UTF_8));
        network.register(login.getText(), encPass);
        password.clear();
    }

    public void logChange(ActionEvent actionEvent) {
        logSelector.setVisible(false);
        logButton.setVisible(false);
        logButton.setDisable(true);
        logSelector.setDisable(true);
        regSelector.setVisible(true);
        regButton.setVisible(true);
        regSelector.setDisable(false);
        regButton.setDisable(false);
    }

    public void regChange(ActionEvent actionEvent) {
        regSelector.setVisible(false);
        regButton.setVisible(false);
        regSelector.setDisable(true);
        regButton.setDisable(true);
        logSelector.setVisible(true);
        logButton.setVisible(true);
        logButton.setDisable(false);
        logSelector.setDisable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        network = Network.getInstance();

        new Thread(() -> {
            try {
                while (true) {
                    String message = network.readMessage();
                    if (message.equals("/quit")) {
                        network.close();
                        break;
                    } else if (message.equals("200 Success")){
                        new ChatApp();
                    }else if (message.equals("403 Forbidden")){
                        password.clear();
                        alertText.setText("Login or password is invalid");
                        alertText.setVisible(true);
                    }else if (message.equals("409 Conflict")){
                        password.clear();
                        alertText.setText("Username already taken");
                        alertText.setVisible(true);
                    }
                }
            } catch (Exception ioException) {
                System.err.println("Server was broken");
            }
        }).start();
    }
}

