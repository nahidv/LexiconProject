package domain;

/**
 * Created by Nahid on 2017-04-11.
 */
public class RegisterDomain {
    private long id;
    private CourseDomain course;
    private PersonDomain student;



    public RegisterDomain(CourseDomain course, PersonDomain student){
        this.course = course;
        this.student = student;
    }

    public RegisterDomain(long id, CourseDomain course, PersonDomain student) {
        this.id = id;
        this.course = course;
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonDomain getStudent(){return student;}

    public void setStudent(PersonDomain student){this.student = student;}

    public CourseDomain getCourse(){return course;}

    public void setCourse(CourseDomain course){this.course = course;}
}
