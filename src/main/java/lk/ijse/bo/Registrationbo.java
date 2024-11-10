package lk.ijse.bo;

import lk.ijse.dto.RegistrationDTO;

public interface Registrationbo extends SuperBO{
    boolean addRegistration(RegistrationDTO registrationDTO);

    boolean updateRegistration(RegistrationDTO registrationDTO);

    RegistrationDTO getregistrations(String id);

}
