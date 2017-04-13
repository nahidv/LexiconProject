package ejb;

import domain.*;
import jpa.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nahid on 2017-04-13.
 */

@Stateless
public class AttendanceServiceImpl implements AttendanceService {
    @PersistenceContext
    EntityManager em;


    @Override
    public void addAttendance(AttendanceDomain attendance) {
        Register r =em.find(Register.class, attendance.getRegister().getId());
        Attendance a = new Attendance(r, attendance.getDate(),attendance.getAttend());
        em.persist(a);
    }

    @Override
    public void updateAttendance(AttendanceDomain attendance) {
        Attendance a = em.find(Attendance.class,attendance.getId());
        Role role = new Role(a.getRegister().getStudent().getRole().getRoleName());
        Course c = new Course(a.getRegister().getCourse().getName(),a.getRegister().getCourse().getStartDate(),a.getRegister().getCourse().getendDate());
        Person p = new Person(a.getRegister().getStudent().getFirstName(),a.getRegister().getStudent().getLastName(),a.getRegister().getStudent().getUserName(),a.getRegister().getStudent().getPassword(),role);
        Register r = new Register(c,p);
        a.setRegister(r);
        a.setDate(attendance.getDate());
        a.setAttend(attendance.getAttend());
        em.merge(a);
    }

    @Override
    public AttendanceDomain getAttendance(Long id) {
        Attendance a  = em.find(Attendance.class,id);
        CourseDomain c = new CourseDomain(a.getRegister().getCourse().getName(),a.getRegister().getCourse().getStartDate(),a.getRegister().getCourse().getendDate());
        RoleDomain role = new RoleDomain(a.getRegister().getStudent().getRole().getRoleName());
        PersonDomain p = new PersonDomain(a.getRegister().getStudent().getFirstName(), a.getRegister().getStudent().getLastName(), a.getRegister().getStudent().getUserName(), a.getRegister().getStudent().getPassword(), role);
        RegisterDomain r = new RegisterDomain(c,p);
        return new AttendanceDomain(a.getId(),r,a.getDate(),a.getAttend());
    }

    @Override
    public void remove(Long id) {
        Attendance a =em.find(Attendance.class,id);
        em.remove(a);
    }

    @Override
    public List<AttendanceDomain> getAttendances() {
      List<Attendance> l = em.createNamedQuery("selectAllAttendance").getResultList();
       return l.stream().map(a->new AttendanceDomain(a.getId(),new RegisterDomain(new CourseDomain(a.getRegister().getCourse().getName(),a.getRegister().getCourse().getStartDate(),a.getRegister().getCourse().getendDate()),
                                new PersonDomain(a.getRegister().getStudent().getFirstName(),a.getRegister().getStudent().getLastName(),a.getRegister().getStudent().getUserName(),
                                a.getRegister().getStudent().getPassword(), new RoleDomain(a.getRegister().getStudent().getRole().getRoleName()))),
                               a.getDate(), a.getAttend())).collect(Collectors.toList());

    }
}


