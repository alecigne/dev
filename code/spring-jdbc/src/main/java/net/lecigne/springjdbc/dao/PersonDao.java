package net.lecigne.springjdbc.dao;

import java.util.List;
import javax.sql.DataSource;
import net.lecigne.springjdbc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;


@SuppressWarnings("SqlResolve")
@Repository
public class PersonDao {

  private JdbcTemplate jdbcTemplate;

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public PersonDao(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  // Simple execution - no parameters and no business result to retrieve

  public void truncateTable() {
    jdbcTemplate.execute("TRUNCATE TABLE PERSON");
  }

  public int createMyself() {
    String sql = "INSERT INTO PERSON (NAME, AGE) VALUES ('Anthony Le Cigne', 33)";
    return jdbcTemplate.update(sql);
  }

  // Retrieving a result

  public int countAllPersons() {
    String sql = "SELECT COUNT(*) FROM PERSON";
    return jdbcTemplate.queryForObject(sql, Integer.class);
  }

  public List<Person> findAll() {
    String sql = "SELECT * FROM PERSON";
    return jdbcTemplate.query(sql, new PersonMapper());
  }

  public List<Person> findAllLambda() {
    String sql = "SELECT * FROM PERSON";
    return jdbcTemplate.query(sql, (rs, rowNum) -> new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
  }

  public List<Person> findAllBeanPropertyRowMapper() {
    String sql = "SELECT * FROM PERSON";
    return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Person.class));
  }

  // Providing parameters

  public int createSomeone(Person person) {
    String sql = "INSERT INTO PERSON (NAME, AGE) VALUES (?, ?)";
    return jdbcTemplate.update(sql, person.getName(), person.getAge());
  }

  public Person findById(int id) {
    String sql = "SELECT * FROM PERSON WHERE ID = ?";
    return jdbcTemplate.queryForObject(sql, new Object[] {id}, new PersonMapper());
  }

  public int createSomeoneWithNamedParameters(Person person) {
    String sql = "INSERT INTO PERSON (NAME, AGE) VALUES (:name, :age)";
    SqlParameterSource namedParameters = new MapSqlParameterSource("name", person.getName())
        .addValue("age", person.getAge());
    return namedParameterJdbcTemplate.update(sql, namedParameters);
  }

  public int createSomeoneWithNamedParametersAndBeanProperties(Person person) {
    String sql = "INSERT INTO PERSON (NAME, AGE) VALUES (:name, :age)";
    SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(person);
    return namedParameterJdbcTemplate.update(sql, namedParameters);
  }

  public int createSomeoneWithNamedParametersAndBeanPropertiesIncorrect(Person person) {
    String sql = "INSERT INTO PERSON (NAME, AGE) VALUES (:fullname, :age)"; // fullname is not a bean property
    SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(person);
    return namedParameterJdbcTemplate.update(sql, namedParameters);
  }

}
