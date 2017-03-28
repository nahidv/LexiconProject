package domain;

/**
 * Created by Nahid on 2017-03-28.
 */
public class RoleDomain {
    private int id;
    private String roleName;

    public RoleDomain(int id, String roleName){
        this.id= id;
        this.roleName= roleName;
    }
    public int getId(){return id; }

    public void setId(int id){this.id = id;}

    public String getRole(){return roleName;}

    public void setRole(String roleName){this.roleName= roleName;}
}


