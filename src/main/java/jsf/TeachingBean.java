package jsf;

import domain.CourseDomain;
import domain.PersonDomain;
import domain.RoleDomain;
import domain.TeachingDomain;
import ejb.CourseService;
import ejb.PersonService;
import ejb.RoleService;
import ejb.TeachingService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by Nahid on 2017-04-05.
 */

@ManagedBean
@RequestScoped
public class TeachingBean {
    private Long id;
    private Integer courseId;
    private Long personId;

    @EJB
    TeachingService teachingService;
    @EJB
    PersonService personService;
    @EJB
    CourseService courseService;

    public String addTeaching(){

        if (getId()==null) {

            teachingService.addTeaching(new TeachingDomain(getCourse(),getTeacher()));
        }
        else
            teachingService.updateTeaching(new TeachingDomain(getId(),getCourse(),getTeacher()));

        setId(null);
        setCourseId(null);
        setTeacherId(null);
        setCourse(null);
        setTeacher(null);
        return "teaching";
    }
    public String editTeaching(Long id){
        TeachingDomain teachingDomain = teachingService.getTeaching(id);
        setId(teachingDomain.getId());
        setCourse(teachingDomain.getCourse().getId());
        setTeacher(teachingDomain.getTeacher().getId());
        return "teaching";
    }
    public String removeTeaching(Long id){
        teachingService.removeTeaching(id);
        return "teaching";
    }
    public List<TeachingDomain> getTeachings(){
        return teachingService.getTeachings();
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


    public PersonDomain getTeacher(){

        PersonDomain personDomain= personService.getPerson(getTeacherId());
        personId= personDomain.getId();
        return personDomain;
    }
    public void setTeacher(Long personId){

        this.personId= personId;}

    public Long getTeacherId() {
        return personId;
    }

    public void setTeacherId(Long personId) {
        this.personId = personId;
    }


}
