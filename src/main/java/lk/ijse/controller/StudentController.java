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
import lk.ijse.bo.Studentbo;
import lk.ijse.bo.Userbo;
import lk.ijse.bo.impl.BOFactory;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.User;
import lk.ijse.util.Regex;

import java.io.IOException;

public class StudentController {

    public AnchorPane studenttbl;
    public TextField stname;
    public TextField staddress;
    public TextField sttell;
    public TextField stusid;
    public TableColumn sttblname;
    public TableColumn sttblmobile;
    public TableColumn sttbladdress;
    public TableColumn sttbluserId;
    public Button backbutton;
    public TableView tblStudent;


    Studentbo studentbo= (Studentbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.student);
    Userbo userbo= (Userbo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.user);

public void initialize(){

    setcellvaluefactory();
    getallStudent();
    String currentStudentId=null;
    currentStudentId=studentbo.getCurrentStudentId();
    String nextempId = generateNextempId(currentStudentId);
    stusid.setText(nextempId);
}

    private String generateNextempId(String currentStudentId) {
        if (currentStudentId != null && currentStudentId.matches("^STU\\d+$")) {

            String numericPart = currentStudentId.substring(3);
            try {

                int orderId = Integer.parseInt(numericPart) + 1;

                return "STU" + String.format("%03d", orderId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "STU001";
    }

    private void clearfields(){
    stname.clear();
    staddress.clear();
    sttell.clear();
    stusid.clear();
}

    private void getallStudent() {
        ObservableList <StudentDTO> studentDTOS=studentbo.getAllStudents();
        tblStudent.setItems(studentDTOS);
    }


    private void setcellvaluefactory() {
        sttblname.setCellValueFactory(new PropertyValueFactory<>("id"));
        sttbluserId.setCellValueFactory(new PropertyValueFactory<>("name"));
        sttblmobile.setCellValueFactory(new PropertyValueFactory<>("tell"));
        sttbladdress.setCellValueFactory(new PropertyValueFactory<>("address")); 

    }

    public void saveOnAction(ActionEvent actionEvent) {
      if (isValid()){
          String id=stusid.getText();
          String name=stname.getText();
          String address= staddress.getText();
          String tel=sttell.getText();
          String username=LoginContoller.liveUserId;
          User user=userbo.getUsers(username);

          StudentDTO studentDTO=new StudentDTO(id,name,address,tel,user);
          boolean result=studentbo.saveStudent(studentDTO);
          System.out.println("menna metanata wenakan wda 1");
          if(result){
              getallStudent();
              clearfields();
              String currentStudentId=null;
              currentStudentId=studentbo.getCurrentStudentId();
              String nextempId = generateNextempId(currentStudentId);
              stusid.setText(nextempId);
              new   Alert(Alert.AlertType.CONFIRMATION,"save Student").show();
          }
          else {
              new Alert(Alert.AlertType.ERROR,"save Student").show();
          }
      }else {
          new Alert(Alert.AlertType.ERROR,"wrong inputs").show();
      }
    }

    public void updateOnAction(ActionEvent actionEvent) {
    String id=stusid.getText();
    String name=stname.getText();
    String address= staddress.getText();
    String tel=sttell.getText();

    StudentDTO studentDTO=new StudentDTO(id,name,address,tel);
    boolean result=studentbo.updateStudent(studentDTO);
    if(result) {
        getallStudent();
        clearfields();
        String currentStudentId=null;
        currentStudentId=studentbo.getCurrentStudentId();
        String nextempId = generateNextempId(currentStudentId);
        stusid.setText(nextempId);
        new Alert(Alert.AlertType.CONFIRMATION, "update Studet").show();
    } else {
        new Alert(Alert.AlertType.ERROR, "update Studet").show();
    }
    }

    public void searchOnAction(ActionEvent actionEvent) {
     String id=stusid.getText();


       StudentDTO studentDTO = studentbo.getStudent(id);
           stname.setText(studentDTO.getName());
           staddress.setText(studentDTO.getAddress());
           sttell.setText(studentDTO.getTell());

       }




    public void deleteOnAction(ActionEvent actionEvent) {
        String id=stusid.getText();

        boolean result=studentbo.deleteStudent(id);
        if (result) {
            getallStudent();
            clearfields();
            String currentStudentId=null;
            currentStudentId=studentbo.getCurrentStudentId();
            String nextempId = generateNextempId(currentStudentId);
            stusid.setText(nextempId);
            new Alert(Alert.AlertType.CONFIRMATION, "delete Studet").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "delete Studet").show();
        }
    }

    public void updatebackOnActionnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) backbutton.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

    public void AddressonAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.ADDRESS,staddress);
    }

    public void nameonAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.NAME,stname);
    }

    public void tellOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.MOBILE,sttell);

    }
    public boolean isValid(){
    if (!Regex.setTextColor(lk.ijse.util.TextField.ADDRESS,staddress)) return false;
    if (!Regex.setTextColor(lk.ijse.util.TextField.NAME,stname)) return false;
    if (!Regex.setTextColor(lk.ijse.util.TextField.MOBILE,sttell)) return false;
    return true;
    }
}
