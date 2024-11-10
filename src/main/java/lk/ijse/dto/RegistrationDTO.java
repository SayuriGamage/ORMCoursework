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
    private String tobePaid;
    private String date;
    private Course courses;
    private Student student;

    public RegistrationDTO(String id, String stname, String paid, Course courseId, Student studentId, String date) {
        this.regi_id=id;
        this.upfront_payment=stname;
        this.tobePaid=paid;
        this.date=date;
        this.student=studentId;
        this.courses=courseId;


    }
}
