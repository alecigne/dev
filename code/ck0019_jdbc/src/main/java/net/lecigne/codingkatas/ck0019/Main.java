package net.lecigne.codingkatas.ck0019;

import net.lecigne.codingkatas.ck0019.business.Person;
import net.lecigne.codingkatas.ck0019.business.PersonDaoJDBC;
import net.lecigne.codingkatas.ck0019.common.Dao;
import net.lecigne.codingkatas.ck0019.common.JDBCHelper;
import net.lecigne.codingkatas.ck0019.common.JDBCHelperFactory;
import net.lecigne.codingkatas.ck0019.common.JDBCHelperFactory.JDBCTypes;

public class Main {

	@SuppressWarnings("squid:S106")
	public static void main(String[] args) {
		JDBCHelper jdbc = JDBCHelperFactory.getJDBCHelper(JDBCTypes.MARIADB);
		Dao<Person> personDao = new PersonDaoJDBC(jdbc);

		Person person1 = new Person("Foobar", 32);
		Person person2 = new Person("Baaz", 21);

		personDao.create(person1);
		personDao.create(person2);
		personDao.readAll().forEach(System.out::println);
	}

}
