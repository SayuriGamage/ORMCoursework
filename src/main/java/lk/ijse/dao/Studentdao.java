package lk.ijse.dao;

import lk.ijse.entity.Student;

import java.util.List;

public interface Studentdao extends SuperDAo {
    boolean addStudent(Student student);

    boolean updateStudent(Student student);

    Student search(String id);

    boolean delete(String id);

    List<Student> getAllStudents();

    Student getstudentdata(String tel);

    Student getStudentbyId(String studentId);

}
