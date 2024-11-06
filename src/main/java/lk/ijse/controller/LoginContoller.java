package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.Userbo;
import lk.ijse.bo.impl.BOFactory;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.Userdao;
import lk.ijse.dto.UserDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class LoginContoller {

    public TextField textusername;
    public TextField textpassword;
    public Hyperlink registerlink;


    Userdao userdao= (Userdao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.user);
    Userbo userbo= (Userbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.user);
      String liveUserRole="";

    public void registerOnAction(ActionEvent actionEvent) throws IOException {
         if(userdao.ifHaveAdmins()){
             new Alert(Alert.AlertType.ERROR,"already have admin").show();
         } else {
             AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/signin.fxml"));
             Scene scene=new Scene(rootNode);
             Stage stage= (Stage) registerlink.getScene().getWindow();
             stage.setScene(scene);
             stage.centerOnScreen();
             stage.setTitle("Registration");

         }
    }

    public void loginOnAction(ActionEvent actionEvent) {
       String username=textusername.getText();
       String password=textpassword.getText();

       if(username.isEmpty() || password.isEmpty()){
           new Alert(Alert.AlertType.ERROR,"please fill all the fields").show();

       } else {
           UserDTO userDTO =userbo.getdata(username);
           if (userDTO == null){
               new Alert(Alert.AlertType.ERROR,"Invalid username").show();
           } else {
               if (BCrypt.checkpw(password,userDTO.getPassword())){
            if(userDTO.getRole().equals("admin")){
                System.out.println("he is admin");
                liveUserRole="admin";
            } else {
                liveUserRole="user";
                System.out.println("he is user");
            }
               } else {
                   new Alert(Alert.AlertType.ERROR,"Invalid password").show();
               }
           }
       }


    }
}
