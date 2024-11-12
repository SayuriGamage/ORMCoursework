package lk.ijse.dao.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.Registrationdao;
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
}