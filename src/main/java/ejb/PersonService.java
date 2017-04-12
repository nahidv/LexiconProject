package ejb;

import domain.PersonDomain;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Solidbeans on 2017-03-20.
 */
@Local
public interface PersonService {

    void addPerson(PersonDomain person);
    void updatePerson(PersonDomain person);
    PersonDomain getPerson(Long id);
    void removePerson(Long id);
    List<PersonDomain> getPersons();
    List<PersonDomain>getTeachers();
    List<PersonDomain>getStudents();
    public List<PersonDomain>getPersonsFirstNameContain(String filter);
}
