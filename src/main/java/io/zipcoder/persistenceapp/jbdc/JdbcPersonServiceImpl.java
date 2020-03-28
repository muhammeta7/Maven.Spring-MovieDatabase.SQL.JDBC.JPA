package io.zipcoder.persistenceapp.jbdc;


import io.zipcoder.persistenceapp.jpa.JpaPersonService;
import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Primary
@Service
public class JdbcPersonServiceImpl implements JpaPersonService {

    @Autowired
    PersonRepository repo;

    @Override
    public void createPerson(Person person) {
        repo.save(person);
    }

    @Override
    public Person findById(Integer id) {
        return repo.findOne(id);
    }

    @Override
    public List<Person> showAll() {
        return (List<Person>)repo.findAll();
    }

    @Override
    public void updatePerson(Integer id, Person person) {
        Person originalPerson = repo.findOne(id);
        originalPerson.setFirst_name(person.getFirst_name());
        originalPerson.setLast_name(person.getLast_name());
        originalPerson.setMobile(person.getMobile());
        originalPerson.setBirthday(person.getBirthday());
        originalPerson.setHome_id(person.getHome_id());
        repo.save(originalPerson);
    }

    @Override
    public Boolean deletePerson(Integer id) {
        repo.delete(id);
        return true;
    }

    @Override
    public List<Person> findAllWithMobile(String mobile) {
        return null;
    }

    @Override
    public List<Person> findAllWithFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Person> findAllWithLastName(String lastName) {
        return null;
    }

    @Override
    public List<Person> findAllWithBirthday(String bday) {
        return null;
    }

    @Override
    public Map<String, ArrayList<Person>> getSurnameMap() {
        Map<String, ArrayList<Person>> map = new HashMap<>();
        List<Person> people = showAll();
        people.forEach(p -> {
            if (map.containsKey(p.getLast_name())) {
                ArrayList<Person> newMap = map.get(p.getLast_name());
                newMap.add(p);
                map.put(p.getLast_name(), newMap);
            } else {
                ArrayList<Person> newMap = new ArrayList<>();
                newMap.add(p);
                map.put(p.getLast_name(), newMap);
            }
        });
        return map;
    }

    @Override
    public Map<String, Integer> getFirstNameMap() {
        Map<String, Integer> map = new HashMap<>();
        Person[] people = showAll().stream().toArray(Person[]::new);
        for(Person p : people){
            map.compute(p.getFirst_name(), (k,v) -> (v == null) ? 1 : v++);
        }
        return map;
    }
}
