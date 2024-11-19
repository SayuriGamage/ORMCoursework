package lk.ijse.dao.impl;

import jakarta.persistence.NoResultException;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.Userdao;
import lk.ijse.entity.User;
import org.hibernate.Session;

import java.util.List;

public class UserdaoImpl implements Userdao {
    @Override
    public boolean ifHaveAdmins() {
        boolean ishaveadmins = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "from User where role='admin'";
        try {
            ishaveadmins = session.createQuery(hql).list().size() > 0;
        } catch (Exception e) {
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

    @Override
    public boolean saveUser(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public User getdatas(String ids) {
        Session session = FactoryConfiguration.getInstance().getSession();
        User user = null;

        try {
            String hql = "FROM User WHERE id = :username";
            user = session.createQuery(hql, User.class)
                    .setParameter("username", ids)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no result is found
            System.out.println("No user found with userid: " + ids);
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
    public boolean deleteUser(String ids) {

        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            session.delete(getdatas(ids));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }



    @Override
    public List<User> getAllUsers() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "from User";

        return session.createQuery(hql, User.class).list();
    }

    @Override
    public void changePassword(String email, String password) {

        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            String hql = "UPDATE User SET password = :password WHERE email = :email";
            session.createQuery(hql)
                    .setParameter("password", password)
                    .setParameter("email", email)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public boolean checkemail(String email) {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "FROM User WHERE email = :email";
        try {
            User user = session.createQuery(hql, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }


}

