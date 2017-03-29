package domain;

/**
 * Created by Nahid on 2017-03-28.
 */
public class RoleDomain {
    private int id;
    private String roleName;

    public RoleDomain(String roleName){
        this.roleName= roleName;
    }

    public RoleDomain(int id, String roleName){
        this.id= id;
        this.roleName= roleName;
    }
    public int getId(){return id; }

    public void setId(int id){this.id = id;}

    public String getRoleName(){return roleName;}

    public void setRoleName(String roleName){this.roleName= roleName;}
}


