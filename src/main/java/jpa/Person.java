package jpa;

import javax.persistence.*;

/**
 * Created by Solidbeans on 2017-03-20.
 */
@Entity
@NamedQueries({
        @NamedQuery(name="selectAll",query="SELECT p FROM Person p"),
        @NamedQuery(name="selectSome",query="SELECT s FROM Person s WHERE LOCATE(:filt,s.firstName) >0 "),
      //  @NamedQuery(name="selectTeachers",query="SELECT  t FROM Person t WHERE LOCATE(:filt,t.role.roleName)= :name "),
        @NamedQuery(name="selectTeachers", query="SELECT t FROM Person t WHERE t.role.roleName LIKE :name"),
        @NamedQuery(name="selectStudents", query="SELECT s FROM Person s WHERE s.role.roleName LIKE :name")
})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name= "ROLE_ID")
    private Role role;

    //@OneToOne(fetch= FetchType.LAZY, mappedBy="person")

    public Person() {
    }

    public Person(String firstName, String lastName, String userName, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName(){return userName;}

    public void setUserName(String userName){this.userName = userName;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}

    public Role getRole(){return role;}

    public void setRole(Role role){this.role = role;}
}
