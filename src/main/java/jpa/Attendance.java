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
        @NamedQuery(name="selectSomeAttendance2",query="SELECT sal FROM Attendance sal WHERE LOCATE(:filt,sal.register.student.lastName) >0 ")
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

        public Attendance() {
        }

        public Attendance (Register register, Date date, boolean attend) {
        this.register = register;
        this.date = date;
        this.attend= attend;
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

}
