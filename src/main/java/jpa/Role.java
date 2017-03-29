package jpa;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nahid on 2017-03-28.
 */
@Entity
@NamedQueries(
        {@NamedQuery(name = "selectAllRoles", query ="select r from Role r" )}
)
@SequenceGenerator(name="seq", initialValue=1, allocationSize=5)
public class Role {
    @Id
  //  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @GeneratedValue
    private int id;
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<Users> students;

    public Role(){}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public int getId(){return id; }

    public void setId(int id){this.id = id;}

    public String getRoleName(){return roleName;}

    public void setRoleName(String roleName){this.roleName= roleName;}

    public List<Users>getStudents(){
        return students;
    }
    public void setStudents(List<Users> students){
        this.students= students;
    }
}
