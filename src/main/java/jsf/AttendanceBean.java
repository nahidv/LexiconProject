package jsf;

import domain.AttendanceDomain;
import domain.CourseDomain;
import domain.PersonDomain;
import domain.RegisterDomain;
import ejb.AttendanceService;
import ejb.CourseService;
import ejb.PersonService;
import ejb.RegisterService;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import java.util.Date;
import java.util.List;

/**
 * Created by Nahid on 2017-04-13.
 */

@javax.faces.bean.ManagedBean
@javax.faces.bean.RequestScoped
public class AttendanceBean {
    private Long id;
    private Long registerId;
    private Date date;
    private boolean attend;

    @EJB
    AttendanceService attendanceService;
    @EJB
    RegisterService registerService;



    public String addAttendance(){

        if (getId()==null) {

            attendanceService.addAttendance(new AttendanceDomain(getRegister(),getDate(),getAttend()));
        }
        else
            attendanceService.updateAttendance(new AttendanceDomain(getId(),getRegister(),getDate(),getAttend()));

        setId(null);
        setRegister(null);
        setRegisterId(null);
        setDate(null);
        setAttend(false);
        return "attendance";
    }
    public String editAttendance(Long id){
        AttendanceDomain attendanceDomain = attendanceService.getAttendance(id);
        setId(attendanceDomain.getId());
        setRegister(attendanceDomain.getRegister().getId());
        return "attendance";
    }
    public String removeAttendance(Long id){
        attendanceService.remove(id);
        return "attendance";
    }
    public List<AttendanceDomain> getAttendances(){
        return attendanceService.getAttendances();
    }

   /* public List<TeachingDomain> getTaachingFilter(){
        if(myFilter==null || myFilter.equals(""))
            return teacingsService.getTeachings();
        else
            return teachingService.getTeachingsFirstNameContain(myFilter);
    }*/

    public String getSubmitButtonLabel(){
        if (id==null)
            return "Add";
        else
            return "Update";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegisterDomain getRegister(){

        RegisterDomain registerDomain= registerService.getRegister(getRegisterId());
        registerId= registerDomain.getId();
        return registerDomain;
    }

    public void setRegister(Long registerId){

        this.registerId= registerId;}

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public Date getDate(){return date;}

    public void setDate(Date date ){this.date= date;}

    public boolean getAttend(){return attend;}

    public void setAttend(boolean attend){this.attend= attend;}


}
