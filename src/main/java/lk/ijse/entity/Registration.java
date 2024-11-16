package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Registration {
    @Id
    private String regi_id;
    private String upfront_payment;
    private String amount;
    private String date;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "course_id")
    private Course courses;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "student_id")
    private Student student;


    @OneToMany(mappedBy = "registration", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<PaymentDetails> paymentDetails ;

    public Registration(String regiId, String upfrontPayment, String amount, String date, Course courses, Student student) {
        this.regi_id = regiId;
        this.upfront_payment = upfrontPayment;
        this.amount = amount;
        this.date = date;
        this.courses = courses;
        this.student = student;
    }
}
