package lk.ijse.bo.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.Studentbo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.Studentdao;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentboImpl implements Studentbo {

    Studentdao studentdao = (Studentdao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.student);

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        return studentdao.addStudent(new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getTell()));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        return studentdao.updateStudent(new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getTell()));
    }

    @Override
    public StudentDTO getStudent(String id) {
        Student student = studentdao.search(id);
        if (student == null) {
            return null;
        } else {
            return new StudentDTO(student.getId(), student.getName(), student.getAddress(), student.getTell());
        }
    }

    @Override
    public boolean deleteStudent(String id) {
        return studentdao.delete(id);

    }

    @Override
    public ObservableList<StudentDTO> getAllStudents() {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> students = studentdao.getAllStudents();
        for (Student student : students) {
            studentDTOS.add(new StudentDTO(student.getId(), student.getName(), student.getAddress(), student.getTell()));
        }
        return FXCollections.observableArrayList(studentDTOS);
    }

    @Override
    public StudentDTO getstudentdata(String tel) {
        Student student=studentdao.getstudentdata(tel);
        return  new StudentDTO(student.getId());
    }

    @Override
    public Student getStudentById(String studentId) {
        return studentdao.getStudentbyId(studentId);
    }

    @Override
    public int StudentCount() {
        return studentdao.StudentCount();
    }

    @Override
    public String getCurrentStudentId() {
        return studentdao.getCurrentStudentId();
    }


}
