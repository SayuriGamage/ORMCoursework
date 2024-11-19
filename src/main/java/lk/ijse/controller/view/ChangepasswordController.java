package lk.ijse.controller.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.Userbo;
import lk.ijse.bo.impl.BOFactory;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class ChangepasswordController {


    public TextField emailaddressontext;
    public TextField newpassswordtext;
    public Button donetext;

    Userbo userbo= (Userbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.user);
    public void doneOnAction(ActionEvent actionEvent) throws IOException {

String email=emailaddressontext.getText();
        boolean check=userbo.checkemail(email);
if (check){
    String newpassword=newpassswordtext.getText();
    String password= BCrypt.hashpw(newpassword,BCrypt.gensalt());

    userbo.changePassword(email,password);
    AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/user.fxml"));
    Scene scene = new Scene(rootNode);

    Stage stage = (Stage) donetext.getScene().getWindow();
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.setTitle("Login Page");

}else {
    new Alert(Alert.AlertType.ERROR,"email not found").show();
}
    }
}
