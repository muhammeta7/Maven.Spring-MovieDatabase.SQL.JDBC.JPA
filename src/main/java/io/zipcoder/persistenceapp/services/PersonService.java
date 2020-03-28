package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.jbdc.PersonMapper;
import io.zipcoder.persistenceapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private static final String UPDATE_PERSON_BY_ID = "UPDATE PERSON SET ? WHERE ID = ?;";
    private static final String GET_ALL_PEOPLE = "SELECT * FROM PERSON";

    DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", "");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    // Add a Person to the database
    public void createPerson(Person person){
        String ADD_PERSON = "INSERT INTO PERSON (FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY, HOME_ID) VALUES (?,?,?,?, ?)";
        String firstName = person.getFirst_name();
        String lastName = person.getLast_name();
        String mobile = person.getMobile();
        String birthday = person.getBirthday();
        Integer homeId = person.getHome_id();
        jdbcTemplate.update(ADD_PERSON, firstName, lastName, mobile, birthday,homeId);
    }

    // Find a single person by ID
    public Person findById(Integer id){
        String GET_PERSON_BY_ID = "SELECT * FROM PERSON WHERE ID = ?;";
        return jdbcTemplate.queryForObject(GET_PERSON_BY_ID, new Object[]{id}, new PersonMapper());
    }

    // FindAll
    public List<Person> showAll(){
        return jdbcTemplate.query(GET_ALL_PEOPLE, new PersonMapper());
    }

    // Update an existing Person in the database
//    public Person updatePerson(Integer id, Person person){
//        Person originalPerson =
//    }

    // Remove a person from the database
    public int deletePerson(Integer id){
        String DELETE_PERSON_BY_ID = "DELETE FROM PERSON WHERE ID = ?;";
        return jdbcTemplate.update(DELETE_PERSON_BY_ID, id);
    }

    // Find all people with a mobile number
    public Person findAllWithMobile(String mobile){
        String GET_PEOPLE_WITH_MOBILE = "SELECT * FROM PERSON WHERE mobile = ?;";
        return jdbcTemplate.queryForObject(GET_PEOPLE_WITH_MOBILE, new PersonMapper(), mobile);
    }

    // remove a list of people from the database


    // find all people with a particular first name
    public Person findAllWithSurname(String firstName){
        String GET_PEOPLE_WITH_FIRSTNAME = "SELECT * FROM PERSON WHERE first_name = ?;";
        return jdbcTemplate.queryForObject(GET_PEOPLE_WITH_FIRSTNAME, new PersonMapper(), firstName);
    }

    // find all people with a particular last name
    public Person findAllWithLastName(String lastName){
        String GET_PEOPLE_WITH_LASTNAME = "SELECT * FROM PERSON WHERE last_name = ?;";
        return jdbcTemplate.queryForObject(GET_PEOPLE_WITH_LASTNAME, new PersonMapper(), lastName);
    }

    // find all people with a particular birthdate
    public Person findAllWithBirthday(String bday){
        String GET_PEOPLE_WITH_BIRTHDAY = "SELECT * FROM PERSON WHERE birthday = ?;";
        return jdbcTemplate.queryForObject(GET_PEOPLE_WITH_BIRTHDAY, new PersonMapper(), bday);
    }

    // Generate a map of surnames to lists of people with that surname

    // Generate a map of first names to the number of times they occur.


}
