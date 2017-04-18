package ejb;

import domain.CourseDomain;
import domain.PersonDomain;
import domain.RegisterDomain;
import domain.RoleDomain;
import jpa.Course;
import jpa.Person;
import jpa.Register;
import jpa.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nahid on 2017-04-11.
 */
@Stateless
public class RegisterServiceImpl implements RegisterService {
    @PersistenceContext
    EntityManager em;

    @Override
    public void addRegister(RegisterDomain register) {
        Course c =em.find(Course.class, register.getCourse().getId());
        Person p = em.find(Person.class,register.getStudent().getId());
        Register r = new Register(c,p);
        em.persist(r);
    }

    @Override
    public void updateRegister(RegisterDomain register) {
        Register r = em.find(Register.class,register.getId());
        Course c = em.find(Course.class, register.getCourse().getId());
       // Course c = new Course(register.getCourse().getName(),register.getCourse().getStartDate(),register.getCourse().getEndDate());

        Person p = em.find(Person.class, register.getStudent().getId());
        r.setCourse(c);
        r.setStudent(p);
        em.merge(r);

    }

    @Override
    public RegisterDomain getRegister(Long id) {
        Register r = em.find(Register.class,id);
        CourseDomain c = new CourseDomain(r.getCourse().getName(),r.getCourse().getStartDate(),r.getCourse().getendDate());
        RoleDomain role = new RoleDomain(r.getStudent().getRole().getRoleName());
        PersonDomain p = new PersonDomain(r.getStudent().getFirstName(), r.getStudent().getLastName(), r.getStudent().getUserName(), r.getStudent().getPassword(), role);
        return new RegisterDomain(r.getId(),c, p);
    }

    @Override
    public void remove(Long id) {
        Register r = em.find(Register.class,id);
        em.remove(r);

    }

    @Override
    public List<RegisterDomain> getRegisters() {
        List<Register> l = em.createNamedQuery("selectAllRegisters").getResultList();
        return l.stream().map(r->new RegisterDomain(r.getId(),new CourseDomain(r.getCourse().getName(),r.getCourse().getStartDate(),r.getCourse().getendDate()), new PersonDomain(r.getStudent().getFirstName(), r.getStudent().getLastName(), r.getStudent().getUserName(), r.getStudent().getPassword(), new RoleDomain(r.getStudent().getRole().getRoleName())))).collect(Collectors.toList());
    }
}
