package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.Registrationbo;
import lk.ijse.bo.Studentbo;
import lk.ijse.bo.impl.BOFactory;

import java.io.IOException;

public class DashboardController {

    public Button userbutton;
    public Button studentbutton;
    public Button coursebutton;
    public Button regisbutton;
    public Label lblstudentcount;
    public Label lblregistrationcount;

    Studentbo studentbo = (Studentbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.student);
    Registrationbo registrationbo = (Registrationbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.regi);

    public void initialize(){
        setStudentCount();
        setRegistrationCount();
    }

    private void setRegistrationCount() {
        try {
            int registrationCount = registrationbo.registrationCount();
            lblregistrationcount.setText(String.valueOf(registrationCount));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load registration count").show();
        }
    }

    private void setStudentCount() {
        try {
            int studentCount = studentbo.StudentCount();
            lblstudentcount.setText(String.valueOf(studentCount));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load student count").show();
        }
    }


    public void registrationOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/registration.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) regisbutton.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

    public void courseOnAction(ActionEvent actionEvent) throws IOException {
       if (LoginContoller.liveUserRole.equals("admin")){
           AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/course.fxml"));
           Scene scene = new Scene(rootNode);

           Stage stage = (Stage) coursebutton.getScene().getWindow();
           stage.setScene(scene);
           stage.centerOnScreen();
           stage.setTitle("Login Page");
       }else {
           new Alert(Alert.AlertType.ERROR,"sorry you cant access").show();
       }
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
      if(LoginContoller.liveUserRole.equals("admin")){
          AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/user.fxml"));
          Scene scene = new Scene(rootNode);

          Stage stage = (Stage) userbutton.getScene().getWindow();
          stage.setScene(scene);
          stage.centerOnScreen();
          stage.setTitle("Login Page");
      }else {
      new Alert(Alert.AlertType.ERROR,"sorry you cant access").show();
      }

    }
}
