package lk.ijse.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.Userbo;
import lk.ijse.bo.impl.BOFactory;
import lk.ijse.dto.StudentDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.util.Regex;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class UserController {

    public TextField useridtext;
    public TextField nametext;
    public TextField passwordtext;
    public TextField emailtext;
    public TableView tbluser;
    public TableColumn colemail;
    public Button backuser;
    public TextField roletext;
    public TableColumn colrole;
    public TableColumn colid;
    public TableColumn colname;
    public Hyperlink changeapssword;



    Userbo userbo= (Userbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.user);

    public void initialize(){
        setcellvaluefactory();
        getallusers();
    }

    private void getallusers() {
        ObservableList<UserDTO> studentDTOS=userbo.getAllUsers();
        tbluser.setItems(studentDTOS);
    }

    private void setcellvaluefactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("username"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    public void saveOnAction(ActionEvent actionEvent) {

       String name = nametext.getText();
       String password = passwordtext.getText();
       String email = emailtext.getText();
       String role = roletext.getText();
       String encript= BCrypt.hashpw(password,BCrypt.gensalt());
       UserDTO userDTO = new UserDTO( name, email,encript, role);
      if (isValid()){
          boolean result = userbo.saveUser(userDTO);
          if (result) {
              getallusers();
              clearfields();
              new Alert(Alert.AlertType.CONFIRMATION, "Registration user successful").show();
          } else {
              new Alert(Alert.AlertType.ERROR, "Registration user failed").show();
          }
      }else {
          new Alert(Alert.AlertType.ERROR, "wrong inputs try again").show();
      }
    }

    private void clearfields() {
        nametext.clear();
        passwordtext.clear();
        emailtext.clear();
        roletext.clear();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
     String ids=useridtext.getText();

     boolean result=userbo.deleteUser(ids);
     if (result) {
         clearfields();
         getallusers();
         new Alert(Alert.AlertType.CONFIRMATION, "delete user successful").show();
     } else {
         new Alert(Alert.AlertType.ERROR, "delete user failed").show();
     }
    }



    public void backAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) backuser.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }




    public void searchOnAction(ActionEvent actionEvent) {
       String ids=useridtext.getText();
       UserDTO userDTO = userbo.getUser(ids);
       nametext.setText(userDTO.getUsername());
       emailtext.setText(userDTO.getEmail());
       passwordtext.setText(userDTO.getPassword());
       roletext.setText(userDTO.getRole());
    }

    public void nameonAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.NAME,nametext);
    }

    public void passwordonAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.FEE,passwordtext);
    }

    public void emailOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.EMAIL,emailtext);
    }

    public void roleonAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.NAME,roletext);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.util.TextField.NAME,nametext)) return false;
        if (!Regex.setTextColor(lk.ijse.util.TextField.FEE,passwordtext)) return false;
        if (!Regex.setTextColor(lk.ijse.util.TextField.EMAIL,emailtext)) return false;
        if (!Regex.setTextColor(lk.ijse.util.TextField.NAME,roletext)) return false;
        return true;
    }

    public void changePasswordAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/changepassword.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) changeapssword.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }
}
