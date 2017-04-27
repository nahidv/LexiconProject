package jsf;

import domain.CourseDomain;
import ejb.CourseService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;
import java.util.List;

/**
 * Created by Nahid on 2017-03-23.
 */
@ManagedBean
@RequestScoped
public class CourseBean {
    private Integer id;
    private String name;
    private Date startDate;
    private Date endDate;

    @EJB
    CourseService courseService;

    public String addCourse(){
        //System.out.println("here");
        if (getId()==null)
            courseService.addCourse(new CourseDomain(getName(), getStartDate(), getEndDate()));
        else
            courseService.updateCourse(new CourseDomain(getId(),getName(),getStartDate(),getEndDate()));

        setId(null);
        setName("");
        setStartDate(null);
        setEndDate(null);
        return "course";
    }
    public String editCourse(Integer id){
        CourseDomain courseDomain = courseService.getCourse(id);
        setId(courseDomain.getId());
        setName(courseDomain.getName());
        setStartDate(courseDomain.getStartDate());
        setEndDate(courseDomain.getEndDate());
        return "course";
    }
    public String removeCourse(Integer id){
        courseService.removeCourse(id);
        return "course";
    }
    public List<CourseDomain> getCourses(){
        return courseService.getCourses();
    }


    public String getSubmitButtonLabel(){
        if (id==null)
            return "Add";
        else
            return "Update";
    }

    public String submitButtonLabel2(){
        System.out.println(getCourses());
        return "attendance";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {  return startDate; }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {  return endDate; }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
