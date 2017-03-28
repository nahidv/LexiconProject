package jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Nahid on 2017-03-28.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Integer id;
    private String roleName;

    public Integer getId(){return id; }

    public void setId(Integer id){this.id = id;}

    public String getRole(){return roleName;}

    public void setRole(String roleName){this.roleName= roleName;}
}
