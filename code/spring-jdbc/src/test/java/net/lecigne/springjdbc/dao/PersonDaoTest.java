package net.lecigne.springjdbc.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import net.lecigne.springjdbc.config.SpringJdbcConfig;
import net.lecigne.springjdbc.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJdbcConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonDaoTest {

  @Autowired
  PersonDao personDao;

  @Test
  public void truncateTable_shouldWork() {
    personDao.truncateTable();
    int numberOfPersons = personDao.countAllPersons();
    assertThat(numberOfPersons).isEqualTo(0);
  }

  @Test
  public void createMyself_shouldWork() {
    int rowsAffected = personDao.createMyself();
    assertThat(rowsAffected).isEqualTo(1);
  }

  @Test
  public void countAllPersons_shouldWork() {
    int numberOfPersons = personDao.countAllPersons();
    assertThat(numberOfPersons).isEqualTo(4);
  }

  @Test
  public void findAll_shouldWork() {
    List<Person> persons = personDao.findAll();
    assertThat(persons).hasSize(4);
  }

  @Test
  public void findAllLambda_shouldWork() {
    List<Person> persons = personDao.findAllLambda();
    assertThat(persons).hasSize(4);
  }

  @Test
  public void findAllBeanPropertyRowMapper() {
    List<Person> persons = personDao.findAllBeanPropertyRowMapper();
    assertThat(persons).hasSize(4);
  }

  @Test
  public void createSomeone_shouldWork() {
    int rowsAffected = personDao.createSomeone(new Person("Jacques Durand", 65));
    assertThat(rowsAffected).isEqualTo(1);
  }

  @Test
  public void findById_shouldWork() {
    Person person = personDao.findById(1);
    assertThat(person.getName()).isEqualTo("Arthur Boulanger");
    assertThat(person.getAge()).isEqualTo(30);
  }

  @Test
  public void createSomeoneWithNamedParameters_shouldWork() {
    Person personToCreate = new Person("Marie Girard", 19);
    int rowsAffected = personDao.createSomeoneWithNamedParameters(personToCreate);
    assertThat(rowsAffected).isEqualTo(1);
  }

  @Test
  public void createSomeoneWithNamedParametersAndBeanProperties_shouldWork() {
    Person personToCreate = new Person("Aurélie Bigard", 19);
    int rowsAffected = personDao.createSomeoneWithNamedParametersAndBeanProperties(personToCreate);
    assertThat(rowsAffected).isEqualTo(1);
  }

  @Test
  public void createSomeoneWithNamedParametersAndBeanPropertiesIncorrect_shouldThrowException() {
    Person personToCreate = new Person("Marcel Legrand", 76);
    assertThatExceptionOfType(InvalidDataAccessApiUsageException.class)
        .isThrownBy(() -> personDao.createSomeoneWithNamedParametersAndBeanPropertiesIncorrect(personToCreate));
  }

}
