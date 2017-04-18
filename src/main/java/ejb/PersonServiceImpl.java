package ejb;

import domain.PersonDomain;
import domain.RoleDomain;
import jpa.Person;
import jpa.Role;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Solidbeans on 2017-03-20.
 */

@Stateless
public class PersonServiceImpl implements PersonService{
    @PersistenceContext
    EntityManager em;

    @Override
    public void addPerson(PersonDomain person) {
        Role r = em.find(Role.class,person.getRole().getId());
        Person p = new Person(person.getFirstName(),person.getLastName(), person.getUserName(), person.getPassword(),r);
        em.persist(p);
    }

    @Override
    public void updatePerson(PersonDomain person) {
        Person p = em.find(Person.class,person.getId());
        Role r = em.find(Role.class,person.getRole().getId());
        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setUserName(person.getUserName());
        p.setPassword(person.getPassword());
        p.setRole(r);
        em.merge(p);

    }

    @Override
    public PersonDomain getPerson(Long id) {
        Person p = em.find(Person.class,id);
        RoleDomain r = new RoleDomain(p.getRole().getRoleName());
        return new PersonDomain(p.getId(),p.getFirstName(),p.getLastName(),p.getUserName(),p.getPassword(), r);
    }

    @Override
    public void removePerson(Long id) {
        Person p = em.find(Person.class,id);
        em.remove(p);
    }

    @Override
    public List<PersonDomain> getPersons() {

        List<Person> l = em.createNamedQuery("selectAll").getResultList();

        return l.stream().map(p->new PersonDomain(p.getId(),p.getFirstName(),p.getLastName(),p.getUserName(),p.getPassword(),new RoleDomain(p.getRole().getRoleName()))).collect(Collectors.toList());

    }

    @Override
    public List<PersonDomain> getTeachers() {
        List<Person> l= em.createNamedQuery("selectTeachers").setParameter("name","teacher").getResultList();
        return l.stream().map(p->new PersonDomain(p.getId(),p.getFirstName(),p.getLastName(), p.getUserName(),p.getPassword(),new RoleDomain(p.getRole().getRoleName()))).collect(Collectors.toList());
    }

    @Override
    public List<PersonDomain> getStudents() {
        List<Person> l=em.createNamedQuery("selectStudents").setParameter("name","student").getResultList();
        return l.stream().map(p->new PersonDomain(p.getId(),p.getFirstName(),p.getLastName(), p.getUserName(),p.getPassword(),new RoleDomain(p.getRole().getRoleName()))).collect(Collectors.toList());
    }

    @Override
    public List<PersonDomain> getPersonsFirstNameContain(String filter) {
        List<Person> l= em.createNamedQuery("selectSome").setParameter("filt", filter).getResultList();
        return l.stream().map(p->new PersonDomain(p.getId(),p.getFirstName(),p.getLastName(), p.getUserName(),p.getPassword(),new RoleDomain(p.getRole().getRoleName()))).collect(Collectors.toList());
    }


}
