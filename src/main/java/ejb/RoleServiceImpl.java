package ejb;

import domain.RoleDomain;
import jpa.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nahid on 2017-03-28.
 */

@Stateless
public class RoleServiceImpl implements RoleService{
    @PersistenceContext
    EntityManager em2;

    @Override
    public void addRole(RoleDomain role) {
        Role r = new Role(role.getRoleName());
        em2.persist(r);
    }

    @Override
    public void updateRole(RoleDomain role) {
        Role r = em2.find(Role.class,role.getId());
        r.setRoleName(role.getRoleName());

        em2.merge(r);
    }

    @Override
    public RoleDomain getRole(Integer id) {
        Role r = em2.find(Role.class,id);
        return new RoleDomain(r.getId(),r.getRoleName());
    }

    @Override
    public void removeRole(Integer id) {
        Role r = em2.find(Role.class,id);
        em2.remove(r);
    }

    @Override
    public List<RoleDomain> getRoles() {
        List<Role> l = em2.createNamedQuery("selectAllRoles").getResultList();

        return l.stream().map(r->new RoleDomain(r.getId(),r.getRoleName())).collect(Collectors.toList());
    }

}
