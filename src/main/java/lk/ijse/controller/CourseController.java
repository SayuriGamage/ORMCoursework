package lk.ijse.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.Coursebo;
import lk.ijse.bo.impl.BOFactory;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.StudentDTO;

import java.io.IOException;

public class CourseController {

    public TextField proid;
    public TextField proname;
    public TextField profee;
    public TextField produration;
    public TableView coursetbl;
    public TableColumn colid;
    public TableColumn colname;
    public TableColumn colduration;
    public TableColumn colfee;
    public Button backid;

    Coursebo coursebo= (Coursebo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.course);


    public void initialize(){
        setCellValueFactory();
        getallStudent();
    }

    private void getallStudent() {
        ObservableList<CourseDTO> courseDTOS=coursebo.getAllStudents();
        coursetbl.setItems(courseDTOS);
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("pro_id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("pro_name"));
        colfee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colduration.setCellValueFactory(new PropertyValueFactory<>("duration"));


    }

    private void clearfields(){
        proid.clear();
        proname.clear();
        profee.clear();
        produration.clear();
    }
    public void saveOnAction(ActionEvent actionEvent) {
        String id=proid.getText();
        String name=proname.getText();
        String feec=profee.getText();
        String duration=produration.getText();

        CourseDTO courseDTO =new CourseDTO(id,name,feec,duration);
        boolean result = coursebo.saveCourse(courseDTO);
        System.out.println("menna methanata wenakan wada");

        if(result){
            getallStudent();
            clearfields();
            new Alert(Alert.AlertType.CONFIRMATION,"save course").show();

        } else{
            new Alert(Alert.AlertType.ERROR,"save coourse").show();
        }

    }

    public void UpdateOnAction(ActionEvent actionEvent) {
        String id=proid.getText();
        String name=proname.getText();
        String feec=profee.getText();
        String duration=produration.getText();


        CourseDTO courseDTO =new CourseDTO(id,name,feec,duration);
        boolean result=coursebo.updateCourse(courseDTO);
        if(result) {
            getallStudent();
            clearfields();
            new Alert(Alert.AlertType.CONFIRMATION, "update course").show();
        } else {
            new Alert(Alert.AlertType.ERROR, " not update course").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id=proid.getText();

        boolean result=coursebo.deleteStudent(id);
        if (result) {
            getallStudent();
            clearfields();
            new Alert(Alert.AlertType.CONFIRMATION, "delete course").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "delete course").show();
        }
    }

    public void backonaction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) backid.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String id=proid.getText();


      CourseDTO courseDTO = coursebo.getCourse(id);
        proname.setText(courseDTO.getPro_name());
        profee.setText(courseDTO.getFee());
        produration.setText(courseDTO.getDuration());
    }
}
