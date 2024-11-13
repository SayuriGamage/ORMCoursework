package lk.ijse.dao;

import lk.ijse.dto.CourseDTO;
import lk.ijse.entity.Course;

import java.util.List;

public interface Coursedao extends SuperDAo{
    boolean addCourse(Course course);

    boolean updateCourse(Course course);

    boolean deleteCourse(String id);

    Course searchcourse(String id);

    List<Course> getAllCourse();

    List<String> getCourseIds();

    Course searchcoursedetails(String id);

    Course getcourseById(String courseId);

    String getCurrentCourseId();

}
