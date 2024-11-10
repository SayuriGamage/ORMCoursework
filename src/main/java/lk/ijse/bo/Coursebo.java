package lk.ijse.bo;

import javafx.collections.ObservableList;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.entity.Course;

import java.util.List;

public interface Coursebo extends SuperBO{
    boolean saveCourse(CourseDTO courseDTO);

    boolean updateCourse(CourseDTO courseDTO);

    boolean deleteStudent(String id);

    CourseDTO getCourse(String id);

    ObservableList<CourseDTO> getAllStudents();

    List<String> getCourseIds();

    CourseDTO getCourseValues(String id);

    Course getCourseById(String courseId);

}
