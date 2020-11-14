package net.lecigne.codingkatas.ck0018.passport;

import net.lecigne.codingkatas.ck0018.customer.Customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "passport_number")
    private String number;

    @OneToOne(mappedBy = "passport")
    private Customer customer;

    public Passport() {
    }

    public Passport(String number) {
        this.number = number;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addCustomer(Customer customer) {
        this.customer = customer;
        customer.setPassport(this);
    }

}
