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
        //Teaching t = em.find(Teaching.class,attendance.getTeaching().getId());
        Attendance a = new Attendance(r, attendance.getDate(),attendance.getAttend());
        em.persist(a);
    }

    @Override
    public void updateAttendance(AttendanceDomain attendance) {
        Attendance a = em.find(Attendance.class,attendance.getId());
       // Role role = new Role(a.getRegister().getStudent().getRole().getRoleName());
       // Course c = em.find(Course.class, attendance.getRegister().getId());
      //  Course c = new Course(a.getRegister().getCourse().getName(),a.getRegister().getCourse().getStartDate(),a.getRegister().getCourse().getendDate());
       // Person p = new Person(a.getRegister().getStudent().getFirstName(),a.getRegister().getStudent().getLastName(),a.getRegister().getStudent().getUserName(),a.getRegister().getStudent().getPassword(),role);
        Register r = em.find(Register.class, attendance.getRegister().getId());
      //  Register r = new Register(c);
        // Teaching t = em.find(Teaching.class, attendance.getTeaching().getId());
        a.setRegister(r);
        //a.setTeaching(t);
        a.setDate(attendance.getDate());
        a.setAttend(attendance.getAttend());
        em.merge(a);
    }

    @Override
    public AttendanceDomain getAttendance(Long id) {
        Attendance a  = em.find(Attendance.class,id);
        CourseDomain c = new CourseDomain(a.getRegister().getCourse().getName(),a.getRegister().getCourse().getStartDate(),a.getRegister().getCourse().getendDate());
        RoleDomain role = new RoleDomain(a.getRegister().getStudent().getRole().getRoleName());
        PersonDomain ps = new PersonDomain(a.getRegister().getStudent().getFirstName(), a.getRegister().getStudent().getLastName(), a.getRegister().getStudent().getUserName(), a.getRegister().getStudent().getPassword(), role);
        RegisterDomain r = new RegisterDomain(c,ps);
        //PersonDomain pt = new PersonDomain(a.getTeaching().getTeacher().getFirstName(),a.getTeaching().getTeacher().getLastName(),a.getTeaching().getTeacher().getUserName(),a.getTeaching().getTeacher().getPassword(), role);
      //  TeachingDomain t = new TeachingDomain(c,pt);
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
                                a.getRegister().getStudent().getPassword(), new RoleDomain(a.getRegister().getStudent().getRole().getRoleName()))), a.getDate(), a.getAttend()
                                )).collect(Collectors.toList());

    }
//new TeachingDomain(new CourseDomain(a.getTeaching().getCourse().getName(),a.getTeaching().getCourse().getStartDate(),a.getTeaching().getCourse().getendDate()),
                                      //  new PersonDomain(a.getTeaching().getTeacher().getFirstName(),a.getTeaching().getTeacher().getLastName(),
                                   //     a.getTeaching().getTeacher().getUserName(),a.getTeaching().getTeacher().getPassword(),
                                    //    new RoleDomain(a.getRegister().getStudent().getRole().getRoleName())
    @Override
    public List<AttendanceDomain> getStudentsPerCourse(Integer id) {
        List<Attendance>l=em.createNamedQuery("selectRegisteredStudentsforCourse").setParameter("id", id).getResultList();
         return l.stream().map(a->new AttendanceDomain(a.getId(),new RegisterDomain(new CourseDomain(a.getRegister().getCourse().getName(),a.getRegister().getCourse().getStartDate(),a.getRegister().getCourse().getendDate()),
                new PersonDomain(a.getRegister().getStudent().getFirstName(),a.getRegister().getStudent().getLastName(),a.getRegister().getStudent().getUserName(),
                        a.getRegister().getStudent().getPassword(), new RoleDomain(a.getRegister().getStudent().getRole().getRoleName()))), a.getDate(), a.getAttend()
        )).collect(Collectors.toList());


    }

    @Override
    public List<AttendanceDomain> getThisAttendance() {
        List<Attendance> l = em.createNamedQuery("selectThisAttendance").setParameter("name", "nahid").getResultList();
        return l.stream().map(a->new AttendanceDomain(a.getId(),new RegisterDomain(new CourseDomain(a.getRegister().getCourse().getName(),a.getRegister().getCourse().getStartDate(),a.getRegister().getCourse().getendDate()),
                new PersonDomain(a.getRegister().getStudent().getFirstName(),a.getRegister().getStudent().getLastName(),a.getRegister().getStudent().getUserName(),
                        a.getRegister().getStudent().getPassword(), new RoleDomain(a.getRegister().getStudent().getRole().getRoleName()))), a.getDate(), a.getAttend()
        )).collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDomain> getCourseAttendance() {
        List<Attendance> l = em.createNamedQuery("selectCourseAttendance").setParameter("name", "java").getResultList();
        return l.stream().map(a->new AttendanceDomain(a.getId(),new RegisterDomain(new CourseDomain(a.getRegister().getCourse().getName(),a.getRegister().getCourse().getStartDate(),a.getRegister().getCourse().getendDate()),
                new PersonDomain(a.getRegister().getStudent().getFirstName(),a.getRegister().getStudent().getLastName(),a.getRegister().getStudent().getUserName(),
                        a.getRegister().getStudent().getPassword(), new RoleDomain(a.getRegister().getStudent().getRole().getRoleName()))), a.getDate(), a.getAttend()
        )).collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDomain> getCourseRegister() {
        List<Attendance> l = em.createNamedQuery("selectCourseRegister").setParameter("name", "java").getResultList();
        return l.stream().map(a->new AttendanceDomain(a.getId(),new RegisterDomain(new CourseDomain(a.getRegister().getCourse().getName(),a.getRegister().getCourse().getStartDate(),a.getRegister().getCourse().getendDate()),
                new PersonDomain(a.getRegister().getStudent().getFirstName(),a.getRegister().getStudent().getLastName(),a.getRegister().getStudent().getUserName(),
                        a.getRegister().getStudent().getPassword(), new RoleDomain(a.getRegister().getStudent().getRole().getRoleName()))), a.getDate(), a.getAttend()
        )).collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDomain> getStudentRegister() {
        List<Attendance> l = em.createNamedQuery("selectStudentRegister").setParameter("name", "java").getResultList();
        return l.stream().map(a->new AttendanceDomain(a.getId(),new RegisterDomain(new CourseDomain(a.getRegister().getCourse().getName(),a.getRegister().getCourse().getStartDate(),a.getRegister().getCourse().getendDate()),
                new PersonDomain(a.getRegister().getStudent().getFirstName(),a.getRegister().getStudent().getLastName(),a.getRegister().getStudent().getUserName(),
                        a.getRegister().getStudent().getPassword(), new RoleDomain(a.getRegister().getStudent().getRole().getRoleName()))), a.getDate(), a.getAttend()
        )).collect(Collectors.toList());
    }


}


