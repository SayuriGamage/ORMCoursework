package lk.ijse.dao.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.Studentdao;
import lk.ijse.entity.Course;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentdaoImpl implements Studentdao {
    @Override
    public boolean addStudent(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }

    }

    @Override
    public Student search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Student student = null;
        try {
            String hql = "FROM Student WHERE id = :id";
            student = session.createQuery(hql, Student.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return student;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.delete(student);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "from Student";

        return session.createQuery(hql, Student.class).list();
    }

    @Override
    public Student getstudentdata(String tell) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Student> query = session.createQuery("from Student where tell =:id ").setParameter("id", tell);
        Student student = query.getSingleResult();
        session.close();

        return student;
    }

    @Override
    public Student getStudentbyId(String studentId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            session.getTransaction().commit();
            return student;
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


