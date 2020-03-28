package io.zipcoder.persistenceapp.jbdc;

import io.zipcoder.persistenceapp.models.Person;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setFirst_name(resultSet.getString("first_name"));
        person.setLast_name(resultSet.getString("last_name"));
        person.setMobile(resultSet.getString("mobile"));
        person.setBirthday(resultSet.getString("birthday"));
        person.setHome_id(resultSet.getInt("home_id"));

        return person;
    }

}
