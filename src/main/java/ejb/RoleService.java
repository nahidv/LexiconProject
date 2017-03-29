package ejb;

import domain.RoleDomain;

import java.util.List;

/**
 * Created by Nahid on 2017-03-28.
 */
public interface RoleService {
    void addRole(RoleDomain role);
    void updateRole(RoleDomain role);
    RoleDomain getRole(Integer id);
    void removeRole(Integer id);
    List<RoleDomain> getRoles();
}
