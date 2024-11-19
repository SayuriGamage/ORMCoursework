package lk.ijse.bo.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.bo.Registrationbo;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.Registrationdao;
import lk.ijse.dto.PaymentDetailsDTO;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Course;
import lk.ijse.entity.PaymentDetails;
import lk.ijse.entity.Registration;
import lk.ijse.entity.Student;
import org.hibernate.Session;

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

    @Override
    public PaymentDetailsDTO getPaymentDetails(String id) {
        PaymentDetails paymentDetails= registrationdao.searchPymentdetails(id);
        return new PaymentDetailsDTO(paymentDetails.getPay_id(),paymentDetails.getDate(),paymentDetails.getTobe_paid(),paymentDetails.getStudent_id(),paymentDetails.getRegistration());

    }

    @Override
    public String getpaymentId(String id) {
        return registrationdao.getpaymentId(id);
    }

    @Override
    public PaymentDetails searchPymentdetails(String id) {
        return registrationdao.searchPymentdetails(id);
    }

    @Override
    public boolean updateRegistrations(PaymentDetailsDTO paymentDetailsDTO) {
        return registrationdao.updateRegistrations(new PaymentDetails(paymentDetailsDTO.getDate(),paymentDetailsDTO.getTobe_paid(),paymentDetailsDTO.getStudent_id(),paymentDetailsDTO.getRegistration()));
    }

    @Override
    public void update(String id, String stname, String amounts, Course course, Student student, String date, String paid,String studentId) {
        Session session= FactoryConfiguration.getInstance().getSession();
        RegistrationDTO registrationDTO=new RegistrationDTO(id,stname,amounts,course,student,date);
        Registration registrations = new Registration(
                registrationDTO.getRegi_id(),
                registrationDTO.getUpfront_payment(),
                registrationDTO.getAmount(),
                registrationDTO.getDate(),
                registrationDTO.getCourses(),
                registrationDTO.getStudent()
        );
        boolean isAdded=registrationdao.updateRegistration2(registrations,session);


        if (isAdded) {
            Registration registration=registrationdao.getRegistrationById2(id,session);
            PaymentDetailsDTO paymentDetailsDTO=new PaymentDetailsDTO(date,paid,studentId,registration);
            PaymentDetails paymentDetails = new PaymentDetails(
                    paymentDetailsDTO.getPay_id(),
                    paymentDetailsDTO.getDate(),
                    paymentDetailsDTO.getTobe_paid(),
                    paymentDetailsDTO.getStudent_id(),
                    paymentDetailsDTO.getRegistration()
            );
            boolean isadds=registrationdao.updateRegistrations2(paymentDetails,session);
            if (isadds){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("Registration update is done");
                alert.showAndWait();
            }else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Registration update is not done1");
                alert.showAndWait();
            }
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Registration update is not done2");
            alert.showAndWait();
        }
    }


}
