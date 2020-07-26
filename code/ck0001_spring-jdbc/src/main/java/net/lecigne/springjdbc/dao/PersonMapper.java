package net.lecigne.springjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.lecigne.springjdbc.model.Person;
import org.springframework.jdbc.core.RowMapper;

public class PersonMapper implements RowMapper<Person> {

  public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
    Person person = new Person();
    person.setId(rs.getInt("id"));
    person.setName(rs.getString("name"));
    person.setAge(rs.getInt("age"));
    return person;
  }

}
