package lk.ijse.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "paymentdetails")
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pay_id;
    private String date;
    private String tobe_paid;
    private String student_id;
    @ManyToOne (cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "registration_id")
    private Registration registration;


    public PaymentDetails(String date, String tobePaid, String studentId, Registration registration) {
        this.date = date;
        this.tobe_paid = tobePaid;
        this.student_id = studentId;
        this.registration = registration;
    }
}
