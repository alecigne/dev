package net.lecigne.codingkatas.springdixmlconfig.vehicle;

import net.lecigne.codingkatas.springdixmlconfig.driver.Driver;
import net.lecigne.codingkatas.springdixmlconfig.motor.Motor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

// Bean definition in beans.xml
public class VehicleSetterInjection {

    private Motor motor;
    private Driver driver;

    public String go() {
        return "The driver is yelling: " + driver.drive() + "\n" +
                "The sound the car makes is: " + motor.propel();
    }

    @Autowired
    @Qualifier("electricMotor")
    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    @Autowired
    @Qualifier("crazyDriver")
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

}
