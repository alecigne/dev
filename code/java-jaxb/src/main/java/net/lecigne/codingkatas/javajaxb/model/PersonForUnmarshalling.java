package net.lecigne.codingkatas.javajaxb.model;

import net.lecigne.codingkatas.javajaxb.utils.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "individual")
public class PersonForUnmarshalling {

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

    private int salary;

    @SuppressWarnings("unused") // for JAXB
    private PersonForUnmarshalling() {
    }

    public PersonForUnmarshalling(Sex sex, String firstName, String lastName, LocalDate birthDate, int children, int salary) {
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
