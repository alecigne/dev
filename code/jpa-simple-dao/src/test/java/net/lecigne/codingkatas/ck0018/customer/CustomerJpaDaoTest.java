package net.lecigne.codingkatas.ck0018.customer;

import net.lecigne.codingkatas.ck0018.common.Dao;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerJpaDaoTest {

    Dao<Customer> customerDao = new CustomerJpaDao();

    @Test
    void testCreate() {
        Customer customerExpected = new Customer("Anthony");
        customerDao.save(customerExpected);
        Customer customerTested = customerDao.get(customerExpected.getCustomerId());
        assertThat(customerTested).isEqualToComparingFieldByField(customerExpected);
    }

}
