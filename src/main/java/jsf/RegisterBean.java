package jsf;

import domain.CourseDomain;
import domain.PersonDomain;
import domain.RegisterDomain;
import ejb.CourseService;
import ejb.PersonService;
import ejb.RegisterService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by Nahid on 2017-04-11.
 */

@ManagedBean
@RequestScoped
public class RegisterBean {
    private Long id;
    private Integer courseId;
    private Long personId;

    @EJB
    RegisterService registerService;
    @EJB
    PersonService personService;
    @EJB
    CourseService courseService;

    public String addRegister(){

        if (getId()==null) {

            registerService.addRegister(new RegisterDomain(getCourse(),getStudent()));
        }
        else
            registerService.updateRegister(new RegisterDomain(getId(),getCourse(),getStudent()));

        setId(null);
        setCourseId(null);
        setStudentId(null);
        setCourse(null);
        setStudent(null);
        return "register";
    }
    public String editRegister(Long id){
        RegisterDomain registerDomain = registerService.getRegister(id);
        setId(registerDomain.getId());
        setCourse(registerDomain.getCourse().getId());
        setStudent(registerDomain.getStudent().getId());
        return "register";
    }
    public String removeRegister(Long id){
        registerService.remove(id);
        return "register";
    }
    public List<RegisterDomain> getRegisters(){
        return registerService.getRegisters();
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

    public CourseDomain getCourse(){

        CourseDomain courseDomain= courseService.getCourse(getCourseId());
        courseId= courseDomain.getId();
        return courseDomain;
    }

    public void setCourse(Integer courseId){

        this.courseId= courseId;}

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }


    public PersonDomain getStudent(){

        PersonDomain personDomain= personService.getPerson(getStudentId());
        personId= personDomain.getId();
        return personDomain;
    }
    public void setStudent(Long personId){

        this.personId= personId;}

    public Long getStudentId() {
        return personId;
    }

    public void setStudentId(Long personId) {
        this.personId = personId;
    }


}
