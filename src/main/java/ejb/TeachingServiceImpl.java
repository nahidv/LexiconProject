package ejb;

import domain.CourseDomain;
import domain.PersonDomain;
import domain.RoleDomain;
import domain.TeachingDomain;
import jpa.Course;
import jpa.Person;
import jpa.Role;
import jpa.Teaching;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nahid on 2017-04-05.
 */

@Stateless
public class TeachingServiceImpl implements TeachingService {
    @PersistenceContext
    EntityManager em;


    @Override
    public void addTeaching(TeachingDomain teaching) {
        Course c =em.find(Course.class, teaching.getCourse().getId());
        Person p = em.find(Person.class,teaching.getTeacher().getId());
        Teaching t = new Teaching(c,p);
        em.persist(t);
    }

    @Override
    public void updateTeaching(TeachingDomain teaching) {
        Teaching t = em.find(Teaching.class,teaching.getId());
        Course c= em.find(Course.class,teaching.getCourse().getId() );
        Person p = em.find(Person.class, teaching.getTeacher().getId());
        t.setCourse(c);
        t.setTeacher(p);
        em.merge(t);
    }

    @Override
    public TeachingDomain getTeaching(Long id) {
        Teaching t = em.find(Teaching.class,id);
        CourseDomain c = new CourseDomain(t.getCourse().getName(),t.getCourse().getStartDate(),t.getCourse().getendDate());
        RoleDomain r = new RoleDomain(t.getTeacher().getRole().getRoleName());
        PersonDomain p = new PersonDomain(t.getTeacher().getFirstName(), t.getTeacher().getLastName(), t.getTeacher().getUserName(), t.getTeacher().getPassword(), r);
        return new TeachingDomain(t.getId(),c, p);
    }

    @Override
    public void removeTeaching(Long id) {
        Teaching t = em.find(Teaching.class,id);
        em.remove(t);
    }

    @Override
    public List<TeachingDomain> getTeachings() {
        List<Teaching> l = em.createNamedQuery("selectAllTeachings").getResultList();
        return l.stream().map(t->new TeachingDomain(t.getId(),new CourseDomain(t.getCourse().getName(),t.getCourse().getStartDate(),t.getCourse().getendDate()), new PersonDomain(t.getTeacher().getFirstName(), t.getTeacher().getLastName(), t.getTeacher().getUserName(), t.getTeacher().getPassword(), new RoleDomain(t.getTeacher().getRole().getRoleName())))).collect(Collectors.toList());
    }
}
