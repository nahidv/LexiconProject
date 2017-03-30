package jsf;

import domain.UsersDomain;
import ejb.UsersService;
import jpa.Role;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by Nahid on 2017-03-28.
 */
@ManagedBean
@RequestScoped
public class UserBean {
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Role role;

    @EJB
    UsersService usersService;

    public String addUser(){
        if (getId()==null)
            usersService.addUser(new UsersDomain(getFirstName(),getLastName(), getUserName(),getPassword(), getRole()));
        else
            usersService.updateUser(new UsersDomain(getId(),getFirstName(),getLastName(), getUserName(), getPassword(),getRole()));

        setId(null);
        setFirstName("");
        setLastName("");
        setUserName("");
        setPassword("");
        setRole(null);
        return "users";
    }
    public String editUser(Integer id){
        UsersDomain usersDomain = usersService.getUsers(id);
        setId(usersDomain.getId());
        setFirstName(usersDomain.getFirstName());
        setLastName(usersDomain.getLastName());
        return "users";
    }
    public String removeUser(Integer id){
        usersService.removeUser(id);
        return "users";
    }
    public List<UsersDomain> getUsers(){
        return usersService.getAllUsers();
    }

  /*  public List<PersonDomain> getPersonsFilter(){
        if(myFilter==null || myFilter.equals(""))
            return personService.getPersons();
        else
            return personService.getPersonsFirstNameContain(myFilter);
    }
*/
    public String getSubmitButtonLabel(){
        if (id==null)
            return "Add";
        else
            return "Update";
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

    public String getUserName() {return userName;}

    public void setUserName(String userName){this.userName = userName;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}

    public Role getRole(){return role;}

    public void setRole(Role role){this.role= role;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*public String getMyFilter(){
        return myFilter;
    }
    public void setMyFilter(String myFilter){
        this.myFilter= myFilter;
    }*/
}
