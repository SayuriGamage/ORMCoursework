package lk.ijse.bo.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.Registrationbo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.Registrationdao;
import lk.ijse.dto.PaymentDetailsDTO;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.entity.PaymentDetails;
import lk.ijse.entity.Registration;

import java.util.ArrayList;
import java.util.List;

public class RegistrationboImpl implements Registrationbo {
    Registrationdao registrationdao= (Registrationdao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.regi);

    @Override
    public boolean addRegistration(RegistrationDTO registrationDTO) {
        return registrationdao.addRegistration(new Registration(registrationDTO.getRegi_id(),registrationDTO.getUpfront_payment(),registrationDTO.getAmount(),registrationDTO.getDate(),registrationDTO.getCourses(),registrationDTO.getStudent()));

    }

    @Override
    public boolean updateRegistration(RegistrationDTO registrationDTO) {
        return registrationdao.updateRegistration(new Registration(registrationDTO.getRegi_id(),registrationDTO.getUpfront_payment(),registrationDTO.getAmount(),registrationDTO.getDate(),registrationDTO.getCourses(),registrationDTO.getStudent()));

    }

    @Override
    public RegistrationDTO getregistrations(String id) {
        Registration registration= registrationdao.searchregistration(id);
        return new RegistrationDTO(registration.getRegi_id(),registration.getUpfront_payment(),registration.getAmount(),registration.getDate(),registration.getCourses(),registration.getStudent());
    }

    @Override
    public boolean deleteRegistration(String id) {
        return registrationdao.deleteRegistration(id);
    }

    @Override
    public ObservableList<RegistrationDTO> getAllRegistrations() {
        List<Registration> registrations=registrationdao.getAllRegistrations();
        List<RegistrationDTO> registrationDTOS=new ArrayList<>();
        for(Registration registration: registrations){
            registrationDTOS.add(new RegistrationDTO(registration.getRegi_id(),registration.getUpfront_payment(),registration.getAmount(),registration.getDate(),registration.getCourses(),registration.getStudent()));
        }
      return FXCollections.observableArrayList(registrationDTOS);
    }

    @Override
    public int registrationCount() {

        return registrationdao.registrationCount();
    }

    @Override
    public String getCurrentRegistrationId() {

        return registrationdao.getCurrentRegistrationId();
    }

    @Override
    public Registration getRegistrationById(String id) {
        return registrationdao.searchregistrationbyid(id);
    }

    @Override
    public boolean addPaymentdetails(PaymentDetailsDTO paymentDetailsDTO) {
        return registrationdao.addPaymentdetails(new PaymentDetails(paymentDetailsDTO.getDate(),paymentDetailsDTO.getTobe_paid(),paymentDetailsDTO.getStudent_id(),paymentDetailsDTO.getRegistration()));
    }


}
