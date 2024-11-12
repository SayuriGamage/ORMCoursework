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
    public AnchorPane rootNode2;



    Userdao userdao= (Userdao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.user);
    Userbo userbo= (Userbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.user);
      static String liveUserRole="";

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

    void getdashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) rootNode2.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registration Page");
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String username = textusername.getText();
        String password = textpassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
        } else {
            UserDTO userDTO = userbo.getdata(username);
            if (userDTO == null) {
                new Alert(Alert.AlertType.ERROR, "Invalid username").show();
            } else {
                if (BCrypt.checkpw(password, userDTO.getPassword())) {
                    String role = userDTO.getRole();
                    if (role != null && role.equals("admin")) {
                        new Alert(Alert.AlertType.CONFIRMATION,"welcome admin "+username).show();
                        liveUserRole = "admin";
                    } else if (role != null && role.equals("coordinator")) {
                        liveUserRole = "coordinator";
                        new Alert(Alert.AlertType.CONFIRMATION,"welcome coordinator "+username).show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid role").show();
                        getdashboard();
                        return;
                    }
                    getdashboard();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid password").show();
                }
            }
        }
    }



}

