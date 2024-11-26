package lk.ijse.controller.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.Registrationbo;
import lk.ijse.bo.impl.BOFactory;
import lk.ijse.dto.PaymentDetailsDTO;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.dto.StudentDTO;

import java.io.IOException;

public class PaymentDetailsController {

    public TableView tblpaymentdetails;
    public TableColumn registrationid;
    public TableColumn date;
    public TableColumn studentid;
    public TableColumn paymentid;
    public TableColumn tobepaid;
    public TextField regiidhere;
    public TextField newpaymenttext;
    public Label greentext;
    public Label redtext;



    Registrationbo registrationbo= (Registrationbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.regi);

    public void initialize() {
        loadallPaymentdetais();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        registrationid.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        studentid.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tobepaid.setCellValueFactory(new PropertyValueFactory<>("tobe_paid"));
        paymentid.setCellValueFactory(new PropertyValueFactory<>("pay_id"));

    }

    private void loadallPaymentdetais() {
        ObservableList<PaymentDetailsDTO>paymentDetailsDTOS=registrationbo.getAllPaymentDetails();
        tblpaymentdetails.setItems(paymentDetailsDTOS);
    }

    public void loadupdatepaymnetAction(ActionEvent actionEvent) throws IOException {
        clearfields();
        String regiid=regiidhere.getText();


        RegistrationDTO registrationDTO=registrationbo.getregistrations(regiid);
        int amount= Integer.parseInt(registrationDTO.getAmount());
        int tobe= Integer.parseInt(registrationDTO.getUpfront_payment());
        int tobePaid=amount-tobe;
        if(amount==tobe && tobePaid==0){
            greentext.setText("Your payment was completed");


        }else {
            redtext.setText("you have to pay  " + tobePaid);

        }
    }

    private void clearfields() {
        greentext.setText("");
        redtext.setText("");
    }


}
