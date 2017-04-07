package jpa;

import javax.persistence.*;

/**
 * Created by Nahid on 2017-04-05.
 */

@Entity
@NamedQueries({
        @NamedQuery(name="selectAllTeachings",query="SELECT teach FROM Teaching teach"),
       // @NamedQuery(name="selectSome",query="SELECT t FROM Teaching t WHERE LOCATE(:filt,t.id) >0 ")
})
public class Teaching {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name= "COURSE_ID")
    private Course course;

    @OneToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name= "PERSON_ID")
    private Person teacher;

    public Teaching() {
    }

    public Teaching (Course course, Person teacher) {
        this.course = course;
        this.teacher = teacher;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse(){return course;}

    public void setCourse(Course course){this.course = course;}

    public Person getTeacher(){return teacher;}

    public void setTeacher(Person teacher){this.teacher= teacher;}
}
