package jsf;

import domain.PersonDomain;
import ejb.PersonService;
import ejb.RoleService;
import jpa.Role;
import domain.RoleDomain;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by Solidbeans on 2017-03-20.
 */

@ManagedBean
@RequestScoped
public class PersonBean {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    private Integer roleId;



    private String myFilter;

    @EJB
    PersonService personService;
    @EJB
    RoleService roleService;

    public String addPerson(){


        if (getId()==null) {


            personService.addPerson(new PersonDomain(getFirstName(), getLastName(), getUserName(), getPassword(), getRole()));
        }
        else
            personService.updatePerson(new PersonDomain(getId(),getFirstName(),getLastName(),getUserName(),getPassword(),getRole()));

        setId(null);
        setFirstName("");
        setLastName("");
        setUserName("");
        setPassword("");
        setRoleId(null);
        setRole(null);
        return "person";
    }
    public String editPerson(Long id){
        PersonDomain personDomain = personService.getPerson(id);
        setId(personDomain.getId());
        setFirstName(personDomain.getFirstName());
        setLastName(personDomain.getLastName());
        setUserName(personDomain.getUserName());
        setPassword(personDomain.getPassword());
        setRole(personDomain.getRole().getId());
        return "person";
    }
    public String removePerson(Long id){
        personService.removePerson(id);
        return "person";
    }
    public List<PersonDomain> getPersons(){
        return personService.getPersons();
    }

    public List<PersonDomain> getPersonsFilter(){
        if(myFilter==null || myFilter.equals(""))
            return personService.getPersons();
        else
            return personService.getPersonsFirstNameContain(myFilter);
    }
    public List<PersonDomain> getTeachers(){
        return personService.getTeachers();
    }



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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {return userName;}

    public void setUserName(String userName){this.userName = userName;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}

    public RoleDomain getRole(){

        RoleDomain roleDomain= roleService.getRole(getRoleId());
        roleId= roleDomain.getId();
        return roleDomain;
    }



    public void setRole(Integer roleId){

        this.roleId= roleId;}

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getMyFilter(){
        return myFilter;
    }
    public void setMyFilter(String myFilter){
        this.myFilter= myFilter;
    }
}
