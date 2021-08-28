package net.lecigne.codingkatas.springdixmlconfig.vehicle;

import net.lecigne.codingkatas.springdixmlconfig.driver.Driver;
import net.lecigne.codingkatas.springdixmlconfig.motor.Motor;

public class VehicleSetterInjection {

    private Motor motor;
    private Driver driver;

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
