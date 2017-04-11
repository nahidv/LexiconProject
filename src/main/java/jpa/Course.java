package jpa;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Nahid on 2017-03-23.
 */

@Entity
@NamedQueries(
        {@NamedQuery(name = "selectAllCourse", query ="select c from Course c" )}
        )
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String name;
    private Date startDate;
    private Date endDate;

    @OneToMany(mappedBy = "course")
    private List<Teaching> teachings;


    public Course(){}

    public Course(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public Integer getId() {   return id;  }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setstartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getendDate() {
        return endDate;
    }

    public void setendDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Teaching>getTeachings(){
        return teachings;
    }
    public void setTeachings(List<Teaching> teachings){
        this.teachings= teachings;
    }

}
