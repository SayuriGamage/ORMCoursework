package lk.ijse.dao.impl;

import jakarta.persistence.NoResultException;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.Coursedao;
import lk.ijse.dto.CourseDTO;
import lk.ijse.entity.Course;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CoursedaoImpl implements Coursedao {
    @Override
    public boolean addCourse(Course course) {
        Session  session= FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
            return true;

        }catch (Exception e){
             e.printStackTrace();
             session.getTransaction().rollback();
             return false;
        }
    }

    @Override
    public boolean updateCourse(Course course) {
        Session session=FactoryConfiguration.getInstance().getSession();
        try {
            session.getTransaction();
            session.update(course);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean deleteCourse(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
         Course course = session.get(Course.class, id);
            session.delete(course);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Course searchcourse(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Course course = null;
        try {
            String hql = "FROM Course WHERE pro_id = :id";
            course = session.createQuery(hql, Course.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return course;
    }

    @Override
    public List<Course> getAllCourse() {
        Session session=FactoryConfiguration.getInstance().getSession();
        String hql="from Course";

        return session.createQuery(hql,Course.class).list();
    }

    @Override
    public List<String> getCourseIds() {
        Session session=FactoryConfiguration.getInstance().getSession();
        Query<String> query=session.createNativeQuery("select pro_id from Course",String.class);
        List<String> cllist=query.list();
        session.close();
        return cllist;

    }

    @Override
    public Course searchcoursedetails(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Course> query = session.createQuery("from Course where pro_id =:id ").setParameter("id",id);
        Course course = query.getSingleResult();
        session.close();

        return course;
    }

    @Override
    public Course getcourseById(String courseId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            session.getTransaction().commit();
            return course;
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
    public String getCurrentCourseId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "SELECT pro_id FROM Course ORDER BY pro_id DESC";
        String courseId = null;

        try {
            courseId = (String) session.createQuery(hql)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            courseId = null;
        } finally {
            session.close();
        }
        return courseId ;
    }

}
