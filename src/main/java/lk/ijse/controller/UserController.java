package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {

    public TextField useridtext;
    public TextField nametext;
    public TextField passwordtext;
    public TextField emailtext;
    public TableView tbluser;
    public TableColumn colid;
    public TableColumn colname;
    public TableColumn colpassword;
    public TableColumn colemail;
    public Button backuser;



    public void saveOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }



    public void backAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) backuser.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

    public void updateOnAction(ActionEvent actionEvent) {

    }
}
