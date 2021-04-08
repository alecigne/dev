package net.lecigne.codingkatas.springdijavaconfig.vehicle;

import net.lecigne.codingkatas.springdijavaconfig.driver.Driver;
import net.lecigne.codingkatas.springdijavaconfig.motor.Motor;

public class Vehicle {

    private final Motor motor;
    private final Driver driver;

    public Vehicle(Motor motor, Driver driver) {
        this.motor = motor;
        this.driver = driver;
    }

    public String go() {
        return "The driver is yelling: " + driver.drive() + "\n" +
                "The sound the car makes is: " + motor.propel();
    }

}
