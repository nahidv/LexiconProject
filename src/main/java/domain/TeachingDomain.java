package domain;

/**
 * Created by Nahid on 2017-04-05.
 */
public class TeachingDomain {
    private long id;
    private CourseDomain course;
    private PersonDomain teacher;



    public TeachingDomain(CourseDomain course, PersonDomain teacher){
        this.course = course;
        this.teacher = teacher;
    }

    public TeachingDomain(long id, CourseDomain course, PersonDomain teacher) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonDomain getTeacher(){return teacher;}

    public void setTeacher(PersonDomain teacher){this.teacher = teacher;}

    public CourseDomain getCourse(){return course;}

    public void setCourse(CourseDomain course){this.course = course;}
}
