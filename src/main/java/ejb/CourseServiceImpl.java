package ejb;

import domain.CourseDomain;
import jpa.Course;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nahid on 2017-03-23.
 */
@Stateless
public class CourseServiceImpl implements CourseService {
    @PersistenceContext
    EntityManager em1;


    @Override
    public void addCourse(CourseDomain course) {
        Course c = new Course(course.getName(),course.getStartDate(),course.getEndDate());
        em1.persist(c);
    }

    @Override
    public void updateCourse(CourseDomain course) {
        Course c = em1.find(Course.class,course.getId());
        c.setName(course.getName());
        c.setstartDate(course.getStartDate());
        c.setendDate(course.getEndDate());
        em1.merge(c);
    }

    @Override
    public CourseDomain getCourse(Integer id) {

        Course c = em1.find(Course.class,id);
        return new CourseDomain(c.getId(),c.getName(),c.getStartDate(),c.getendDate());
    }

    @Override
    public void removeCourse(Integer id) {
        Course c = em1.find(Course.class,id);
        em1.remove(c);
    }

    @Override
    public List<CourseDomain> getCourses() {

        List<Course> l = em1.createNamedQuery("selectAllCourse").getResultList();

        return l.stream().map(c->new CourseDomain(c.getId(),c.getName(),c.getStartDate(),c.getendDate())).collect(Collectors.toList());
    }
}
