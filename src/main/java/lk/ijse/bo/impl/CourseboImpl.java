package lk.ijse.bo.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.Coursebo;
import lk.ijse.dao.Coursedao;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseboImpl implements Coursebo {

    Coursedao coursedao= (Coursedao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.course);
    @Override
    public boolean saveCourse(CourseDTO courseDTO) {
        return coursedao.addCourse(new Course(courseDTO.getPro_id(),courseDTO.getPro_name(),courseDTO.getFee(),courseDTO.getDuration()));

    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) {
        return coursedao.updateCourse(new Course(courseDTO.getPro_id(),courseDTO.getPro_name(),courseDTO.getFee(),courseDTO.getDuration()));

    }

    @Override
    public boolean deleteStudent(String id) {
        return coursedao.deleteCourse(id);
    }

    @Override
    public CourseDTO getCourse(String id) {
        Course course=coursedao.searchcourse(id);
        if(course == null){
            return null;
        } else {
            return new CourseDTO(course.getPro_id(),course.getPro_name(),course.getFee(),course.getDuration());
        }
    }

    @Override
    public ObservableList<CourseDTO> getAllStudents() {
        List<CourseDTO> courseDTOS=new ArrayList<>();
        List<Course> courses=coursedao.getAllCourse();
        for(Course course : courses){
            courseDTOS.add(new CourseDTO(course.getPro_id(),course.getPro_name(),course.getFee(),course.getDuration()));
        }
        return FXCollections.observableArrayList(courseDTOS);
    }

    @Override
    public List<String> getCourseIds() {
        return coursedao.getCourseIds();
    }

    @Override
    public CourseDTO getCourseValues(String id) {
       Course c=coursedao.searchcoursedetails(id);
        return new CourseDTO(c.getPro_name(),c.getFee());
    }

    @Override
    public Course getCourseById(String courseId) {
        return coursedao.getcourseById(courseId);
    }

    @Override
    public String getCurrentCourseId() {
        return coursedao.getCurrentCourseId();
    }


}
