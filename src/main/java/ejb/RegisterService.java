package ejb;

import domain.RegisterDomain;

import java.util.List;

/**
 * Created by Nahid on 2017-04-11.
 */
public interface RegisterService {
    void addRegister(RegisterDomain register);
    void updateRegister(RegisterDomain register);
    RegisterDomain getRegister(Long id);
    void remove(Long id);
    List<RegisterDomain> getRegisters();
    List<RegisterDomain>getRegisteredStudents(String courseName);
}
