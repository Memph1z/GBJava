package ChatPack;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    public TextField input;
    public TextArea output;

    public void sendToArea(ActionEvent actionEvent) {
        StringBuilder sb = new StringBuilder(output.getText());
        if (!input.getText().equals("")) {
            if (output.getText().equals("")){
                sb.append(input.getText());
            }else {
                sb.append("\n").append(input.getText());
            }
            output.setText(sb.toString());
            input.clear();
        }
    }

}
