package lk.ijse.dao;

import lk.ijse.dto.RegistrationDTO;
import lk.ijse.entity.Registration;

import java.util.List;

public interface Registrationdao extends SuperDAo{

    boolean addRegistration(Registration registration);

    boolean updateRegistration(Registration registration);


    Registration searchregistration(String id);

    boolean deleteRegistration(String id);

    List<Registration> getAllRegistrations();

}
