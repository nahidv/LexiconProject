package ejb;

import domain.UsersDomain;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Nahid on 2017-03-28.
 */
@Local
public interface UsersService {
    void addUser(UsersDomain users);
    void updateUser(UsersDomain users);
    UsersDomain getUsers(Integer id);
    void removeUser(Integer id);
    List<UsersDomain> getAllUsers();
    //public List<UsersDomain>getAllUsersFirstNameContain(String filter);
}
