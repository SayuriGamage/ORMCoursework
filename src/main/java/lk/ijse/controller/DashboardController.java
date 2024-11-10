package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    public AnchorPane rootNode2;
    public Button userbutton;
    public Button studentbutton;
    public Button coursebutton;
    public Button regisbutton;

    public void registrationOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/registration.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) regisbutton.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

    public void courseOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/course.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) coursebutton.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/Student.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) studentbutton.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

    public void usermanageOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/user.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) userbutton.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");

    }
}
