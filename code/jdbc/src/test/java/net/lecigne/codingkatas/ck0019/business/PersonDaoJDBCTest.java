package net.lecigne.codingkatas.ck0019.business;

import net.lecigne.codingkatas.ck0019.common.Dao;
import net.lecigne.codingkatas.ck0019.common.JDBCHelper;
import net.lecigne.codingkatas.ck0019.common.JDBCHelperFactory;
import net.lecigne.codingkatas.ck0019.common.JDBCHelperFactory.JDBCTypes;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class PersonDaoJDBCTest {

    private static boolean schemaIsDone = false;

    private final JDBCHelper jdbc = JDBCHelperFactory.getJDBCHelper(JDBCTypes.H2);
    private final Dao<Person> personDao = new PersonDaoJDBC(jdbc);

    private void runScript(String fileName) {
        Connection connection = null;
        try {
            connection = this.jdbc.getConnection();
            try {
                File script = new File(PersonDaoJDBCTest.class.getClassLoader().getResource(fileName).getFile());
                RunScript.execute(connection, new FileReader(script));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @BeforeEach
    public void setUp() {
        if (!schemaIsDone) {
            runScript("schema.sql");
            schemaIsDone = true;
        }
        runScript("init.sql");
    }

    @Test
    void testRead() {
        Person personExpected = new Person(1, "Foobar", 25);
        Person personTested = personDao.read(1);
        assertThat(personTested).isEqualToComparingFieldByField(personExpected);
    }

    @Test
    void testReadAll() {
        assertThat(personDao.readAll().size()).isEqualTo(2);
    }

    @Test
    void testCreate() {
        Person personExpected = new Person("Anthony", 32);
        personDao.create(personExpected);
        assertThat(personDao.readAll().size()).isEqualTo(3);
        Person personTested = personDao.read(personExpected.getId());
        assertThat(personTested).isEqualToComparingFieldByField(personExpected);
    }

    @Test
    void testUpdate() {
        Person p = personDao.read(2);
        p.setName("Jacques");
        p.setAge(50);
        personDao.update(p);

        Person personTested = personDao.read(2);
        Person personExpected = new Person(2, "Jacques", 50);
        assertThat(personTested).isEqualToComparingFieldByField(personExpected);
    }

    @Test
    void testDelete() {
        personDao.delete(2);
        assertThat(personDao.read(2)).isNull();
    }

}
