package net.lecigne.codingkatas.javajaxb.model;

import net.lecigne.codingkatas.javajaxb.utils.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "individual")
@XmlType(propOrder = {"firstName", "lastName", "children", "birthDate", "sex"})
@XmlAccessorType(XmlAccessType.NONE)
public class Person {

    @XmlAttribute(name = "sex")
    private Sex sex;

    @XmlElement(name = "givenName")
    private String firstName;

    @XmlElement(name = "surname")
    private String lastName;

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthDate;

    @XmlElement
    private int children;

    @XmlTransient
    private int salary;

    @SuppressWarnings("unused") // for JAXB
    private Person() {
    }

    public Person(Sex sex, String firstName, String lastName, LocalDate birthDate, int children, int salary) {
        this.sex = sex;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.children = children;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getChildren() {
        return children;
    }

    public Sex getSex() {
        return sex;
    }

    public int getSalary() {
        return salary;
    }
}
