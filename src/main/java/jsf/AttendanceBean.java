package jsf;

import domain.*;
import ejb.*;

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
  //  private Long teachingId;

    private String myFilter;


    @EJB
    AttendanceService attendanceService;
    @EJB
    RegisterService registerService;
    @EJB
    TeachingService teachingService;


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
       // setTeaching(null);
       // setTeachingId(null);
        return "attendance";
    }
    public String editAttendance(Long id){
        AttendanceDomain attendanceDomain = attendanceService.getAttendance(id);
        setId(attendanceDomain.getId());
        setRegister(attendanceDomain.getRegister().getId());
       // setTeaching(attendanceDomain.getTeaching().getId());
        return "attendance";
    }
    public String removeAttendance(Long id){
        attendanceService.remove(id);
        return "attendance";
    }
    public List<AttendanceDomain> getAttendances(){
        return attendanceService.getAttendances();
    }

    public List<AttendanceDomain>getThisAttendance(){return attendanceService.getThisAttendance();}

    public List<AttendanceDomain>getCourseAttendance(){return attendanceService.getCourseAttendance();}

   public List<AttendanceDomain> getStudentsPerCourse(){
        if (getRegister()==null)
            return null;
        else
            return attendanceService.getStudentsPerCourse(getRegister().getCourse().getId());
    }
    public List<AttendanceDomain>getCourseRegister(){
       return attendanceService.getCourseRegister();
    }
    public List<AttendanceDomain>getStudentRegister(){return attendanceService.getStudentRegister();}



    public String getSubmitButtonLabel(){
        if (id==null)
            return "Add";
        else
            return "Update";
    }

    public String submitButtonLabel2(){
        System.out.println(getRegisterId());
            return "attendance";
    }

    public String submitButtonLabel3(){
        return "attendteacher";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegisterDomain getRegister(){

        RegisterDomain registerDomain= registerService.getRegister(getRegisterId());
        if(registerDomain != null)
            registerId= registerDomain.getId();
        return registerDomain;
    }

    public void setRegister(Long registerId){

        this.registerId= registerId;
    }

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

    /*public TeachingDomain getTeaching(){
        TeachingDomain teachingDomain = teachingService.getTeaching(getTeachingId());
        teachingId= teachingDomain.getId();
        return teachingDomain;
    } */
    /*public void setTeaching(Long teachingId){
        this.teachingId= teachingId;
    }
    public Long getTeachingId() {return teachingId;}

    public void setTeachingId(Long teachingId){this.teachingId=teachingId;}*/

    public String getMyFilter(){
        return myFilter;
    }
    public void setMyFilter(String myFilter){
        this.myFilter= myFilter;
    }

}
