package lk.ijse.bo;

import javafx.collections.ObservableList;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;

public interface Studentbo extends SuperBO {
    boolean saveStudent(StudentDTO studentDTO);

    boolean updateStudent(StudentDTO studentDTO);

    StudentDTO getStudent(String id);

    boolean deleteStudent(String id);

    ObservableList<StudentDTO> getAllStudents();

    StudentDTO getstudentdata(String tel);

    Student getStudentById(String studentId);

}
