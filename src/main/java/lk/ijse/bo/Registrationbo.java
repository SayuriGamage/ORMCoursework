package lk.ijse.bo;

import javafx.collections.ObservableList;
import lk.ijse.dto.PaymentDetailsDTO;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.entity.Registration;

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

}
