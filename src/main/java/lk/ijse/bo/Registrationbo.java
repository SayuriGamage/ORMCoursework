package lk.ijse.bo;

import javafx.collections.ObservableList;
import lk.ijse.dto.RegistrationDTO;

public interface Registrationbo extends SuperBO{
    boolean addRegistration(RegistrationDTO registrationDTO);

    boolean updateRegistration(RegistrationDTO registrationDTO);

    RegistrationDTO getregistrations(String id);

    boolean deleteRegistration(String id);

    ObservableList<RegistrationDTO> getAllRegistrations();

}
