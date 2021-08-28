package net.lecigne.codingkatas.springdixmlconfig.vehicle;

import net.lecigne.codingkatas.springdixmlconfig.driver.Driver;
import net.lecigne.codingkatas.springdixmlconfig.motor.Motor;

public class VehicleFieldInjection {

    private Motor motor;
    private Driver driver;

    public String go() {
        return "The driver is yelling: " + driver.drive() + "\n" +
                "The sound the car makes is: " + motor.propel();
    }

}
