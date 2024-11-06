package lk.ijse.dao.impl;

import jakarta.persistence.NoResultException;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.Userdao;
import lk.ijse.entity.User;
import org.hibernate.Session;

public class UserdaoImpl implements Userdao {
    @Override
    public boolean ifHaveAdmins() {
       boolean ishaveadmins = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql="from User where role='admin'";
        try {
            ishaveadmins=session.createQuery(hql).list().size()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ishaveadmins;
    }

    @Override
    public User getdata(String username) {
        Session session = FactoryConfiguration.getInstance().getSession();
        User user = null;

        try {
            String hql = "FROM User WHERE username = :username";
            user = session.createQuery(hql, User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no result is found
            System.out.println("No user found with username: " + username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return user;
    }

    @Override
    public boolean registerAdmin(User user) {
        System.out.println(user);
        boolean result = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

