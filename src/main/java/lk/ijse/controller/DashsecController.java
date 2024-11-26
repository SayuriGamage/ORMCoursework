package lk.ijse.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.bo.Registrationbo;
import lk.ijse.bo.Studentbo;
import lk.ijse.bo.impl.BOFactory;

public class DashsecController {

    public Label lblregistrationcount;

    public Label lblstuentcount;




    Studentbo studentbo = (Studentbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.student);
    Registrationbo registrationbo = (Registrationbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.regi);

    public void initialize() {
    setRegistrationCount();
    setStudentCount();
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
            lblstuentcount.setText(String.valueOf(studentCount));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load student count").show();
        }
    }

}
