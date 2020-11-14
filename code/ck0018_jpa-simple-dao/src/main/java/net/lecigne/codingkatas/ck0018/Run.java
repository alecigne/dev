package net.lecigne.codingkatas.ck0018;

import net.lecigne.codingkatas.ck0018.common.Dao;
import net.lecigne.codingkatas.ck0018.customer.Customer;
import net.lecigne.codingkatas.ck0018.customer.CustomerJpaDao;
import net.lecigne.codingkatas.ck0018.passport.Passport;

public class Run {

    public static void main(String[] args) {
        Dao<Customer> customerDao = new CustomerJpaDao();
        Customer c1 = new Customer("Anthony");
        Passport p1 = new Passport("abc1234");
        c1.setPassport(p1);
        customerDao.save(c1);
    }

}
