package net.lecigne.codingkatas.springdixmlconfig.vehicle;

import net.lecigne.codingkatas.springdixmlconfig.driver.Driver;
import net.lecigne.codingkatas.springdixmlconfig.motor.Motor;

public class VehicleConstructorInjection {

    private final Motor motor;
    private final Driver driver;

    public VehicleConstructorInjection(Motor motor, Driver driver) {
        this.motor = motor;
        this.driver = driver;
    }

    public String go() {
        return "The driver is yelling: " + driver.drive() + "\n" +
                "The sound the car makes is: " + motor.propel();
    }

}
