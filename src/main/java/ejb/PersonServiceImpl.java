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
 * Created by Nahid on 2017-03-28.
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

    @Override
    public PersonDomain checkLogin(String userName, String password) {
        List<Person> l=em.createNamedQuery("selectOne").setParameter("name",userName). setParameter("passw", password).getResultList();

        if (l.size() == 1) {
            RoleDomain role = new RoleDomain(l.get(0).getRole().getId(), l.get(0).getRole().getRoleName());
            return new PersonDomain(l.get(0).getId(), l.get(0).getFirstName(), l.get(0).getLastName(), l.get(0).getUserName(), l.get(0).getPassword(), role);
        }else
            return null;
    }


}
