package lk.ijse.bo;

import javafx.collections.ObservableList;
import lk.ijse.dto.PaymentDetailsDTO;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.entity.Course;
import lk.ijse.entity.PaymentDetails;
import lk.ijse.entity.Registration;
import lk.ijse.entity.Student;

public interface Registrationbo extends SuperBO{
    boolean addRegistration(RegistrationDTO registrationDTO);

    boolean updateRegistration(RegistrationDTO registrationDTO);

    RegistrationDTO getregistrations(String id);

    boolean deleteRegistration(String id);

    ObservableList<RegistrationDTO> getAllRegistrations();


    int registrationCount();

    String getCurrentRegistrationId();

    Registration getRegistrationById(String id);

    boolean addPaymentdetails(PaymentDetailsDTO paymentDetailsDTO);

    PaymentDetailsDTO getPaymentDetails(String id);

    String getpaymentId(String id);

    PaymentDetails searchPymentdetails(String id);

    boolean updateRegistrations(PaymentDetailsDTO paymentDetailsDTO);

    void update(String id, String stname, String amounts, Course course, Student student, String date, String paid,String studentid);

    ObservableList<PaymentDetailsDTO> getAllPaymentDetails();


    PaymentDetailsDTO getPaymentDetailss(String regiid);

}
