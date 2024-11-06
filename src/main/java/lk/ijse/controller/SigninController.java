package lk.ijse.controller;

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
import lk.ijse.dto.UserDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class SigninController {

    public TextField txtusername;
    public TextField txtemail;
    public TextField txtpassword;
    public Button btnRegister;

    Userbo userbo= (Userbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.user);

    public void signOnAction(ActionEvent actionEvent) {
      String username=txtusername.getText();
      String email=txtemail.getText();
      String password=txtpassword.getText();

      if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
          new Alert(Alert.AlertType.ERROR,"please fill all the fields").show();

      }else {
          String encryptedPassword= null;

          try {
              encryptedPassword= BCrypt.hashpw(password,BCrypt.gensalt());
          } catch (Exception e){
              e.printStackTrace();
              new Alert(Alert.AlertType.ERROR,"error while encrypting password").show();
         return;
          }
          UserDTO admin = new UserDTO(username, email, encryptedPassword, "admin");


          boolean result =userbo.registerAdmin(admin);

          if (result) {
              new Alert(Alert.AlertType.CONFIRMATION, "Registration successful").show();
          } else {
              new Alert(Alert.AlertType.ERROR, "Registration failed").show();
          }
      }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/login.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) btnRegister.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }
}
