package net.lecigne.codingkatas.ck0019.business;

import net.lecigne.codingkatas.ck0019.common.Dao;
import net.lecigne.codingkatas.ck0019.common.JDBCHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoJDBC implements Dao<Person> {

    private final JDBCHelper jdbcHelper;

    private static final String SQL_QUERY_CREATE = "insert into `person` (`name`, `age`) values (?, ?)";
    private static final String SQL_QUERY_READ = "select * from `person` where `id` = ?";
    private static final String SQL_QUERY_READALL = "select * from `person`";
    private static final String SQL_QUERY_UPDATE = "update `person` set `name` = ?, `age` = ? where `id` = ?";
    private static final String SQL_QUERY_DELETE = "delete from `person` where `id` = ?";

    public PersonDaoJDBC(JDBCHelper jdbcHelper) {
        this.jdbcHelper = jdbcHelper;
    }

    @Override
    public void create(Person person) {
        Connection cnx = null;
        PreparedStatement ps = null;
        try {
            cnx = jdbcHelper.getConnection();
            ps = cnx.prepareStatement(SQL_QUERY_CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                person.setId(generatedKeys.getLong(1));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcHelper.disconnect(cnx, ps);
        }
    }

    @Override
    public Person read(long id) {
        Connection cnx = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Person person = null;
        try {
            cnx = jdbcHelper.getConnection();
            ps = cnx.prepareStatement(SQL_QUERY_READ);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString(2);
                int age = rs.getInt(3);
                person = new Person(id, name, age);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcHelper.disconnect(cnx, ps, rs);
        }
        return person;
    }

    @Override
    public List<Person> readAll() {
        Connection cnx = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Person> persons = new ArrayList<>();
        try {
            cnx = jdbcHelper.getConnection();
            ps = cnx.prepareStatement(SQL_QUERY_READALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                Person person = new Person(id, name, age);
                persons.add(person);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcHelper.disconnect(cnx, ps, rs);
        }
        return persons;
    }

    @Override
    public void update(Person person) {
        Connection cnx = null;
        PreparedStatement ps = null;
        try {
            cnx = jdbcHelper.getConnection();
            ps = cnx.prepareStatement(SQL_QUERY_UPDATE);
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setLong(3, person.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcHelper.disconnect(cnx, ps);
        }
    }

    @Override
    public void delete(long id) {
        Connection cnx = null;
        PreparedStatement ps = null;
        try {
            cnx = jdbcHelper.getConnection();
            ps = cnx.prepareStatement(SQL_QUERY_DELETE);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcHelper.disconnect(cnx, ps);
        }
    }

}
