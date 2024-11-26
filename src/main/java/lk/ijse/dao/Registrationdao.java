package lk.ijse.dao;

import lk.ijse.dto.PaymentDetailsDTO;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.entity.PaymentDetails;
import lk.ijse.entity.Registration;
import org.hibernate.Session;

import java.util.List;

public interface Registrationdao extends SuperDAo{

    boolean addRegistration(Registration registration);

    boolean updateRegistration(Registration registration);


    Registration searchregistration(String id);

    boolean deleteRegistration(String id);

    List<Registration> getAllRegistrations();

    int registrationCount();

    String getCurrentRegistrationId();

    Registration searchregistrationbyid(String id);


    boolean addPaymentdetails(PaymentDetails paymentDetails);

    PaymentDetails searchPymentdetails(String id);

    String getpaymentId(String id);

    boolean updateRegistrations(PaymentDetails paymentDetails);

    boolean updateRegistration2(Registration registrations, Session session);


    Registration getRegistrationById2(String id, Session session);

    boolean updateRegistrations2(PaymentDetails paymentDetails, Session session);

    List<PaymentDetails> getAllPaymentDetails();

    List<PaymentDetails> getAllPaymentDetailsforpayment(String regiid);

    PaymentDetailsDTO getPaymentDetailss(String regiid);

}
