package jsf;

import domain.RoleDomain;
import ejb.RoleService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by Nahid on 2017-03-28.
 */
@ManagedBean
@RequestScoped
public class RoleBean {
    private Integer id;
    private String roleName;


    @EJB
    RoleService roleService;

    public String addRole(){

        if (getId()==null)
            roleService.addRole(new RoleDomain(getRoleName()));
        else
            roleService.updateRole(new RoleDomain(getId(),getRoleName()));

        setId(null);
        setRoleName("");

        return "role";
    }
    public String editRole(Integer id){
        RoleDomain roleDomain = roleService.getRole(id);
        setId(roleDomain.getId());
        setRoleName(roleDomain.getRoleName());

        return "role";
    }

    public List<RoleDomain> getRoles(){
        return roleService.getRoles();
    }

    public String removeRole(Integer id){
        roleService.removeRole(id);
        return "role";
    }
    public String getSubmitButtonLabel(){
        if (id==null)
            return "Add";
        else
            return "Update";
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
