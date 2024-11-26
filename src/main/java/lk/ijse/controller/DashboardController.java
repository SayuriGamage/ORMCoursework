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
    public Button paymentbuttonid;
    public AnchorPane dashoboardpane;
    public Button userbutton1;


    Studentbo studentbo = (Studentbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.student);
    Registrationbo registrationbo = (Registrationbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.regi);

    public void initialize() throws IOException {

        loaddashboardsec();
    }

    private void loaddashboardsec() throws IOException {
        AnchorPane dashi= FXMLLoader.load(this.getClass().getResource("/view/dashsec.fxml"));
        dashoboardpane.getChildren().clear();
        dashoboardpane.getChildren().add(dashi);
    }



    public void registrationOnAction(ActionEvent actionEvent) throws IOException {
       /* AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/registration.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) regisbutton.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");*/
        AnchorPane dashi= FXMLLoader.load(this.getClass().getResource("/view/registration.fxml"));
        dashoboardpane.getChildren().clear();
        dashoboardpane.getChildren().add(dashi);

    }

    public void courseOnAction(ActionEvent actionEvent) throws IOException {
       if (LoginContoller.liveUserRole.equals("admin")){
           AnchorPane dashi = FXMLLoader.load(getClass().getResource("/view/course.fxml"));
           dashoboardpane.getChildren().clear();
           dashoboardpane.getChildren().add(dashi);
       }else {
           new Alert(Alert.AlertType.ERROR,"sorry you cant access").show();
       }
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane dashi = FXMLLoader.load(getClass().getResource("/view/Student.fxml"));
        dashoboardpane.getChildren().clear();
        dashoboardpane.getChildren().add(dashi);
    }

    public void usermanageOnAction(ActionEvent actionEvent) throws IOException {
      if(LoginContoller.liveUserRole.equals("admin")){
          AnchorPane dashi = FXMLLoader.load(getClass().getResource("/view/user.fxml"));
          dashoboardpane.getChildren().clear();
          dashoboardpane.getChildren().add(dashi);
      }else {
      new Alert(Alert.AlertType.ERROR,"sorry you cant access").show();
      }

    }

    public void paymentOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane dashi = FXMLLoader.load(getClass().getResource("/view/payment_details.fxml"));
        dashoboardpane.getChildren().clear();
        dashoboardpane.getChildren().add(dashi);
    }

    public void dashboardsecAction(ActionEvent actionEvent) throws IOException {
        AnchorPane dashi= FXMLLoader.load(this.getClass().getResource("/view/dashsec.fxml"));
        dashoboardpane.getChildren().clear();
        dashoboardpane.getChildren().add(dashi);
    }
}
