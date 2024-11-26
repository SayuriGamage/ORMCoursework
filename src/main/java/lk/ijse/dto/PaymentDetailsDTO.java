package lk.ijse.dto;


import lk.ijse.entity.Registration;
import lk.ijse.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentDetailsDTO {
    private long pay_id;
    private String date;
    private String tobe_paid;
    private String student_id;
    private Registration registration;

    public PaymentDetailsDTO(String date, String paid, String studentId, Registration registration) {
      this.date = date;
      this.tobe_paid = paid;
      this.student_id = studentId;
      this.registration = registration;

    }
    public String getRegistrationId() {
        return registration != null ? registration.getRegi_id() : "";
    }
}
