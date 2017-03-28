package ejb;

import domain.CourseDomain;

import java.util.List;

/**
 * Created by Nahid on 2017-03-23.
 */
public interface  CourseService {
    void addCourse(CourseDomain course);
    void updateCourse(CourseDomain course);
    CourseDomain getCourse(Integer id);
    void removeCourse(Integer id);
    List<CourseDomain> getCourses();

}
