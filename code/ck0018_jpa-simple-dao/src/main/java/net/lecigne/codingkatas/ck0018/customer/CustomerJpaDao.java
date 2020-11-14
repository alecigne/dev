package net.lecigne.codingkatas.ck0018.customer;

import net.lecigne.codingkatas.ck0018.common.Dao;
import net.lecigne.codingkatas.ck0018.common.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CustomerJpaDao implements Dao<Customer> {

    @Override
    public Customer get(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction txn = em.getTransaction();
        Customer customer = null;

        try {
            txn.begin();
            customer = em.find(Customer.class, id);
        } catch (Exception e) {
            txn.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        EntityManager em = JpaUtil.getEntityManager();
        List<Customer> customerList = new ArrayList<>();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            customerList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return customerList;
    }

    @Override
    public void save(Customer customer) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction txn = em.getTransaction();

        try {
            txn.begin();
            em.persist(customer);
            txn.commit();
        } catch (Exception e) {
            txn.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Customer customer, String[] params) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction txn = em.getTransaction();
        try {
            txn.begin();
            em.merge(customer);
            txn.commit();
        } catch (Exception e) {
            txn.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Customer user) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction txn = em.getTransaction();
        try {
            txn.begin();
            em.remove(em.find(Customer.class, user.getCustomerId()));
            txn.commit();
        } catch (Exception e) {
            txn.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}
