package ejb;

import domain.UsersDomain;
import jpa.Users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nahid on 2017-03-29.
 */

@Stateless
public class UsersServiceImpl implements UsersService {
    @PersistenceContext
    EntityManager em3;

    @Override
    public void addUser(UsersDomain users) {
        Users u = new Users(users.getFirstName(),users.getLastName(), users.getUserName(),users.getPassword(),users.getRole());
        em3.persist(u);
    }

    @Override
    public void updateUser(UsersDomain users) {
        Users u= em3.find(Users.class, users.getId());
        u.setFirstName(users.getFirstName());
        u.setLastName(users.getLastName());
        u.setUserName(users.getUserName());
        u.setPassword(users.getPassword());
        u.setRole(users.getRole());
        em3.merge(u);
    }

    @Override
    public UsersDomain getUsers(Integer id) {
        Users u = em3.find(Users.class,id);
        return new UsersDomain(u.getId(),u.getFirstName(),u.getLastName(),u.getUserName(),u.getPassword(),u.getRole() );
    }

    @Override
    public void removeUser(Integer id) {
       Users u  = em3.find(Users.class,id);
        em3.remove(u);
    }

    @Override
    public List<UsersDomain> getAllUsers() {
        List<Users> l = em3.createNamedQuery("selectAllTheUsers").getResultList();

        return l.stream().map(u->new UsersDomain(u.getId(),u.getFirstName(),u.getLastName(),u.getUserName(),u.getPassword(),u.getRole())).collect(Collectors.toList());
    }
}
