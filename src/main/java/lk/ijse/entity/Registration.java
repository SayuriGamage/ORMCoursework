package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Registration {
    @Id
    private String regi_id;
    private String upfront_payment;
    private String tobePaid;
    private String date;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course courses;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
