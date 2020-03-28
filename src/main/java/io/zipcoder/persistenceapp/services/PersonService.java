package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.jbdc.PersonMapper;
import io.zipcoder.persistenceapp.models.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {

    DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", "");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    // Add a Person to the database
    public void createPerson(Person person){
        Integer id = person.getId();
        String firstName = person.getFirst_name();
        String lastName = person.getLast_name();
        String mobile = person.getMobile();
        String birthday = person.getBirthday();
        Integer homeId = person.getHome_id();
        this.jdbcTemplate.execute("INSERT INTO PERSON(id, FIRST_NAME, LAST_NAME, BIRTHDAY, mobile, HOME_ID)"
                + " VALUES (" + id + ",'" + firstName + "','" + lastName + "','" + birthday + "','" + mobile + "'," + homeId + ")");
    }

    // Find a single person by ID
    public Person findById(Integer id){
        String sql = "SELECT * FROM PERSON WHERE ID = ?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PersonMapper());
    }

    // FindAll
    public List<Person> showAll(){
        String sql = "SELECT * FROM PERSON";
        return jdbcTemplate.query(sql, new PersonMapper());
    }

    // Update an existing Person in the database
    public void updatePerson(Integer id, Person person){
        String fN = person.getFirst_name();
        String ln = person.getLast_name();
        String bD = person.getBirthday();
        String mB = person.getMobile();
        int hId = person.getHome_id();
        this.jdbcTemplate.execute("UPDATE PERSON SET FIRST_NAME = '" + fN + "', LAST_NAME = '" + ln + "', BIRTHDAY = '" + bD + "', MOBILE = '" + mB + "', HOME_ID = " + hId + "WHERE id =" + id + ";");
    }

    // Remove a person from the database
    public Boolean deletePerson(Integer id){
        String sql = "DELETE FROM PERSON WHERE ID = ?;";
        jdbcTemplate.update(sql, id);
        return true;
    }

    // Find all people with a mobile number
    public List<Person> findAllWithMobile(String mobile){
        String sql = "SELECT * FROM PERSON WHERE mobile = ?;";
        return jdbcTemplate.query(sql, new PersonMapper(), mobile);
    }

    // find all people with a particular first name
    public List<Person> findAllWithFirstName(String firstName){
        String sql = "SELECT * FROM PERSON WHERE first_name = ?;";
        return jdbcTemplate.query(sql, new PersonMapper(), firstName);
    }

    // find all people with a particular last name
    public List<Person> findAllWithLastName(String lastName){
        String sql = "SELECT * FROM PERSON WHERE last_name = ?;";
        return jdbcTemplate.query(sql, new PersonMapper(), lastName);
    }

    // find all people with a particular birthdate
    public List<Person> findAllWithBirthday(String bday){
        String sql = "SELECT * FROM PERSON WHERE birthday = ?;";
        return jdbcTemplate.query(sql, new PersonMapper(), bday);
    }

    // Generate a map of surnames to lists of people with that surname
    public Map<String,ArrayList<Person>> getSurnameMap() {
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

    // Generate a map of first names to the number of times they occur.
    public Map<String, Integer> getFirstNameMap(){
        Map<String, Integer> map = new HashMap<>();
        Person[] people = showAll().stream().toArray(Person[]::new);
        for(Person p : people){
            map.compute(p.getFirst_name(), (k,v) -> (v == null) ? 1 : v++);
        }
        return map;
    }

}
