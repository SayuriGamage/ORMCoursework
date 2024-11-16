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
    private String date;
    private String tobe_paid;
    private String student_id;
    private Registration registration;
}
