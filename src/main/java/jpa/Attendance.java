package jpa;

/**
 * Created by Nahid on 2017-04-12.
 */

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name="selectAllAttendance",query="SELECT a FROM Attendance a"),
        @NamedQuery(name="selectSomeAttendance1",query="SELECT saf FROM Attendance saf WHERE LOCATE(:filt,saf.register.student.firstName) >0 "),
        @NamedQuery(name="selectSomeAttendance2",query="SELECT sal FROM Attendance sal WHERE LOCATE(:filt,sal.register.student.lastName) >0 "),
        @NamedQuery(name="selectRegisteredStudentsforCourse", query="SELECT a FROM Attendance a WHERE a.register.course.id = :id")

       // @NamedQuery(name="selectRegisteredStudentsforCourse", query ="SELECT c FROM Attendance c WHERE LOCATE(:filt,c.register.course.name)>0")
      //  query = "select c.id from Cars c join CarStatus d where c.status = d.status and d.color = 'red'"
        //@NamedQuery(name="selectRegisteredStudentsforCourse", query= "SELECT r FROM Attendance.register r  join Attendance.teaching t WHERE r.course.name = t.course.name"),
      //  @NamedQuery(name="selectRegisteredStudentsforCourse", query= "SELECT a.register.student.firstName FROM Attendance a WHERE a.register.course.id = a.teaching.course.id AND a.teaching.course.name LIKE :name "),
       // @NamedQuery(name="selectRegisteredStudentsforCourse", query= "SELECT a.register.student.firstName FROM Attendance a WHERE  a.teaching.course.id = a.register.course.id AND a.register.course.name LIKE :name  ")
})
public class Attendance {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @ManyToOne(fetch= FetchType.EAGER)
        @JoinColumn(name= "REGISTER_ID")
        private Register register;

        private Date date;
        private boolean attend;

        /*@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name = "TEACHING_ID")
        private Teaching teaching;*/

        public Attendance() {
        }

        public Attendance (Register register, Date date, boolean attend) {
        this.register = register;
        this.date = date;
        this.attend= attend;
       // this.teaching = teaching;
        }
public long getId() {
        return id;
        }

public void setId(long id) {
        this.id = id;
        }

public Register getRegister(){return register;}

public void setRegister(Register register){this.register = register;}

public Date getDate(){return date;}

public void setDate(Date date){this.date = date;}

public boolean getAttend(){return attend;}

public void setAttend(boolean attend){this.attend= attend;}

//public Teaching getTeaching(){return teaching;}

//public void setTeaching(Teaching teaching){this.teaching=teaching;}

}
