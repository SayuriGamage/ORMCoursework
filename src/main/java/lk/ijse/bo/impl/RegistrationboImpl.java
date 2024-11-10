package lk.ijse.bo.impl;

import lk.ijse.bo.Registrationbo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.Registrationdao;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.entity.Registration;

public class RegistrationboImpl implements Registrationbo {
    Registrationdao registrationdao= (Registrationdao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.regi);
    @Override
    public boolean addRegistration(RegistrationDTO registrationDTO) {
        return registrationdao.addRegistration(new Registration(registrationDTO.getRegi_id(),registrationDTO.getUpfront_payment(),registrationDTO.getTobePaid(),registrationDTO.getDate(),registrationDTO.getCourses(),registrationDTO.getStudent()));

    }

    @Override
    public boolean updateRegistration(RegistrationDTO registrationDTO) {
        return registrationdao.updateRegistration(new Registration(registrationDTO.getRegi_id(),registrationDTO.getUpfront_payment(),registrationDTO.getTobePaid(),registrationDTO.getDate(),registrationDTO.getCourses(),registrationDTO.getStudent()));

    }

    @Override
    public RegistrationDTO getregistrations(String id) {
        Registration registration= registrationdao.searchregistration(id);
        return new RegistrationDTO(registration.getRegi_id(),registration.getTobePaid(),registration.getDate(),registration.getUpfront_payment(),registration.getCourses(),registration.getStudent());
    }
}
