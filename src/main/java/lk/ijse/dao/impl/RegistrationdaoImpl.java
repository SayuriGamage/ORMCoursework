package lk.ijse.dao.impl;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.Registrationdao;
import lk.ijse.dto.PaymentDetailsDTO;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.entity.PaymentDetails;
import lk.ijse.entity.Registration;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RegistrationdaoImpl implements Registrationdao {
    @Override
    public boolean addRegistration(Registration registration) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        Object object=session.save(registration);
        if(object!=null){
            transaction.commit();
            session.close();
            return true;
        }
        return false;

    }

    @Override
    public boolean updateRegistration(Registration registration) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(registration);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Registration searchregistration(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
   Registration registration=null;
        try {
            String hql = "FROM Registration WHERE regi_id = :id";
            registration = session.createQuery(hql, Registration.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return registration;
    }

    @Override
    public boolean deleteRegistration(String id) {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        Registration  registration=session.get(Registration.class,id);
        session.delete(registration);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Registration> getAllRegistrations() {
        Session session=FactoryConfiguration.getInstance().getSession();
        String hql="from Registration";
        return session.createQuery(hql,Registration.class).list();

    }

    @Override
    public int registrationCount() {
        Session session=FactoryConfiguration.getInstance().getSession();
        String hql="select count(regi_id) from Registration";
        Query query=session.createQuery(hql);
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public String getCurrentRegistrationId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "SELECT regi_id FROM Registration ORDER BY regi_id DESC";
        String regiId = null;

        try {
            regiId = (String) session.createQuery(hql)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            regiId = null;
        } finally {
            session.close();
        }
        return regiId;
    }

    @Override
    public Registration searchregistrationbyid(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            Registration registration = session.get(Registration.class, id);
            session.getTransaction().commit();
            return registration;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean addPaymentdetails(PaymentDetails paymentDetails) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(paymentDetails);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }



    @Override
    public PaymentDetails searchPymentdetails(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            PaymentDetails paymentDetails = session.get(PaymentDetails.class, id);
            session.getTransaction().commit();
            return paymentDetails;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public String getpaymentId(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "SELECT pay_id FROM PaymentDetails WHERE registration = :id";
        String paymentId = null;
        try {
            paymentId = (String) session.createQuery(hql)
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            paymentId = null;
        } finally {
            session.close();
        }
        return paymentId;
    }

    @Override
    public boolean updateRegistrations(PaymentDetails paymentDetails) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(paymentDetails);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateRegistration2(Registration registrations, Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(registrations);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Registration getRegistrationById2(String id, Session session) {
        return session.get(Registration.class, id);
    }

    @Override
    public boolean updateRegistrations2(PaymentDetails paymentDetails, Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(paymentDetails);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PaymentDetails> getAllPaymentDetails() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM PaymentDetails");
            List<PaymentDetails> paymentDetailsList = query.getResultList();
            session.getTransaction().commit();
            return paymentDetailsList;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<PaymentDetails> getAllPaymentDetailsforpayment(String regiid) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM PaymentDetails WHERE registration = :regiid");
            query.setParameter("regiid", regiid);
            List<PaymentDetails> paymentDetailsList = query.getResultList();
            session.getTransaction().commit();
            return paymentDetailsList;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public PaymentDetailsDTO getPaymentDetailss(String regiid) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM PaymentDetails WHERE registration = :regiid");
            query.setParameter("regiid", regiid);
            PaymentDetails paymentDetails = (PaymentDetails) query.getSingleResult();
            PaymentDetailsDTO paymentDetailsDTO = new PaymentDetailsDTO(paymentDetails.getDate(),paymentDetails.getTobe_paid(),paymentDetails.getStudent_id(),paymentDetails.getRegistration());
            session.getTransaction().commit();
            return paymentDetailsDTO;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


}
