package net.lecigne.codingkatas.springdixmlconfig.vehicle;

import net.lecigne.codingkatas.springdixmlconfig.driver.Driver;
import net.lecigne.codingkatas.springdixmlconfig.motor.Motor;

public class Vehicle {

    private Motor motor;
    private Driver driver;

    public Vehicle() {
    }

    public Vehicle(Motor motor, Driver driver) {
        this.motor = motor;
        this.driver = driver;
    }

    public String go() {
        return "The driver is yelling: " + driver.drive() + "\n" +
                "The sound the car makes is: " + motor.propel();
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

}
