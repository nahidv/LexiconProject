package domain;

import jpa.Role;
/**
 * Created by Nahid on 2017-03-25.
 */
public class PersonDomain {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private RoleDomain role;


    public PersonDomain(String firstName, String lastName, String userName, String password, RoleDomain role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.role = role;

    }

    public PersonDomain(long id, String firstName, String lastName, String userName, String password, RoleDomain role) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDomain getRole(){return role;}

    public void setRole(RoleDomain role){this.role = role;}
}
