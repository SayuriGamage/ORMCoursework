package lk.ijse.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.Coursebo;
import lk.ijse.bo.Registrationbo;
import lk.ijse.bo.Studentbo;
import lk.ijse.bo.impl.BOFactory;
import lk.ijse.dao.Registrationdao;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Course;
import lk.ijse.entity.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegistrationController {

    public ComboBox courseid;
    public TextField coursename;
    public TextField amount;
    public TextField upfrontpayment;
    public TextField mobile;
    public TextField regisid;
    public TextField studentid;
    public Label datelbl;
    public TableView registrationtbl;
    public TableColumn colregiid;
    public TableColumn colcourse;
    public TableColumn coluppayment;
    public TableColumn coltotalpayment;
    public Button backbutton;
    public TextField tobePayment;
    public TableColumn colstid;


Coursebo coursebo= (Coursebo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.course);
Studentbo studentbo= (Studentbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.student);
Registrationbo registrationbo= (Registrationbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.regi);

    public void initialize(){
        setcellvaluefactory();
        getallRegistrations();
        loadAllCourses();
        setDate();
        String currentRegistrationId=null;
        currentRegistrationId=registrationbo.getCurrentRegistrationId();
        String nextempId = generateNextregiId(currentRegistrationId);
        regisid.setText(nextempId);
    }

    private String generateNextregiId(String currentRegistrationId) {
        if (currentRegistrationId != null && currentRegistrationId.matches("^REG\\d+$")) {

            String numericPart =currentRegistrationId.substring(3);
            try {

                int orderId = Integer.parseInt(numericPart) + 1;

                return "REG" + String.format("%03d", orderId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "REG001";
    }

    private void getallRegistrations() {
        ObservableList<RegistrationDTO> registrationDTOS=registrationbo.getAllRegistrations();
        registrationtbl.setItems(registrationDTOS);
    }

    private void setcellvaluefactory() {
      colregiid.setCellValueFactory(new PropertyValueFactory<>("regi_id"));
      coluppayment.setCellValueFactory(new PropertyValueFactory<>("upfront_payment"));
      coltotalpayment.setCellValueFactory(new PropertyValueFactory<>("tobePaid"));
        colcourse.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colstid.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        coluppayment.setCellValueFactory(new PropertyValueFactory<>("upfront_payment"));
    }

    private void setDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        datelbl.setText(formattedDate);
    }

    private void loadAllCourses() {
        ObservableList<String> obst= FXCollections.observableArrayList();
        List<String> ids=coursebo.getCourseIds();
        for(String id : ids){
            obst.add(id);
        }
        courseid.setItems(obst);
    }

    public void cancelOnAction(ActionEvent actionEvent) {
        String id=regisid.getText();
        boolean idDeleted=registrationbo.deleteRegistration(id);
        if(idDeleted){
            cleartextFields();
            getallRegistrations();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Registration is deleted");
            alert.showAndWait();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Registration is not deleted");
            alert.showAndWait();
        }
    }

    public void backonaction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) backbutton.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

    public void registrationOnAction(ActionEvent actionEvent) {
      String id=regisid.getText();
      String stname=upfrontpayment.getText();
      String paid=tobePayment.getText();
        String courseId = courseid.getValue().toString(); // String ID from ComboBox
        Course course = coursebo.getCourseById(courseId);
        String studentId=studentid.getText();
        Student student=studentbo.getStudentById(studentId);
      String date=datelbl.getText();

      RegistrationDTO registrationDTO=new RegistrationDTO(id,stname,paid,course,student,date);

      boolean isAdded=registrationbo.addRegistration(registrationDTO);
      if(isAdded){
          cleartextFields();
          getallRegistrations();
          Alert alert=new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Information");
          alert.setContentText("Registration is done");
          alert.showAndWait();
      } else {
          Alert alert=new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setContentText("Registration is not done");
          alert.showAndWait();
      }

    }

    private void cleartextFields() {
        regisid.clear();
        upfrontpayment.clear();
        tobePayment.clear();
        studentid.clear();
        courseid.getSelectionModel().clearSelection();
        coursename.clear();
        amount.clear();
        mobile.clear();
    }

    public void updateonAction(ActionEvent actionEvent) {
        String id=regisid.getText();
        String stname=upfrontpayment.getText();
        String paid=tobePayment.getText();
        String courseId = courseid.getValue().toString(); // String ID from ComboBox
        Course course = coursebo.getCourseById(courseId);
        String studentId=studentid.getText();
        Student student=studentbo.getStudentById(studentId);
        String date=datelbl.getText();

        RegistrationDTO registrationDTO=new RegistrationDTO(id,stname,paid,course,student,date);

        boolean isUpdated=registrationbo.updateRegistration(registrationDTO);
        if(isUpdated){
            cleartextFields();
            getallRegistrations();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("update Registration is done");
            alert.showAndWait();
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Registration is not update");
            alert.showAndWait();
        }
    }

    public void coursedetailONAction(ActionEvent actionEvent) {
      String id=courseid.getValue().toString();
        CourseDTO courseDTO=coursebo.getCourseValues(id);
        coursename.setText(courseDTO.getPro_name());
        amount.setText(courseDTO.getFee());
    }

    public void studentonAction(ActionEvent actionEvent) {
        String tel=mobile.getText();
        StudentDTO studentDTO=studentbo.getstudentdata(tel);
        studentid.setText(studentDTO.getId());
    }

    public void amountpepaidaction(ActionEvent actionEvent) {
        int payment= Integer.parseInt(upfrontpayment.getText());
        int amounts= Integer.parseInt(amount.getText());
        int toBePaid=(amounts-payment);
        tobePayment.setText(String.valueOf(toBePaid));

    }

    public void searchonaction(ActionEvent actionEvent) {
        String id=regisid.getText();

        RegistrationDTO registrationDTO=registrationbo.getregistrations(id);
        regisid.setText(registrationDTO.getRegi_id());
        upfrontpayment.setText(registrationDTO.getUpfront_payment());
        tobePayment.setText(registrationDTO.getTobePaid());
        mobile.setText(registrationDTO.getStudent().getTell());
        courseid.setValue(registrationDTO.getCourses().getPro_id());
        studentid.setText(registrationDTO.getStudent().getId());
        amount.setText(registrationDTO.getCourses().getFee());


    }


    public void upfrontPaymentAction(KeyEvent keyEvent) {

    }
}
