package lk.ijse.dto;

import lk.ijse.entity.Course;
import lk.ijse.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrationDTO {
    private String regi_id;
    private String upfront_payment;
    private String amount;
    private String date;
    private Course courses;
    private Student student;




    public RegistrationDTO(String id, String stname, String paid, Course course, Student student, String date) {
        this.regi_id = id;
        this.upfront_payment = stname;
        this.amount = paid;
        this.courses = course;
        this.student = student;
        this.date = date;
    }

    public String getCourseId() {
        return courses != null ? courses.getPro_id() : "";
    }

    public String getStudentId() {
        return student != null ? student.getId() : "";
    }



}
