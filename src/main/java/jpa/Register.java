package jpa;

import javax.persistence.*;

/**
 * Created by Nahid on 2017-04-11.
 */


@Entity
@NamedQueries({
        @NamedQuery(name="selectAllRegisters",query="SELECT r FROM Register r"),
         @NamedQuery(name="selectSomeRegisters1",query="SELECT sr FROM Register sr WHERE LOCATE(:filt,sr.student.firstName) >0 "),
        @NamedQuery(name="selectSomeRegisters2",query="SELECT sr FROM Register sr WHERE LOCATE(:filt,sr.course.name) >0 "),
        @NamedQuery(name="selectRegisteredStudents", query="SELECT r FROM Register r WHERE r.course.name = :name")

})

public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name= "COURSE_ID")
    private Course course;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name= "PERSON_ID")
    private Person student;

    public Register() {
    }

    public Register (Course course, Person teacher) {
        this.course = course;
        this.student = teacher;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse(){return course;}

    public void setCourse(Course course){this.course = course;}

    public Person getStudent(){return student;}

    public void setStudent(Person student){this.student= student;}
}
