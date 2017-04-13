package domain;

import java.util.Date;

/**
 * Created by Nahid on 2017-04-12.
 */
public class AttendanceDomain {

    private long id;
    private RegisterDomain register;
    private Date date;
    private boolean attend;



    public AttendanceDomain(RegisterDomain register, Date date, boolean attend){
        this.register= register;
        this.date= date;
        this.attend = attend;
    }

    public AttendanceDomain(long id, RegisterDomain register, Date date, boolean attend) {
        this.id = id;
        this.register= register;
        this.date= date;
        this.attend = attend;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RegisterDomain getRegister(){return register;}

    public void setRegister(RegisterDomain register){this.register = register;}

    public Date getDate(){return date;}

    public void setDate(Date date){this.date= date;}

    public boolean getAttend(){return attend;}

    public void setAttend(boolean attend){this.attend= attend;}
}
